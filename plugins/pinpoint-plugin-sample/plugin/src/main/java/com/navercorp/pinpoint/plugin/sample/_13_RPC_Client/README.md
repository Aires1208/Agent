# 远程调用(RPC)客户端(发送端)

在远程调用的客户端，需要把跟踪信息附着在远程调用的数据中。

举例来说，在一次http请求中，A进程使用http协议请求B进程，则A进程是RPC客户端。

例1：在A进程中，如果拦截a1和a2方法，a1的拦截器是a1'，a2的拦截器是a2'，并且a1调用了a2，那么在跟踪的过程中，需要顺序记录的数据如下：

    a1'：
    - 创建new trace
    - 在span recorder中记录：
        - api
        - service type
        - end point
        - remote address
        - acceptor host
        - rpc name
        - 结果/异常
    - 关闭trace
    - 从trace context中移除当前trace

    a2'：
    - 记录span event开始
    - 在span event recorder中记录：
        - api
        - service type
        - end point
        - destination id
        - rpc name
        - next span id
        - 结果/异常
    - 在http message header中设置：
        - next transaction id
        - next span id
        - parent span id
        - parent application name
        - parent application type
    - 记录span event结束
        

例2：在A进程中，如果只拦截a方法，其拦截器是a'，那么在创建trace的过程中，需要顺序记录的内容如下：

    a':
    - 创建new trace
    - 在span recorder中记录：
        - api
        - service type
        - end point
        - remote address
        - acceptor host
        - rpc name
        - 结果/异常
    - 记录1个span event开始
    - 在span event recorder中记录：
        - api
        - service type
        - end point
        - destination id
        - rpc name
        - next span id
        - 结果/异常
    - 在http message header中设置：
        - next transaction id
        - next span id
        - parent span id
        - parent application name
        - parent application type
    - 记录span event结束
    - 关闭trace
    - 从trace context中移除当前trace

在RPC客户端侧必须记录至少1个span event，因为只有span event recorder才会记录transaction id和next span id，这样RPC服务端的拦截器产生的记录就才能和RPC客户端的记录根据transaction id和next span id对应构成一个完整的调用链。

