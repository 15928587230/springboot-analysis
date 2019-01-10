package owinfo.analysis._6ReflectGeneric.GenericTest;

import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;
import owinfo.analysis._6ReflectGeneric.ReflectTest;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Springboot ResolvableType源码分析
 */
public class ResolvableTest<T extends ResolvableType, D extends ReflectTest> {

    private static ResolvableType[] resolvableTypes = new ResolvableType[0];

    public static void main(String[] args) {


        /**
         *  as方法测试，采用递归的方法，转成父类或者父接口
         *  本次转换通过父接口父类实现，即关联Class类型。并未使用其他类型
         */
        ResolvableType resolvableType = as(ResolvableType.forClass(ArrayList.class), List.class);
        System.out.println(resolvableType);


        /**
         *  Generics相关方法(泛型相关)
         */
        ResolvableType resolvableType1 = ResolvableType.forClass(ResolvableTest.class);
        ResolvableType[] generics = resolvableType1.getGenerics();
        for (ResolvableType type: generics) {
            System.out.println(type);
        }

        /**
         *  自定义获取泛型的ResolvableType[]数组
         */
        ResolvableType[] resolvableTypes = resolveGenerics(resolvableType1);
        for (ResolvableType type: resolvableTypes) {
            System.out.println(type);
        }
    }


    /**
     * Class类型只会存在类型变量
     * ParameterizedType 存在参数泛型中 包括通配符类型和类型变量
     * TypeVariable 类或者参数中(如方法参数或者成员变量中)
     * WildCard 参数泛型的参数中
     * @param type
     * @return
     */
    public static ResolvableType[] resolveGenerics(ResolvableType type) {
        Assert.notNull(type, "ResolvableType can not be null");
        Type origin = type.getType();
        if (origin instanceof Class) {
            /**
             *  Class类型T、D, 类型变量进行解析
             */
            TypeVariable[] typeParameters = ((Class) origin).getTypeParameters();
            int length = typeParameters.length;
            ResolvableType[] resolvableTypes = new ResolvableType[length];

            for (int i = 0; i < length; i++) {
                resolvableTypes[i] = ResolvableType.forType(typeParameters[i]);
            }
            return resolvableTypes;

        } else if (type instanceof ParameterizedType){
            /**
             *  参数泛型 T，?(WildCard类型)
             */
            Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
            int length = typeArguments.length;
            ResolvableType[] resolvableTypes = new ResolvableType[length];

            for (int i = 0; i < length; i++) {
                if (typeArguments[i] instanceof WildcardType) {
                    WildcardType wildcardType = (WildcardType)typeArguments[i];

                    /**
                     *  在不知道类型?的情况下获取边界
                     */
                    if (wildcardType.getLowerBounds()[0] == null) {
                        resolvableTypes[i] = ResolvableType.forType(wildcardType.getUpperBounds()[0]);
                    } else {
                        resolvableTypes[i] = ResolvableType.forType(wildcardType.getLowerBounds()[0]);
                    }
                } else {
                    resolvableTypes[i] = ResolvableType.forType(typeArguments[i]);
                }
            }
            return resolvableTypes;

        } else if (type instanceof GenericArrayType) {
            /**
             *  数组泛型
             */
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return new ResolvableType[] {ResolvableType.forType(componentType)};

        } else if (type instanceof TypeVariable) {
            /**
             *  类型变量
             */
            return new ResolvableType[] {type};

        } else {
            return new ResolvableType[] {ResolvableType.NONE};
        }
    }

    /**
     * 将原始类型转成目标类型
     * @param originType
     * @param destType
     * @return
     */
    public static ResolvableType as(ResolvableType originType, Class<?> destType) {
        if (originType == null) {
            return ResolvableType.NONE;

        } else {
            Class<?> resolve = originType.resolve();
            if (resolve != null && resolve != destType) {
                ResolvableType[] interfaceTypes = getInterfaceTypes(resolve);
                for (int i = 0; i < interfaceTypes.length; i++) {
                    as(interfaceTypes[i], destType);
                }

                as(getSuperType(resolve), destType);
            } else {
                return originType;
            }

            return originType;
        }
    }

    /**
     * 获取Class的父接口ResolvableType类型
     * @param resolve
     * @return
     */
    public static ResolvableType[] getInterfaceTypes(Class<?> resolve) {
        Type[] genericInterfaces = resolve.getGenericInterfaces();
        if (genericInterfaces == null) {
            return resolvableTypes;

        } else {
            ResolvableType[] resolvableTypes = new ResolvableType[genericInterfaces.length];
            for (int i = 0; i < genericInterfaces.length; i++) {
                resolvableTypes[i] = ResolvableType.forType(genericInterfaces[i]);
            }

            return resolvableTypes;
        }
    }

    /**
     * 获取父类ResolvableType类型
     * @param resolve
     * @return
     */
    public static ResolvableType getSuperType(Class<?> resolve) {
        Type superclass = resolve.getGenericSuperclass();
        if (superclass == null) {
            return ResolvableType.NONE;

        } else {
            return ResolvableType.forType(superclass);
        }
    }
}
