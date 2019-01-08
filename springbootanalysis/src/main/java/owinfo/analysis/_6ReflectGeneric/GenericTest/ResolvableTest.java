package owinfo.analysis._6ReflectGeneric.GenericTest;

import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;
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

        ResolvableType[] generics1 = getGenerics(resolvableType1);
        for (ResolvableType type: generics1) {
            System.out.println(type);
        }

        Class<?>[] classes = resolvableType1.resolveGenerics();
        for (Class clazz: classes) {
            System.out.println(clazz);
        }
    }

    /**
     * 自定义方法获取泛型的ResolvableType类型数组
     * @param originType
     * @return
     */
    @Nullable
    public static ResolvableType[] getGenerics(ResolvableType originType) {
        Assert.notNull(originType, "ResolvableType can not be null");
        ResolvableType[] resolvableTypes;

        Type type = originType.getType();
        if (type instanceof Class) {
            TypeVariable[] typeVariables = ((Class) type).getTypeParameters();
            resolvableTypes = new ResolvableType[typeVariables.length];
            fillIn(resolvableTypes, typeVariables);

        } else if (type instanceof ParameterizedType) {
            Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
            resolvableTypes = new ResolvableType[typeArguments.length];

            fillIn(resolvableTypes, typeArguments);
        } else if (type instanceof GenericArrayType){
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            resolvableTypes = new ResolvableType[1];
            resolvableTypes[0] = ResolvableType.forType(componentType);

        } else {
            return null;
        }
        return resolvableTypes;
    }

    public static void fillIn(ResolvableType[] resolvableTypes, Type[] types) {
        for (int i = 0; i < types.length; i++) {
            resolvableTypes[i] = ResolvableType.forType(types[i]);
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
