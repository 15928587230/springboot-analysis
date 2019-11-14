package owinfo.analysis._5BootEventMonitor;

import java.util.EventListener;

/**
 * 模拟Springboot监听器
 */
public interface SpringListener extends EventListener {

	void onRun(SpringEvent springEvent);
}

class SpringStartingListener implements SpringListener {

	@Override
	public void onRun(SpringEvent springEvent) {
		/**
		 *  只监听Starting事件
		 */
		if (springEvent instanceof SpringStartingEvent) {
			System.out.println(springEvent.getSource() + "Spring starting listener...");
		}
	}
}

class SpringPrepareListener implements SpringListener {

	@Override
	public void onRun(SpringEvent springEvent) {
		/**
		 *  只监听prepare事件
		 */
		if (springEvent instanceof SpringPrepareEvent) {
			System.out.println(springEvent.getSource() + "Spring prepare listener...");
		}
	}
}

class SpringRunningListener implements SpringListener {

	/**
	 * 只监听Running事件
	 */
	@Override
	public void onRun(SpringEvent springEvent) {
		if (springEvent instanceof SpringRunningEvent) {
			System.out.println(springEvent.getSource() + "Spring running listener...");
		}
	}
}

class SpringClosedListener implements SpringListener {

	/**
	 * 只监听Closed事件
	 */
	@Override
	public void onRun(SpringEvent springEvent) {
		if (springEvent instanceof SpringClosedEvent) {
			System.out.println(springEvent.getSource() + "Spring closed listener...");
		}
	}
}