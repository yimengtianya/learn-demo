package logback.learn;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Runnable {

	Logger logger = LoggerFactory.getLogger(Task.class);

	@Override
	public void run() {
		for (int i = 0; i < 10000000; i++) {
			logger.debug("hehe");
		}
	}

	public static void main(String[] args) throws InterruptedException {

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
		for (int i = 0; i < 10; i++) {
			executor.execute(new Task());
		}
	}

}
