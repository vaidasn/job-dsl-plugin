package javaposse.jobdsl.plugin;

import hudson.ExtensionList;
import hudson.ExtensionPoint;
import javaposse.jobdsl.dsl.helpers.Context;
import jenkins.model.Jenkins;

public abstract class JobDslContextExtensionPoint implements ExtensionPoint {
    public static ExtensionList<JobDslContextExtensionPoint> all() {
          return Jenkins.getInstance().getExtensionList(JobDslContextExtensionPoint.class);
      }
}
