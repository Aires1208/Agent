# 使用注解设置过滤器的目标方法

获取到目标类的InstrumentClass后，可以不需要再获取目标方法的InstrumentMethod后再添加拦截器。
拦截器的目标方法和Scope可以使用标注在过滤器类上的注解来指定。
