package owinfo.analysis._13ConditionalAnnotationTest;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Conditional 注解指定Class<T>。
 * 返回true则注入Bean到容器中
 * 返回false则不注入Bean到容器中
 */
public class LinuxConfig implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String property = System.getProperty("os.name");
		System.out.println(property);
		if (property.contains("Linux")) {
			return true;
		}
		return false;
	}
}
