package owinfo.analysis._4ObserverPattern;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;

/**
 * MyEventSource作为事件源，维护观察者列表
 * 提供增加，删除观察者等方法
 * 当处理自身业务之后，通知观察者列表
 */
public class MyEventSource  {
    /**
     * 观察者列表
     */
    private List<MyEventListener> listeners;

    public MyEventSource() {
        listeners = new ArrayList<>();
    }

    /**
     * 增加观察者
     */
    public void addListener(MyEventListener listener) {
        listeners.add(listener);
    }

    /**
     * 删除观察者
     */
    public void deleteListener(MyEventListener listener) {
        Iterator<MyEventListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            EventListener eventListener = iterator.next();
            if (eventListener.equals(listener)) {
                listeners.remove(listener);
            }
        }
    }

    /**
     * 通知观察者
     * 根据不同的处理方式，发送不同的事件给观察者
     */
    public void sendEmail(String message) {
        System.out.println("事件源：" + message);
        Iterator<MyEventListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(new SendEmailEvent(message));
        }
    }

    /**
     * 通知观察者
     * 根据不同的处理方式，发送不同的事件给观察者
     */
    public void sendSms(String message) {
        System.out.println("事件源：" + message);
        Iterator<MyEventListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(new SendSmsEvent(message));
        }
    }

    public static void main(String[] args) {
        MyEventListener listener = new SendListener();
        MyEventSource eventSource = new MyEventSource();
        eventSource.addListener(listener);
        eventSource.sendEmail("发送了一封邮件");
        eventSource.sendSms("发送了一条短信");
    }
}

