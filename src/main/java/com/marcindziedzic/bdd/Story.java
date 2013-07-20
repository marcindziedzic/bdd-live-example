package com.marcindziedzic.bdd;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterConverters;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.jbehave.core.reporters.Format.*;

public abstract class Story extends JUnitStory {

    private final CrossReference xref = new CrossReference();

    public Story() {
        configuredEmbedder()
                .embedderControls()
                .doGenerateViewAfterStories(true)
                .doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true)
                .useThreads(1)
                .useStoryTimeoutInSecs(60);
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
        // Start from default ParameterConverters instance
        ParameterConverters parameterConverters = new ParameterConverters();
        // factory to allow parameter conversion and loading from external
        // resources (used by StoryParser too)
        LocalizedKeywords keywords = getKeywords();

        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(keywords,
                new LoadFromClasspath(embeddableClass), parameterConverters);
        // add custom converters
        parameterConverters.addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ParameterConverters.ExamplesTableConverter(examplesTableFactory));

        return new MostUsefulConfiguration()
                .useKeywords(keywords)
                .useStoryParser(new RegexStoryParser(keywords))
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStepCollector(new MarkUnmatchedStepsAsPending(keywords))
                .useStoryParser(new RegexStoryParser(keywords, examplesTableFactory))
                .useStoryPathResolver(new UnderscoredCamelCaseResolver(getStoryExtension()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withDefaultFormats().withPathResolver(new FilePrintStreamFactory.ResolveToPackagedName())
                                .withViewResources(viewResources).withFormats(CONSOLE, TXT, HTML, XML)
                                .withFailureTrace(true).withFailureTraceCompression(true).withCrossReference(xref))
                .useParameterConverters(parameterConverters)
                        // use '%' instead of '$' to identify parameters
                .useStepPatternParser(new RegexPrefixCapturingPatternParser("$"))
                .useStepMonitor(xref.getStepMonitor());

    }

    public LocalizedKeywords getKeywords() {
        return new LocalizedKeywords();
    }

    public String getStoryExtension() {
        return ".story";
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), getSteps());
    }

    public List<Object> getSteps() {
        LinkedList<Object> steps = new LinkedList<Object>();
        steps.add(this);
        return steps;
    }

}
