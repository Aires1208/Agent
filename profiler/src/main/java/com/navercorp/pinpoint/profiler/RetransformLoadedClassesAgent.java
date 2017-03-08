/*
 * Copyright 2014 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler;

import com.navercorp.pinpoint.bootstrap.Agent;
import com.navercorp.pinpoint.bootstrap.AgentOption;
import com.navercorp.pinpoint.bootstrap.config.ProfilerConfig;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.profiler.instrument.transformer.TransformerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @author SunWenlin
 */
public class RetransformLoadedClassesAgent implements Agent {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DefaultAgent defaultAgent;

    public RetransformLoadedClassesAgent(AgentOption agentOption) {
        defaultAgent = new DefaultAgent(agentOption);
        retransformLoadedClasses(defaultAgent);
    }

    private void retransformLoadedClasses(DefaultAgent defaultAgent) {
        Instrumentation instrumentation = defaultAgent.getInstrumentation();
        TransformerRegistry transformerRegistry = defaultAgent.getClassFileTransformerDispatcher().getTransformerRegistry();
        for (Class loadedClass : instrumentation.getAllLoadedClasses()) {
            String jvmClassName = loadedClass.getName().replaceAll("\\.", "/");
            ClassFileTransformer transformer = transformerRegistry.findTransformer(jvmClassName);
            if (transformer == null) {
                continue;
            }
            try {
                instrumentation.retransformClasses(loadedClass);
                logger.info("retransformed: " + loadedClass);
            } catch (Exception e) {
                logger.error("retransform class {} failed. Caused by {}", loadedClass, e.toString());
            }
        }
    }

    @Override
    public void start() {
        defaultAgent.start();
    }

    @Override
    public void stop() {
        defaultAgent.stop();
    }

    @Override
    public TraceContext getTraceContext() {
        return defaultAgent.getTraceContext();
    }

    @Override
    public ProfilerConfig getProfilerConfig() {
        return defaultAgent.getProfilerConfig();
    }
}
