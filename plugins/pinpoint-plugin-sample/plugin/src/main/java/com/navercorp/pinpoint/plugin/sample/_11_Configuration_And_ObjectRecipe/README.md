# 在插件中获取pinpoint参数

`ProfilerConfig config = instrumentor.getProfilerConfig();`

# 在插件中定义类，并传递给拦截器

拦截器是在目标类加载时加载的，拦截器的class loader是目标类的class loader。插件是pinpoint加载的，其class loader是pinpoint的plugin class loader。
如果在插件中定义一个类X，并且拦截器需要使用X，如果以普通的方法将X传递给拦截器的话，拦截器在加载时会找不到X类的定义。
所以，需要使用ObjectFactory来完成上述工作。
同理，应该避免使用在插件中定义的类型的静态变量来共享数据。
