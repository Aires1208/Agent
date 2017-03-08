# 自定义拦截

可以自定义拦截器，只需要实现`AroundInterceptor`或者`AroundInterceptor1`, `AroundInterceptor2`...

这些拦截器接口的区别主要在于被拦截方法的参数的个数：

- AroundInterceptor: 接受被拦截方法不定个数的参数
- AroundInterceptor1: 接受1个被拦截方法的参数
- AroundInterceptor2: 接受2个被拦截方法的参数
- 余此类推

