package owinfo.analysis._6ReflectGeneric;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Reflects {
	Reflect[] reflect();
}
