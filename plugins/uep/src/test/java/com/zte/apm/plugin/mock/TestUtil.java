package com.zte.apm.plugin.mock;

import com.navercorp.pinpoint.bootstrap.interceptor.annotation.Scope;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethod;
import com.navercorp.pinpoint.bootstrap.interceptor.annotation.TargetMethods;
import com.navercorp.pinpoint.bootstrap.interceptor.scope.ExecutionPolicy;
import com.zte.ums.api.usf.bsf.system.Path;
import com.zte.ums.uep.api.pfl.emb.EMessage;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by 10116285 on 16-6-27.
 */
public class TestUtil {

    public static EMessage makeEMessage(int code, String sourcePath, String destPath) {
        ApmEMessage eMessage = new ApmEMessage(code);
        eMessage.setSourcePath(new Path(null, null, null, null, sourcePath));
        eMessage.setDestinationPath(new Path(null, null, null, null, destPath));
        return eMessage;
    }

    public static void assertScope(Class targetClass, String value, ExecutionPolicy executionPolicy) {
        Scope scope = null;
        Annotation[] annotations = targetClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Scope.class) {
                scope = (Scope)annotation;
            }
        }
        if (scope == null) {
            fail();
        }
        assertEquals(value, scope.value());
        if (executionPolicy != null) {
            assertEquals(executionPolicy, scope.executionPolicy());
        }
    }

    public static void assertTargetMethod(Class targetClass, String name, String... paramTypes) {
        TargetMethods targetMethods = null;
        Annotation[] annotations = targetClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == TargetMethods.class) {
                targetMethods = (TargetMethods)annotation;
            }
        }

        for (TargetMethod targetMethod : targetMethods.value()) {
            String targetMethodName = targetMethod.name();
            String[] targetMethodParamTypes = targetMethod.paramTypes();
            if (targetMethodName.equals(name) && arrayEquals(targetMethodParamTypes, paramTypes)) {
                return;
            }
        }
        fail();
    }

    private static boolean arrayEquals(String[] targetMethodParamTypes, String[] paramTypes) {
        if (targetMethodParamTypes == null && paramTypes == null) {
            return true;
        }
        if (targetMethodParamTypes == null) {
            return false;
        }
        if (paramTypes == null) {
            return false;
        }
        if (targetMethodParamTypes.length != paramTypes.length) {
            return false;
        }
        int index = 0;
        for (int i = 0; i < targetMethodParamTypes.length; i++) {
            if (targetMethodParamTypes[index] == null && paramTypes[index]==null) {
                continue;
            }
            if (targetMethodParamTypes[index] == null) {
                return false;
            }
            if (paramTypes[index]==null) {
                return false;
            }
            if (!targetMethodParamTypes[index].equals(paramTypes[index])) {
                return false;
            }
        }
        return true;
    }

}
