package owinfo.analysis._6ReflectGeneric.GenericTest;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *  Springboot ResolvableType方法测试
 *  ResolvableType可解决的类型(对Type接口和子接口进行类型封装和统一管理)
 */
public class ResolvableTest {

    public static void main(String[] args) {

        List<String> parameterType = new ArrayList<>();

        ResolvableType resolvableType = ResolvableType.forClass(parameterType.getClass());
        System.out.println(resolvableType);

        Type type = resolvableType.getGenerics()[0].getType();
        Class<?> rawClass = resolvableType.getRawClass();
        System.out.println(type);
        System.out.println(rawClass);
    }
}
