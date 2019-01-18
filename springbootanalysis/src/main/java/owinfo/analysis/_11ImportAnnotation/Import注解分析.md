# IOC分析

- Import使用
- Import何时被扫描和解析的
- configuration、component、service、controller等等何时被解析的
- BeanFactoryProcessor后置处理器源码走查
- BeanProcessor对象后置处理
- 实例初始化、前中后、对象复制源码走查
- Schedule源码分析
**总结:其实大多数都是运用Processor机制，对bean进行加工处理。比如Enable系列的EnableAspectJAutoProxy、EnableScheduling**
