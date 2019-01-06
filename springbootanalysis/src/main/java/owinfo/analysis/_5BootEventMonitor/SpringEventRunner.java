package owinfo.analysis._5BootEventMonitor;

import org.slf4j.Logger;

import java.util.List;

/**
 * Springboot事件执行者，定义执行方法模板
 * 具体执行方法由子类重写
 */
public interface SpringEventRunner {

    public void starting(SpringEventSource source);

    public void prepare(SpringEventSource source);

    public void running(SpringEventSource source);

    public void closed(SpringEventSource source);
}

class SimpleSpringEventRunner implements SpringEventRunner {

    private Logger logger;
    private SimpleEventMulticaster mutilcaster;

    public SimpleSpringEventRunner(Logger logger, List<SpringListener> listeners) {
        this.logger = logger;
        mutilcaster = new SimpleEventMulticaster(logger, listeners);
    }

    @Override
    public void starting(SpringEventSource source) {
        this.mutilcaster.multicaster(new SpringStartingEvent(source));
    }

    @Override
    public void prepare(SpringEventSource source) {
        this.mutilcaster.multicaster(new SpringPrepareEvent(source));
    }

    @Override
    public void running(SpringEventSource source) {
        this.mutilcaster.multicaster(new SpringRunningEvent(source));
    }

    @Override
    public void closed(SpringEventSource source) {
        this.mutilcaster.multicaster(new SpringClosedEvent(source));
    }
}