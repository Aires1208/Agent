# 使用拦截器Scope以避免重复跟踪

这种情况比较普遍，例如有方法A和方法B，方法A中调用方法B，这两个方法又都可以被独立调用。即可能产生的调用路径有：

- 外部->A->B
- 外部->B

如果对A和B都注入拦截器，那么在上述嵌套调用时，会产生重复跟踪的情况。
这时一来拦截器不容易编码，可能需要考虑嵌套跟踪的干扰，二来重复跟踪是不必要的。

为了避免这种情况，可以使用Scope拦截器。

使用InterceptorScope来指定一个Scope，使用ExecutionPolicy来指定一种策略。

ExecutionPolicy有3种策略：

- ALWAYS: 始终执行
- BOUNDARY: 一个Scope下，只能有一个拦截器在执行
- INTERNAL: 必须在另一个相同Scope的拦截器在执行时，才执行

其中，默认的策略是BOUNDARY，即不能嵌套拦截。