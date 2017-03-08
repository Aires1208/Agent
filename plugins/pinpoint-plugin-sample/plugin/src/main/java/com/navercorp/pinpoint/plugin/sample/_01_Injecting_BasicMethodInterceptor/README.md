# 基础方法拦截

pinpoint内置的方法拦截器是`com.navercorp.pinpoint.bootstrap.interceptor.BasicMethodInterceptor`

这个拦截器**不能在RPC边界的方法中使用**，例如http client, http server。
这个拦截器的作用是在已有的Span中添加一个Span Event。