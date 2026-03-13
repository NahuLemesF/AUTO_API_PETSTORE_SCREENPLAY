package org.nahulemes.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.SNIPPET_TYPE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/consulta.feature")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "org.nahulemes.stepdefinitions,org.nahulemes.stepdefinitions.hooks"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/test-results/timeline"
)
@ConfigurationParameter(
        key = SNIPPET_TYPE_PROPERTY_NAME,
        value = "camelcase"
)
public class UserLifecycleRunner {
}
