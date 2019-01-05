package owinfo.analysis._4ObserverPattern;

import java.util.EventObject;

public class SendEmailEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SendEmailEvent(Object source) {
        super(source);
    }
}
