package owinfo.analysis._5BootEventMonitor;

import java.util.EventObject;

/**
 *  模拟Springboot事件对象
 */
public class SpringEvent  extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SpringEvent(Object source) {
        super(source);
    }
}

/**
 *  下面自定义事件类型
 */
class SpringStartingEvent extends SpringEvent{
    public SpringStartingEvent(Object source) {
        super(source);
    }
}

class SpringPrepareEvent extends SpringEvent {
    public SpringPrepareEvent(Object source) {
        super(source);
    }
}

class SpringRunningEvent extends SpringEvent {
    public SpringRunningEvent(Object source) {
        super(source);
    }
}

class SpringClosedEvent extends SpringEvent {
    public SpringClosedEvent(Object source) {
        super(source);
    }
}
