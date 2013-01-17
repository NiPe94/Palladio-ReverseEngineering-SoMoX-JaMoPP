/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.qimpress.sourcecodedecorator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.Type;

import eu.qimpress.samm.staticstructure.ComponentType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Implementing Classes Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isCompositeComponent <em>Is Composite Component</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getComponent <em>Component</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getImplementingClasses <em>Implementing Classes</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getSubComponents <em>Sub Components</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isInitialComponent <em>Is Initial Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink()
 * @model
 * @generated
 */
public interface ComponentImplementingClassesLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Implementing Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gmt.modisco.java.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementing Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementing Classes</em>' reference list.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink_ImplementingClasses()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<Type> getImplementingClasses();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(ComponentType)
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink_Component()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ComponentType getComponent();

	/**
	 * Sets the value of the '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(ComponentType value);

	/**
	 * Returns the value of the '<em><b>Is Composite Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Composite Component</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Composite Component</em>' attribute.
	 * @see #isSetIsCompositeComponent()
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink_IsCompositeComponent()
	 * @model unique="false" unsettable="true" required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	boolean isCompositeComponent();

	/**
	 * Returns whether the value of the '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isCompositeComponent <em>Is Composite Component</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Composite Component</em>' attribute is set.
	 * @see #isCompositeComponent()
	 * @generated
	 */
	boolean isSetIsCompositeComponent();

	/**
	 * Returns the value of the '<em><b>Sub Components</b></em>' reference list.
	 * The list contents are of type {@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Components</em>' reference list.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink_SubComponents()
	 * @model ordered="false"
	 * @generated
	 */
	EList<ComponentImplementingClassesLink> getSubComponents();

	/**
	 * Returns the value of the '<em><b>Provided Interfaces</b></em>' reference list.
	 * The list contents are of type {@link eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provided Interfaces</em>' reference list.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink_ProvidedInterfaces()
	 * @model ordered="false"
	 * @generated
	 */
	EList<InterfaceSourceCodeLink> getProvidedInterfaces();

	/**
	 * Returns the value of the '<em><b>Required Interfaces</b></em>' reference list.
	 * The list contents are of type {@link eu.qimpress.sourcecodedecorator.InterfaceSourceCodeLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Interfaces</em>' reference list.
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink_RequiredInterfaces()
	 * @model ordered="false"
	 * @generated
	 */
	EList<InterfaceSourceCodeLink> getRequiredInterfaces();

	/**
	 * Returns the value of the '<em><b>Is Initial Component</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If set to true: This component&nbsp;results from a class-level. Thus, it represents a very low-level component. Required
	 * for Reverse Engineering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Initial Component</em>' attribute.
	 * @see #setIsInitialComponent(boolean)
	 * @see eu.qimpress.sourcecodedecorator.SourceCodeDecoratorPackage#getComponentImplementingClassesLink_IsInitialComponent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isInitialComponent();

	/**
	 * Sets the value of the '{@link eu.qimpress.sourcecodedecorator.ComponentImplementingClassesLink#isInitialComponent <em>Is Initial Component</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Initial Component</em>' attribute.
	 * @see #isInitialComponent()
	 * @generated
	 */
	void setIsInitialComponent(boolean value);


} // ComponentImplementingClassesLink