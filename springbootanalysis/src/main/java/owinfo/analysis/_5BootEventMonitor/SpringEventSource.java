package owinfo.analysis._5BootEventMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟事件源对象
 */
public class SpringEventSource implements Serializable {

	private String msgId;
	private String msg;

	public SpringEventSource(String msgId, String msg) {
		this.msgId = msgId;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "SpringEventSource{" +
				"msgId='" + msgId + '\'' +
				", msg='" + msg + '\'' +
				'}';
	}

	/**
	 * 创建事件源, 发布不同事件
	 */
	public static void main(String[] args) {
		SpringEventSource source = new SpringEventSource("ID", "MSG");

		Logger logger = LoggerFactory.getLogger(SpringEventSource.class);
		List<SpringListener> listeners = new ArrayList<>();
		listeners.add(new SpringStartingListener());
		listeners.add(new SpringClosedListener());
		listeners.add(new SpringPrepareListener());
		listeners.add(new SpringRunningListener());
		// 创建执行者
		SpringEventRunner springEventRunner = new SimpleSpringEventRunner(logger, listeners);
		springEventRunner.starting(source);
		springEventRunner.prepare(source);
		springEventRunner.running(source);
		springEventRunner.closed(source);
	}
}
