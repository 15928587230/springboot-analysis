package owinfo.analysis._7DecotatorPattern;

import org.springframework.core.ResolvableType;

public class ConcreteComponent implements Component<Class> {

	/**
	 * 此处同样是利用装饰者模式，对ResolvableType进行功能扩展
	 */
	private ResolvableType resolvableType;

	public ConcreteComponent(Class clazz) {
		this.resolvableType = ResolvableType.forClass(clazz);
	}

	@Override
	public ResolvableType[] resolveGenericInterfaces() {
		System.out.println("===> 获取Class父接口ResolvableType[]");
		return this.resolvableType.getInterfaces();
	}

	@Override
	public ResolvableType resolveGenericSuperclass() {
		System.out.println("===> 获取Class父类ResolvableType");
		return this.resolvableType.getSuperType();
	}
}
