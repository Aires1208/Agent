# 跟踪异步调用

所谓异步调用，是指创建任务和处理任务是在2个不同的线程上的调用。

如果要跟踪异步调用，那么需要：
    1.拦截创建异步任务的方法，并且赋予一个AsyncTraceId
    2.把AsyncTraceId传给异步任务的处理方法
    3.在异步任务的处理类中添加AsyncTraceIdAccessor字段
    4.拦截处理异步任务的方法，并且该拦截器需要继承SpanAsyncEventSimpleAroundInterceptor
