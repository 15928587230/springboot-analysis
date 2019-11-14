package owinfo.analysis._13ConditionalAnnotationTest;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Conditional 注解指定Condition.class。
 * 返回true则注入Bean到容器中
 * 返回false则不注入Bean到容器中
 */
public class WindowsConfig implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String property = System.getProperty("os.name");
		System.out.println(property);
		if (property.contains("Windows")) {
			return true;
		}
		return false;
	}
}
