# 使用方法过滤器来设置多个方法的拦截器

   如果需要给一个类中的多个方法添加同一个拦截器，那么可以使用方法过滤器来获得一组方法，不再需要指定每一个方法的名称和参数列表来获取目标方法的InstrumentMethod。
   例如，一个类中存在多个名称为`recordMe`，但是参数列表不同的方法，使用方法过滤器来设置目标方法的拦截器可以如下操作：

```
   for (InstrumentMethod method : instrumentClass.getDeclaredMethods(MethodFilters.name("recordMe"))) {
       method.addInterceptor("com.navercorp.pinpoint.bootstrap.interceptor.BasicMethodInterceptor",
           va(SamplePluginConstants.MY_SERVICE_TYPE));
   }
```