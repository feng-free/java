package com.zsl.forkJoinPool;
/*
 * @ClassName RecursiveActionTest
 * @Description orkJoinPool就是用来解决这种问题的：将一个大任务拆分成多个小任务后，
 * 使用fork可以将小任务分发给其他线程同时处理，使用join可以将多个线程处理的结果进行汇总；
 * 这实际上就是分治思想的并行版本
 * @Author 张双亮
 * @Version 1.0
 **/

import java.util.Arrays;
import java.util.Random;
public class RecursiveActionTest {
	public static void main(String[] args) {
		long[] array = new Random().longs(100_0000).toArray();
		System.out.println(array.length);
		Sorter.sort(array);
		System.out.println(Arrays.toString(array));
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
