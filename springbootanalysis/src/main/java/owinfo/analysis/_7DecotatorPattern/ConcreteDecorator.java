package owinfo.analysis._7DecotatorPattern;

import org.springframework.core.ResolvableType;

public class ConcreteDecorator implements Decorator<Component> {

	/**
	 * 对Component进行装饰
	 */
	private Component component;

	public ConcreteDecorator(Component component) {
		this.component = component;
	}

	public ResolvableType[] resolveGenericInterfaces() {
		return component.resolveGenericInterfaces();
	}

	public ResolvableType resolveGenericSuperclass() {
		return component.resolveGenericSuperclass();
	}
}
