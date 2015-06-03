/**
 * 
 */
package com.somnus.thread.threadlocal;

/**
 * @Title: ThreadLocalTest.java
 * @Package com.somnus.thread.threadlocal
 * @Description: TODO
 * @author Somnus
 * @date 2015年4月25日 上午9:54:27
 * @version V1.0
 */
public class ThreadLocal2Test {
	// 创建一个Integer型的线程本地变量
	public static final ThreadLocal<Index> container = new ThreadLocal<Index>() {
		@Override
		protected Index initialValue() {
			return new Index();
		}
	};

	public static void main(String[] args) throws InterruptedException {
		for (int j = 0; j < 5; j++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 获取当前线程的本地变量，然后累加1000次
					Index index = container.get();
					for (int i = 0; i < 1000; i++) {
						index.increase();
					}
					// 重新设置累加后的本地变量
					container.set(index);
					System.out.println(Thread.currentThread().getName() + " : " + index.num);
				}
			}, "Thread-" + j).start();
		}
	}
}