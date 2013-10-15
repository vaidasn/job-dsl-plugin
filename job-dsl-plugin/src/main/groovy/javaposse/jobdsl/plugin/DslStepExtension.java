package javaposse.jobdsl.plugin;

import hudson.Extension;
import javaposse.jobdsl.dsl.helpers.Context;
import javaposse.jobdsl.dsl.helpers.StepContext;
import jenkins.YesNoMaybe;
import org.apache.commons.collections.Closure;

import java.util.Collection;

import static javaposse.jobdsl.plugin.RemovedJobAction.IGNORE;

@Extension
public class DslStepExtension extends JobDslContextExtensionPoint {
    @Override
    public Class<? extends Context> getContextType() {
        return StepContext.class;
    }

    @Override
    public String getMethodName() {
        return "dsl2";
    }

    @Override
    public Object execute(Object... args) {
        String scriptText = "";
        String targets = null;
        RemovedJobAction removedJobAction = IGNORE;
        boolean ignoreExisting = false;

        if (args.length == 0) {
            return null;
        }
        if (args[0] instanceof Closure) {
            if (args.length > 1) {
                return null;
            }
            // TODO
            throw new UnsupportedOperationException();
        } else if (args[0] instanceof String) {
            scriptText = (String) args[0];
        } else if (args[0] instanceof Collection) {
            // TODO
        }
        if (args.length > 1) {
            // TODO
        }

        ExecuteDslScripts.ScriptLocation scriptLocation = new ExecuteDslScripts.ScriptLocation(Boolean.toString(!scriptText.isEmpty()), targets, scriptText);
        return new ExecuteDslScripts(scriptLocation, ignoreExisting, removedJobAction);
    }
}
