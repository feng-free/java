package com.zsl.forkJoinPool;/*
 * @ClassName Sort
 * @Description 数组排序
 * @Author 张双亮
 * @Version 1.0
 **/

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Sorter extends RecursiveAction {
	private final long[] array;//排序数组
	private final int lo, hi;//数组的起始索引，数组的结束索引
	private static final int THRESHOLD = 1000;
	private Sorter(long[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}
	//开始任务
	public static void sort(long[] array) {
		ForkJoinPool.commonPool().invoke(new Sorter(array, 0, array.length));
	}
	//判断并切分任务
	protected void compute() {
		// 数组长度小于1000直接排序
		if (hi - lo < THRESHOLD)
			Arrays.sort(array, lo, hi);
		else {
			int mid = (lo + hi) >>> 1;
			// 数组长度大于1000，将数组平分为两份
			// 由两个子任务进行排序
			Sorter left = new Sorter(array, lo, mid);
			Sorter right = new Sorter(array, mid, hi);
			invokeAll(left, right);
			// 排序完成后合并排序结果
			merge(lo, mid, hi);
		}
	}
	//合并小任务
	private void merge(int lo, int mid, int hi) {
		long[] buf = Arrays.copyOfRange(array, lo, mid);
		for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
			if (k == hi || buf[i] < array[k]) {
				array[j] = buf[i++];
			} else {
				array[j] = array[k++];
			}
		}
	}
}
