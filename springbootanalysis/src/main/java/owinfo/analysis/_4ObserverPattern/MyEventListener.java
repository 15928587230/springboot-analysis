package owinfo.analysis._4ObserverPattern;

import java.util.EventListener;
import java.util.EventObject;

public interface MyEventListener extends EventListener{

    /**
     *  扩展观察者，统一执行update业务
     */
    void update(EventObject eventObject);
}

class SendListener implements MyEventListener {

    /**
     *  根据观察者不同，执行不同的业务
     */
    @Override
    public void update(EventObject eventObject) {
        System.out.println("观察者：" + eventObject.getSource());
    }
}