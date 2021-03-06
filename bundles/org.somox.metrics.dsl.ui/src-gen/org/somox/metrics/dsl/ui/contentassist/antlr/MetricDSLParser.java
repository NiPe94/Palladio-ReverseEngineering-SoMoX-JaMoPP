/*
* generated by Xtext
*/
package org.somox.metrics.dsl.ui.contentassist.antlr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.somox.metrics.dsl.services.MetricDSLGrammarAccess;

import com.google.inject.Inject;

public class MetricDSLParser extends AbstractContentAssistParser {

    @Inject
    private MetricDSLGrammarAccess grammarAccess;

    private Map<AbstractElement, String> nameMappings;

    @Override
    protected org.somox.metrics.dsl.ui.contentassist.antlr.internal.InternalMetricDSLParser createParser() {
        final org.somox.metrics.dsl.ui.contentassist.antlr.internal.InternalMetricDSLParser result = new org.somox.metrics.dsl.ui.contentassist.antlr.internal.InternalMetricDSLParser(
                null);
        result.setGrammarAccess(this.grammarAccess);
        return result;
    }

    @Override
    protected String getRuleName(final AbstractElement element) {
        if (this.nameMappings == null) {
            this.nameMappings = new HashMap<AbstractElement, String>() {
                private static final long serialVersionUID = 1L;

                {
                    this.put(MetricDSLParser.this.grammarAccess.getMetricAccess().getAlternatives(),
                            "rule__Metric__Alternatives");
                    this.put(MetricDSLParser.this.grammarAccess.getNumberAccess().getAlternatives(),
                            "rule__Number__Alternatives");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricDefinitionAccess().getAlternatives(),
                            "rule__MetricDefinition__Alternatives");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricModelAccess().getGroup(),
                            "rule__MetricModel__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricModelAccess().getGroup_0(),
                            "rule__MetricModel__Group_0__0");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricModelAccess().getGroup_1(),
                            "rule__MetricModel__Group_1__0");
                    this.put(MetricDSLParser.this.grammarAccess.getInternalMetricAccess().getGroup(),
                            "rule__InternalMetric__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getInternalMetricAccess().getGroup_8(),
                            "rule__InternalMetric__Group_8__0");
                    this.put(MetricDSLParser.this.grammarAccess.getParameterAccess().getGroup(),
                            "rule__Parameter__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getConstantAccess().getGroup(),
                            "rule__Constant__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getWeightedMetricAccess().getGroup(),
                            "rule__WeightedMetric__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getStepwiseMetricAccess().getGroup(),
                            "rule__StepwiseMetric__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getRatioMetricAccess().getGroup(),
                            "rule__RatioMetric__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getBoundAndWeightAccess().getGroup(),
                            "rule__BoundAndWeight__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricAndWeightAccess().getGroup(),
                            "rule__MetricAndWeight__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getMYIDAccess().getGroup(), "rule__MYID__Group__0");
                    this.put(MetricDSLParser.this.grammarAccess.getMYIDAccess().getGroup_1(), "rule__MYID__Group_1__0");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricModelAccess().getImportURIAssignment_0_1(),
                            "rule__MetricModel__ImportURIAssignment_0_1");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricModelAccess().getMetricsAssignment_1_1(),
                            "rule__MetricModel__MetricsAssignment_1_1");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricModelAccess().getMetricsAssignment_4(),
                            "rule__MetricModel__MetricsAssignment_4");
                    this.put(MetricDSLParser.this.grammarAccess.getExternalMetricAccess().getNameAssignment(),
                            "rule__ExternalMetric__NameAssignment");
                    this.put(MetricDSLParser.this.grammarAccess.getInternalMetricAccess().getNameAssignment_1(),
                            "rule__InternalMetric__NameAssignment_1");
                    this.put(MetricDSLParser.this.grammarAccess.getInternalMetricAccess().getShortNameAssignment_3(),
                            "rule__InternalMetric__ShortNameAssignment_3");
                    this.put(MetricDSLParser.this.grammarAccess.getInternalMetricAccess().getDescriptionAssignment_5(),
                            "rule__InternalMetric__DescriptionAssignment_5");
                    this.put(MetricDSLParser.this.grammarAccess.getInternalMetricAccess().getParameterAssignment_8_2(),
                            "rule__InternalMetric__ParameterAssignment_8_2");
                    this.put(MetricDSLParser.this.grammarAccess.getInternalMetricAccess().getDefinitionAssignment_9(),
                            "rule__InternalMetric__DefinitionAssignment_9");
                    this.put(MetricDSLParser.this.grammarAccess.getParameterAccess().getNameAssignment_1(),
                            "rule__Parameter__NameAssignment_1");
                    this.put(MetricDSLParser.this.grammarAccess.getParameterAccess().getShortnameAssignment_3(),
                            "rule__Parameter__ShortnameAssignment_3");
                    this.put(MetricDSLParser.this.grammarAccess.getParameterAccess().getDescriptionAssignment_5(),
                            "rule__Parameter__DescriptionAssignment_5");
                    this.put(MetricDSLParser.this.grammarAccess.getParameterAccess().getDefaultValueAssignment_7(),
                            "rule__Parameter__DefaultValueAssignment_7");
                    this.put(MetricDSLParser.this.grammarAccess.getConstantAccess().getNameAssignment_1(),
                            "rule__Constant__NameAssignment_1");
                    this.put(MetricDSLParser.this.grammarAccess.getConstantAccess().getValueAssignment_3(),
                            "rule__Constant__ValueAssignment_3");
                    this.put(MetricDSLParser.this.grammarAccess.getWeightedMetricAccess().getWeightsAssignment_2(),
                            "rule__WeightedMetric__WeightsAssignment_2");
                    this.put(MetricDSLParser.this.grammarAccess.getStepwiseMetricAccess().getInnerMetricAssignment_2(),
                            "rule__StepwiseMetric__InnerMetricAssignment_2");
                    this.put(MetricDSLParser.this.grammarAccess.getStepwiseMetricAccess().getStepsAssignment_5(),
                            "rule__StepwiseMetric__StepsAssignment_5");
                    this.put(MetricDSLParser.this.grammarAccess.getRatioMetricAccess().getNominatorMetricAssignment_2(),
                            "rule__RatioMetric__NominatorMetricAssignment_2");
                    this.put(
                            MetricDSLParser.this.grammarAccess.getRatioMetricAccess()
                                    .getDenominatorMetricAssignment_4(),
                            "rule__RatioMetric__DenominatorMetricAssignment_4");
                    this.put(MetricDSLParser.this.grammarAccess.getBoundAndWeightAccess().getUpperBoundAssignment_1(),
                            "rule__BoundAndWeight__UpperBoundAssignment_1");
                    this.put(MetricDSLParser.this.grammarAccess.getBoundAndWeightAccess().getWeightAssignment_3(),
                            "rule__BoundAndWeight__WeightAssignment_3");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricAndWeightAccess().getMetricAssignment_1(),
                            "rule__MetricAndWeight__MetricAssignment_1");
                    this.put(MetricDSLParser.this.grammarAccess.getMetricAndWeightAccess().getWeightAssignment_3(),
                            "rule__MetricAndWeight__WeightAssignment_3");
                }
            };
        }
        return this.nameMappings.get(element);
    }

    @Override
    protected Collection<FollowElement> getFollowElements(final AbstractInternalContentAssistParser parser) {
        try {
            final org.somox.metrics.dsl.ui.contentassist.antlr.internal.InternalMetricDSLParser typedParser = (org.somox.metrics.dsl.ui.contentassist.antlr.internal.InternalMetricDSLParser) parser;
            typedParser.entryRuleMetricModel();
            return typedParser.getFollowElements();
        } catch (final RecognitionException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected String[] getInitialHiddenTokens() {
        return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
    }

    public MetricDSLGrammarAccess getGrammarAccess() {
        return this.grammarAccess;
    }

    public void setGrammarAccess(final MetricDSLGrammarAccess grammarAccess) {
        this.grammarAccess = grammarAccess;
    }
}
