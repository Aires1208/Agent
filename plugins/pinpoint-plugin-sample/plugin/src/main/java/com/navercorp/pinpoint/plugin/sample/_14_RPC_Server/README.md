# 远程调用(RPC)服务端(接收端)

在远程调用的服务端，需要把附着在远程调用的数据中的跟踪信息获取出来并构造一个新的跟踪信息。

举例来说，在一次http响应中，B进程使用http协议响应请求，则B进程是RPC服务端。

例：在B进程中，如果拦截b方法，其拦截器是b'，那么在跟踪的过程中，需要顺序记录的数据如下：

    b'：
    - 从http message header中取出：
        - next transaction id
        - next span id
        - parent span id
        - parent application name
        - parent application type
    - 根据上述信息创建continue trace
    - 在span recorder中记录：
        - service type
        - rpc name
        - end point
        - acceptor host
        - remote address
        - parent application(name & type)
    - 关闭trace
    - 从trace context中移除当前trace

如果需要拦截其它b中调用的方法，则对应的拦截器中只需要获取span event recorder，并记录相关信息。
