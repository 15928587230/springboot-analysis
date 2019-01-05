package owinfo.analysis._4ObserverPattern;

import java.util.EventListener;
import java.util.EventObject;

public interface MyEventListener extends EventListener{

    public void update(EventObject eventObject);
}

class SendEmailListener implements MyEventListener {

    @Override
    public void update(EventObject eventObject) {
        System.out.println("观察者：" + eventObject.getSource());
    }
}