package owinfo.analysis._7DecotatorPattern;

import org.springframework.core.ResolvableType;

import java.util.ArrayList;

public class MainTest {

    public static void main(String[] args) {

        /**
         *  被装饰对象
         *  ConcreteComponent利用装饰者模式对ResolvableType进行扩展，扩展进行打印功能
         */
        Component component = new ConcreteComponent(ArrayList.class);

        /**
         *  装饰者对象
         */
        Decorator decorator = new ConcreteDecorator(component);

        ResolvableType[] interfaces = ((ConcreteDecorator) decorator).resolveGenericInterfaces();

        for (ResolvableType resolvableType: interfaces) {
            System.out.println(resolvableType);
        }

        ResolvableType resolvableType = ((ConcreteDecorator) decorator).resolveGenericSuperclass();
        System.out.println(resolvableType);
    }
}
