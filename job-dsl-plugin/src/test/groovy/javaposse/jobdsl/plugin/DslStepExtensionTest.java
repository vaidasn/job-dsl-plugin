package javaposse.jobdsl.plugin;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.TopLevelItem;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import static hudson.model.Result.SUCCESS;
import static javaposse.jobdsl.plugin.RemovedJobAction.IGNORE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DslStepExtensionTest {
    @Rule
    public JenkinsRule jenkinsRule = new JenkinsRule();

    @Test
    public void testAll() {
        boolean found = false;
        for (JobDslContextExtensionPoint jobDslContextExtensionPoint : JobDslContextExtensionPoint.all()) {
            if (jobDslContextExtensionPoint instanceof DslStepExtension) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void test() throws Exception {
        // setup
        FreeStyleProject job = jenkinsRule.createFreeStyleProject("seed");
        job.getBuildersList().add(new ExecuteDslScripts(new ExecuteDslScripts.ScriptLocation("true", null, SCRIPT), true, IGNORE));

        // when
        FreeStyleBuild freeStyleBuild = job.scheduleBuild2(0).get();

        // then
        assertEquals(SUCCESS, freeStyleBuild.getResult());
        TopLevelItem testJob = jenkinsRule.getInstance().getItem("test-job");
        assertTrue(testJob instanceof FreeStyleProject);

        // when
        FreeStyleBuild testJobBuild = ((FreeStyleProject) testJob).scheduleBuild2(0).get();

        // then
        assertEquals(SUCCESS, testJobBuild.getResult());
        assertTrue(jenkinsRule.getInstance().getItem("foo") instanceof FreeStyleProject);
    }

    private static final String SCRIPT = "" +
            "job {\n" +
            "  name('test-job')\n" +
            "  steps {\n" +
            "    dsl2('job { name(\"foo\") }')\n" +
            "  }\n" +
            "}";
}
