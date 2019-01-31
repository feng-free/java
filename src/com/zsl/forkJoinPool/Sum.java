package com.zsl.forkJoinPool;/*
 * @ClassName Sum
 * @Description 大数计算
 * @Author 张双亮
 * @Version 1.0
 **/

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class Sum extends RecursiveTask<Long> {
	private final int[] array;
	private final int lo, hi;
	private static final int THRESHOLD = 600;
	public static long sum(int[] array) {
		return ForkJoinPool.commonPool().invoke(new Sum(array, 0, array.length));
	}
	private Sum(int[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}
	@Override
	protected Long compute() {
		if (hi - lo < THRESHOLD) {
			return sumSequentially();
		} else {
			int middle = (lo + hi) >>> 1;
			Sum left = new Sum(array, lo, middle);
			Sum right = new Sum(array, middle, hi);
			right.fork();
			long leftAns = left.compute();
			long rightAns = right.join();
			// 注意subTask2.fork要在subTask1.compute之前
			// 因为这里的subTask1.compute实际上是同步计算的
			return leftAns + rightAns;
		}
	}
	
	private long sumSequentially() {
		long sum = 0;
		for (int i = lo; i < hi; i++) {
			sum += array[i];
		}
		return sum;
	}
}
