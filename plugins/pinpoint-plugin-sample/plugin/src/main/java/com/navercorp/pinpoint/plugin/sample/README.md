# pinpoint plugin示例

## service声明
  - /META-INF/services/com.navercorp.pinpoint.bootstrap.plugin.ProfilerPlugin
内容：com.navercorp.pinpoint.plugin.sample.SamplePlugin _// 插件的类名，实现ProfilerPlugin, TransformTemplateAware接口_
  - META-INF/services/com.navercorp.pinpoint.common.trace.TraceMetadataProvider
内容：com.navercorp.pinpoint.plugin.sample.SampleTraceMetadataProvider _// 实现TraceMetadataProvider接口_

## 插件实现
以SamplePlugin为例:

  - 设置插件应用类型探测器，这决定了该插件生效时，该应用是什么类型的，例如是tomcat、jboss等
ProfilerPluginSetupContext.addApplicationTypeDetector(ApplicationTypeDetector);
  - 设置目标类和对应的拦截器
TransformTemplate.transform("被拦截的类名", new TransformCallback());
  - 给目标类和目标方法添加拦截器，需要注意，如果1个目标类中有多个方法需要添加拦截器，需要在同一个InstrumentClass添加


```

public class MyInterceptor implements TransformCallback {

    @Override
    public byte[] doInTransform(Instrumentor instrumentor, ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws InstrumentException {

        // 1. 获取目标类的InstrumentClass
        InstrumentClass target = instrumentor.getInstrumentClass(classLoader, className, classfileBuffer);

        // 2. 从目标类获取目标方法的InstrumentMethod
        InstrumentMethod targetMethod = target.getDeclaredMethod("targetMethod", "java.lang.String");

        // 3. 在目标方法上添加拦截器，第1个参数是拦截器类的全限定名，如果拦截器类构造方法需要参数，则参数紧随其后。
        targetMethod.addInterceptor("com.navercorp.pinpoint.bootstrap.interceptor.BasicMethodInterceptor",
            va(SamplePluginConstants.MY_SERVICE_TYPE));

        // 4. 返回目标类的字节码
        return target.toBytecode();
    }
}

```