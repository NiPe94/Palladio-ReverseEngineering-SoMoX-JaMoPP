package org.somox.test.gast2seff.visitors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.BitSet;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Method;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor.FunctionCallType;
import org.somox.kdmhelper.KDMReader;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.MethodLevelSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;
import org.somox.sourcecodedecorator.SourcecodedecoratorFactory;

public class JaMoPP2PCMBaseTest {

    private static final Logger logger = Logger.getLogger(JaMoPP2PCMBaseTest.class.getSimpleName());

    protected static final String PROJECT_URI = "MockupProject";
    protected static final String CURRENT_TEST_FOLDER = "currentTest";
    protected static final String SOURCE_CODE_DECORATOR_MODEL_NAME = "sourcecodedecorator.sourcecodedecorator";
    protected static final String INTERFACE_A_NAME = "InterfaceA";
    protected static final String PROVIDING_INTERFACE_NAME = "ProvidingInterface";
    protected static final String PROVIDING_COMPONENT_NAME = "ProvidingComponent";
    protected static final String REQUIRED_COMPONENT_NAME = "RequiringComponent";

    protected static final String PCM_REPO_NAME = "testPCMModel.repository";

    protected static final String TEST_MODEL_PATH = "example-test-model/";
    protected static final String PCM_REPOITORY_URI = TEST_MODEL_PATH + PCM_REPO_NAME;

    protected static final String METHOD_NAME_TEST_EXTERNAL_CALL_WITH_SIMPLE_PARAMETER_AND_RETURN_TYPE = "testExternalCallWithSimpleParametersAndReturnType";

    protected static final String METHOD_NAME_TEST_EXTERNAL_CALL = "testExternalCall";

    protected static final String METHOD_NAME_PROVIDING_METHOD = "providingMethod";

    protected static Root compilationUnits;

    protected SourceCodeDecoratorRepository sourceCodeDecorator;
    protected Repository pcmRepository;
    protected ResourceSet resourceSet;

    protected class MethodFunctionCallClassificationVisitorPair {
        public MethodFunctionCallClassificationVisitorPair() {
        }

        public Method method;
        public FunctionCallClassificationVisitor functionCallClassificationVisitor;
    }

    @BeforeClass
    public static void beforeClass() throws IOException, CoreException {
        initCompilationUnits();
        initializeLogger();
    }

    @Before
    public void beforeTest() throws IOException {
        this.initPCMRepository();
        this.initializeSourceCodeDecorator();
    }

    void initPCMRepository() throws IOException {
        this.resourceSet = new ResourceSetImpl();
        final String uriString = PROJECT_URI + "/" + PCM_REPOITORY_URI;
        final String testURIStr = PROJECT_URI + "/" + CURRENT_TEST_FOLDER + "/" + PCM_REPO_NAME;
        final EObject eObject = this.getRootEObject(uriString, testURIStr);
        this.pcmRepository = (Repository) eObject;
    }

    protected EObject getRootEObject(final String uriString, final String testURIStr) throws IOException {
        final URI uri = URI.createPlatformResourceURI(uriString, true);
        final Resource resource = this.resourceSet.createResource(uri);
        resource.load(null);
        final URI testURI = URI.createPlatformResourceURI(testURIStr, true);
        final Resource resourceForTest = this.resourceSet.createResource(testURI);
        final EObject rootElement = resource.getContents().get(0);
        resourceForTest.getContents().add(EcoreUtil.copy(rootElement));
        resourceForTest.save(null);
        final EObject eObject = resourceForTest.getContents().get(0);
        return eObject;
    }

    @Rule
    public TestWatcher watchmen = new TestWatcher() {
        @Override
        protected void finished(final org.junit.runner.Description description) {
            JaMoPP2PCMBaseTest.this.afterTest();
            final String previousMethodName = description.getMethodName();
            try {
                JaMoPP2PCMBaseTest.this.pcmRepository.eResource().save(null);
            } catch (final IOException e) {
                logger.warn("Could not save PCM repository. Reason: " + e);
            }
            final String timestamp = new Date(System.currentTimeMillis()).toString().replace(" ", "_");
            moveFilesFromTo(PROJECT_URI, CURRENT_TEST_FOLDER, previousMethodName + "_" + timestamp);

        };
    };

    private OperationSignature findOperationSignatureWithName(final String methodName, final String interfaceName) {
        final OperationInterface opInterface = this.findOperationInterfaceWithName(interfaceName);
        for (final OperationSignature opSig : opInterface.getSignatures__OperationInterface()) {
            if (methodName.equals(opSig.getEntityName())) {
                return opSig;
            }
        }
        throw new RuntimeException(
                "Could not find OperationSignature " + methodName + " in OperationInterface " + interfaceName);
    }

    protected Method findMethodInClassifier(final String methodName, final String classifierName) {
        final ConcreteClassifier classifier = this.findConcreteClassifierWithName(classifierName);
        for (final Method method : classifier.getMethods()) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        throw new RuntimeException("Could not find Method " + methodName + " in Classifier " + classifierName);
    }

    private ComponentImplementingClassesLink createAndAddComponentImplementingClassLink(final String componentName,
            final InterfaceSourceCodeLink providedInterface, final InterfaceSourceCodeLink requiredInterfacs) {
        final ComponentImplementingClassesLink componentImplementingClassLink = SourcecodedecoratorFactory.eINSTANCE
                .createComponentImplementingClassesLink();
        componentImplementingClassLink.setComponent(this.findComponentInPCMRepo(componentName));
        componentImplementingClassLink.getImplementingClasses().add(this.findClassForComponent(componentName));
        if (null != providedInterface) {
            componentImplementingClassLink.getProvidedInterfaces().add(providedInterface);
        }
        if (null != requiredInterfacs) {
            componentImplementingClassLink.getRequiredInterfaces().add(requiredInterfacs);
        }
        this.sourceCodeDecorator.getComponentImplementingClassesLink().add(componentImplementingClassLink);
        return componentImplementingClassLink;
    }

    protected RepositoryComponent findComponentInPCMRepo(final String componentName) {
        for (final RepositoryComponent repoComp : this.pcmRepository.getComponents__Repository()) {
            if (componentName.equals(repoComp.getEntityName())) {
                return repoComp;
            }
        }
        throw new RuntimeException("Could not find RepositoryComponent " + componentName);
    }

    private ConcreteClassifier findClassForComponent(final String providingCompName) {
        final ConcreteClassifier classifier = this.findConcreteClassifierWithName(providingCompName + "Impl");
        return classifier;
    }

    private InterfaceSourceCodeLink createAndAddInterface2InterfaceCorrespondence(final String interfaceName) {
        final InterfaceSourceCodeLink interfaecLink = SourcecodedecoratorFactory.eINSTANCE
                .createInterfaceSourceCodeLink();
        interfaecLink.setGastClass(this.findConcreteClassifierWithName(interfaceName));
        interfaecLink.setInterface(this.findOperationInterfaceWithName(interfaceName));
        this.sourceCodeDecorator.getInterfaceSourceCodeLink().add(interfaecLink);
        return interfaecLink;
    }

    private OperationInterface findOperationInterfaceWithName(final String interfaceName) {
        for (final Interface opInterface : this.pcmRepository.getInterfaces__Repository()) {
            if (opInterface instanceof OperationInterface && interfaceName.equals(opInterface.getEntityName())) {
                return (OperationInterface) opInterface;
            }
        }
        throw new RuntimeException("Could not find OperationInterface " + interfaceName);
    }

    private ConcreteClassifier findConcreteClassifierWithName(final String concreteClassifierName) {
        for (final CompilationUnit cu : compilationUnits.getCompilationUnits()) {
            for (final ConcreteClassifier concreteClassifier : cu.getClassifiers()) {
                if (concreteClassifierName.equals(concreteClassifier.getName())) {
                    return concreteClassifier;
                }
            }
        }
        throw new RuntimeException("Could not find ConcreteClassifier " + concreteClassifierName);
    }

    /**
     * this method can be used by inherited classes to do something after the test
     */
    protected void afterTest() {

    }

    /**
     * Copied from:
     * http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method
     * Get the method name for the calling method of the getMethodMethod.
     *
     * @return method name
     */
    public static String getTestMethodName() {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[2].getMethodName();
    }

    private void initializeSourceCodeDecorator() throws IOException {
        this.sourceCodeDecorator = SourcecodedecoratorFactory.eINSTANCE.createSourceCodeDecoratorRepository();

        // interfaces2interfaces
        final InterfaceSourceCodeLink opInterfaceAToInterfaceA = this
                .createAndAddInterface2InterfaceCorrespondence(INTERFACE_A_NAME);
        final InterfaceSourceCodeLink providingOpInterfaceToProvidingInterface = this
                .createAndAddInterface2InterfaceCorrespondence(PROVIDING_INTERFACE_NAME);
        // method2method4interface
        this.createAndAddMethodLevelSourceCodeLink(METHOD_NAME_TEST_EXTERNAL_CALL_WITH_SIMPLE_PARAMETER_AND_RETURN_TYPE,
                null, INTERFACE_A_NAME);
        this.createAndAddMethodLevelSourceCodeLink(METHOD_NAME_TEST_EXTERNAL_CALL, null, INTERFACE_A_NAME);
        this.createAndAddMethodLevelSourceCodeLink(METHOD_NAME_PROVIDING_METHOD, null, PROVIDING_INTERFACE_NAME);

        // components2Class
        this.createAndAddComponentImplementingClassLink(PROVIDING_COMPONENT_NAME, opInterfaceAToInterfaceA, null);
        this.createAndAddComponentImplementingClassLink(REQUIRED_COMPONENT_NAME,
                providingOpInterfaceToProvidingInterface, opInterfaceAToInterfaceA);

        // methods2methods
        this.createAndAddMethodLevelSourceCodeLink(METHOD_NAME_TEST_EXTERNAL_CALL_WITH_SIMPLE_PARAMETER_AND_RETURN_TYPE,
                PROVIDING_COMPONENT_NAME, INTERFACE_A_NAME);
        this.createAndAddMethodLevelSourceCodeLink(METHOD_NAME_TEST_EXTERNAL_CALL, PROVIDING_COMPONENT_NAME,
                INTERFACE_A_NAME);
        this.createAndAddMethodLevelSourceCodeLink(METHOD_NAME_PROVIDING_METHOD, REQUIRED_COMPONENT_NAME,
                PROVIDING_INTERFACE_NAME);

        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI uri = URI.createPlatformResourceURI(
                PROJECT_URI + "/" + CURRENT_TEST_FOLDER + "/" + SOURCE_CODE_DECORATOR_MODEL_NAME, true);
        final Resource resource = resourceSet.createResource(uri);
        resource.getContents().add(this.sourceCodeDecorator);
        resource.save(null);
    }

    private static void initCompilationUnits() throws IOException, CoreException {
        final KDMReader kdmReader = new KDMReader();
        final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        final IProject project = root.getProject(PROJECT_URI);
        if (!project.exists()) {
            project.create(null);
            project.open(null);
        }
        kdmReader.loadProject(project.getName());
        compilationUnits = kdmReader.getRoot();
    }

    private MethodLevelSourceCodeLink createAndAddMethodLevelSourceCodeLink(final String methodName,
            final String componentName, final String interfaceName) {
        final MethodLevelSourceCodeLink methodLevelSourceCodeLink = SourcecodedecoratorFactory.eINSTANCE
                .createMethodLevelSourceCodeLink();
        final String classifierForMethod = null == componentName ? interfaceName : componentName + "Impl";
        final Method method = this.findMethodInClassifier(methodName, classifierForMethod);
        final OperationSignature opSignature = this.findOperationSignatureWithName(methodName, interfaceName);
        methodLevelSourceCodeLink.setFile(method.getContainingCompilationUnit());
        methodLevelSourceCodeLink.setFunction(method);
        methodLevelSourceCodeLink.setOperation(opSignature);
        if (null != componentName) {
            methodLevelSourceCodeLink.setRepositoryComponent(this.findComponentInPCMRepo(componentName));
        }
        this.sourceCodeDecorator.getMethodLevelSourceCodeLink().add(methodLevelSourceCodeLink);
        return methodLevelSourceCodeLink;
    }

    public static void assertBitSetsForType(final Map<Commentable, List<BitSet>> annotations,
            final Class<?> expectedClass, final FunctionCallType... expectedFunctionCallTypes) {
        for (final Entry<Commentable, List<BitSet>> annotation : annotations.entrySet()) {
            final Commentable commentable = annotation.getKey();
            if (expectedClass.isInstance(commentable)) {
                for (final BitSet bitSet : annotation.getValue()) {
                    assertBitSet(expectedFunctionCallTypes.length, bitSet, expectedFunctionCallTypes);
                }
            }
        }
    }

    protected static void assertBitSet(final int expectedBitSetCardinality, final BitSet bitSet,
            final FunctionCallType... expectedFunctionCallTypes) {
        // assert
        assertEquals("There should be exactly " + expectedBitSetCardinality + " element(s) set to true in the bit set",
                expectedBitSetCardinality, bitSet.cardinality());
        for (final FunctionCallType expectedFunctionCallType : expectedFunctionCallTypes) {
            final boolean isSet = bitSet.get(FunctionCallClassificationVisitor.getIndex(expectedFunctionCallType));
            assertTrue("Statement should be recognized as " + expectedFunctionCallType + " call action - Bit Set: "
                    + bitSet, isSet);
        }
    }

    /**
     * Moves files from one folder in the MockupProject to another one
     *
     * @param srcProjectName
     *            Name of the srcProject
     * @param srcPath
     *            srcPath in workspace
     * @param destinationPath
     *            destinationPath in test workspace
     * @throws URISyntaxException
     */
    private static void moveFilesFromTo(final String srcProjectName, final String srcPath,
            final String destinationPath) {
        final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        final IProject project = root.getProject(srcProjectName);
        final IResource member = project.findMember(srcPath);
        if (null == member) {
            logger.warn("Member ('" + srcPath + "') not found. Nothing to do in ‘moveCreatedFilesToPath‘");
            return;
        }
        final IPath destinationIPath = new Path(destinationPath);
        try {
            member.move(destinationIPath, true, new NullProgressMonitor());
        } catch (final CoreException e) {
            logger.warn("Could not move src folder do destination folder " + destinationIPath + ": " + e.getMessage());
        }
    }

    /**
     * init logger for test purposes
     */
    public static void initializeLogger() {
        if (!Logger.getRootLogger().getAllAppenders().hasMoreElements()) {
            final PatternLayout layout = new PatternLayout();
            final String conversionPattern = "[%p] %d %c %M - %m%n";
            layout.setConversionPattern(conversionPattern);
            final ConsoleAppender ca = new ConsoleAppender();
            ca.setWriter(new OutputStreamWriter(System.out));
            ca.setLayout(layout);
            Logger.getRootLogger().addAppender(ca);
            Logger.getRootLogger().setLevel(Level.ALL);

        }
    }

}
