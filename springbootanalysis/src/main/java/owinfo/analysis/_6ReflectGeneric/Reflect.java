package owinfo.analysis._6ReflectGeneric;

import java.lang.annotation.*;

/**
 * java8 开始@Repeatable定义重复注解
 *
 * @Repeatable 使用中有点坑, 还是用原来的方式定义较好
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Reflect {

	String desc() default "描述信息";
}
