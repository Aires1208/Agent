package com.zte.apm.plugin.mock;

import com.navercorp.pinpoint.bootstrap.context.MethodDescriptor;

/**
 * Created by 10116285 on 16-6-20.
 */
public class ApmMethodDescriptor implements MethodDescriptor {
    private final String className;
    private final String methodName;
    private final String[] parameterTypes;

    public ApmMethodDescriptor(String className, String methodName, String... parameterTypes) {
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public String[] getParameterTypes() {
        return this.parameterTypes;
    }

    @Override
    public String[] getParameterVariableName() {
        return new String[0];
    }

    @Override
    public String getParameterDescriptor() {
        return null;
    }

    @Override
    public int getLineNumber() {
        return 0;
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public void setApiId(int apiId) {

    }

    @Override
    public int getApiId() {
        return 0;
    }

    @Override
    public String getApiDescriptor() {
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }
}
