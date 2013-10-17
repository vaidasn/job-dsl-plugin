package javaposse.jobdsl.plugin;

import hudson.Extension;
import javaposse.jobdsl.dsl.helpers.StepContext;

import java.util.Collection;

import static javaposse.jobdsl.plugin.ExecuteDslScripts.ScriptLocation;
import static javaposse.jobdsl.plugin.RemovedJobAction.IGNORE;
import static org.apache.commons.lang.StringUtils.join;

@Extension
public class DslStepExtension extends JobDslContextExtensionPoint {
    @DslMethod(context = StepContext.class)
    public Object dsl2(String scriptText) {
        return dsl2(scriptText, IGNORE.name(), false);
    }

    @DslMethod(context = StepContext.class)
    public Object dsl2(String scriptText, String removedJobAction) {
        return dsl2(scriptText, removedJobAction, false);
    }

    @DslMethod(context = StepContext.class)
    public Object dsl2(String scriptText, String removedJobAction, boolean ignoreExisting) {
        ScriptLocation scriptLocation = new ScriptLocation("true", null, scriptText);
        return new ExecuteDslScripts(scriptLocation, ignoreExisting, RemovedJobAction.valueOf(removedJobAction));
    }

    @DslMethod(context = StepContext.class)
    public Object dsl2(Collection<String> externalScripts) {
        return dsl2(externalScripts, IGNORE.name(), false);
    }

    @DslMethod(context = StepContext.class)
    public Object dsl2(Collection<String> externalScripts, String removedJobAction) {
        return dsl2(externalScripts, removedJobAction, false);
    }

    @DslMethod(context = StepContext.class)
    public Object dsl2(Collection<String> externalScripts, String removedJobAction, boolean ignoreExisting) {
        ScriptLocation scriptLocation = new ScriptLocation("false", join(externalScripts, "\n"), null);
        return new ExecuteDslScripts(scriptLocation, ignoreExisting, RemovedJobAction.valueOf(removedJobAction));
    }
}
