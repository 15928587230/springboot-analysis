package owinfo.analysis._4ObserverPattern;

import java.util.EventObject;

public class SendSmsEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SendSmsEvent(Object source) {
        super(source);
    }
}
