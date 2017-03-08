# 拦截构造方法

和拦截普通方法一样，首先获取目标类的InstrumentClass，然后再获取目标方法的InstrumentMethod。

对于构造方法，获取其InstrumentMethod时，使用`InstrumentMethod targetConstructor = instrumentClass.getConstructor("java.lang.String");`