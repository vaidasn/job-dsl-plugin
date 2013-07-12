package javaposse.jobdsl.dsl;

import groovy.util.Node;

public interface ContextExtension {
    String getMethodName();

    WithXmlAction execute(Node node, Object... args);
}
