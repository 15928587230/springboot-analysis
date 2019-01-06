package owinfo.analysis._5BootEventMonitor;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  模拟Springboot广播器
 */
public interface SpringEventMulticaster {
}

/**
 * 事件广播器具体实现
 * 遍历存在的事件监听器，依次广播
 */
class SimpleEventMulticaster implements SpringEventMulticaster {

    private Logger logger;
    private List<SpringListener> listeners;
    private ExecutorService executors;

    /**
     * 广播器初始化
     */
    public SimpleEventMulticaster(Logger logger, List<SpringListener> listeners) {
        executors = new ThreadPoolExecutor(10, 10,
                15, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(100));
        this.logger = logger;
        this.listeners = new ArrayList<>();
        this.listeners.addAll(listeners);
    }

    /**
     *  广播不同事件对象
     */
    public void multicaster(SpringEvent springEvent) {
        this.doMulticaster(springEvent);
    }

    /**
     *  遍历执行监听器具体业务
     */
    private void doMulticaster(SpringEvent springEvent) {
        for (final SpringListener listener: listeners) {
            executors.execute(() -> invokeListener(listener, springEvent));
        }
    }

    private void invokeListener(SpringListener listener, SpringEvent springEvent) {
        listener.onRun(springEvent);
    }
}