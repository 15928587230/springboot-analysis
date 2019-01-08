package owinfo.analysis._6ReflectGeneric.GenericTest;

import org.springframework.core.ResolvableType;
import owinfo.analysis._6ReflectGeneric.ReflectParentTest;
import owinfo.analysis._6ReflectGeneric.ReflectTest;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 *  泛型参数类型测试Type
 *  Type的子接口ParameterizedType、GenericArrayType、WildCardType、TypeVariable
 *  Type实现类Class
 *
 *  ParameterizedType 参数化类型，即泛型。用来获取泛型参数类型数组。如Map<K,V>
 *  GenericArrayType 参数化类型，数组泛型。用来获取数组泛型的参数类型. 如List<T>[]
 *  WildCardType 参数化类型，边界泛型。用来获取泛型的边界类型 如 User<T extends User, T super User>
 *  TypeVariable  参数化类型，类型变量泛型。用来获取泛型能够接受的变量类型，以及边界类型等等。 比如GenericTest<T extends GenericTest>
 *
 */
public class GenericTest<T extends GenericTest> extends Object{

    public void test(GenericTest p0, GenericInnerTest<GenericTest> p1,
                            Map<String,GenericTest> p2, List<String>[] p3,
                            Map<String,GenericTest>[] p4, List<? extends GenericTest> p5,
                            Map<? extends GenericTest,? super GenericTest> p6) {
    }

    class GenericInnerTest<T extends GenericTest> {}

    public static void main(String[] args) throws Exception {
        Method[] test = GenericTest.class.getDeclaredMethods();
        for (Method method: test) {
            if ("test".equals(method.getName())) {
                Type[] parameterTypes = method.getGenericParameterTypes();
                for (Type type: parameterTypes) {
                    /**
                     * 泛型参数类型为 ParameterizedType
                     * getActualTypeArguments() 获取泛型的参数类型数组
                     * getOwnerType() 获取泛型所属的类的类型
                     * getRawType() 获取声明参数的外部类型
                     */

                    if (type instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) type;
                        System.out.println(parameterizedType.getActualTypeArguments());
                        System.out.println(parameterizedType.getOwnerType());
                        System.out.println(parameterizedType.getRawType());
                    }
                    if (type instanceof Class) {
                        Class clazz = (Class) type;
                        System.out.println(clazz.getTypeName());
                    }
                    if (type instanceof TypeVariable) {
                        TypeVariable typeVariable = (TypeVariable) type;
                        System.out.println(typeVariable.getTypeName());
                    }
                    if (type instanceof GenericArrayType) {
                        GenericArrayType genericArrayType = (GenericArrayType) type;
                        System.out.println(genericArrayType.getTypeName());
                    }
                    if (type instanceof WildcardType) {
                        WildcardType wildcardType = (WildcardType) type;
                        System.out.println(wildcardType.getTypeName());
                    }
                }
            }
        }

        System.out.println("\n\n");
        // 获取类型变量 T
        TypeVariable<Class<GenericTest>>[] typeParameters = GenericTest.class.getTypeParameters();
        for (TypeVariable typeVariable: typeParameters) {
            System.out.println(typeVariable.getName()); //T
            System.out.println(typeVariable.getBounds()[0].getTypeName());//上边界GenericTest
        }


        /**
         *  Springboot ResolvableType是对泛型的一种包装，表示一种类或者接口等等的类型
         *  同样提供了获取Field、Method、Class、Type的方法，并且使用起来更加简单
         */
        boolean assignableFrom = Object.class.isAssignableFrom(GenericTest.class);
        System.out.println("Object是GenericTest的父类" + assignableFrom); //true

        ResolvableType resolvableType = ResolvableType.forClass(ReflectParentTest.class);
        boolean assignable = resolvableType.isAssignableFrom(ReflectTest.class);
        System.out.println("ReflectParentTest是ReflectTest的父类" + assignable); //true
    }
}
