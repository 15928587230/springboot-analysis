package owinfo.analysis._7DecotatorPattern;

import org.springframework.core.ResolvableType;

/**
 *  需要被装饰的抽象接口
 */
public interface Component<T> {

    /**
     * 获取父类接口
     * @return
     */
    ResolvableType[] resolveGenericInterfaces();

    /**
     * 获取父类
     * @return
     */
    ResolvableType resolveGenericSuperclass();
}
