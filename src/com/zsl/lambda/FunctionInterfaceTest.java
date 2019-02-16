package com.zsl.lambda;
/*
 * @ClassName FunctionInterfaceTest
 * @Description lambda测试
 * @Author 张双亮
 * @Version 1.0
 **/

public class FunctionInterfaceTest {
	
    public static void test() {
		boolean b = func(new FunctionInterface<Integer>() {
			@Override
			public boolean test(Integer param) {
				if(param == 1){
					return true;
				}else{
					return false;
				}
			}
		});
	    System.out.println("++++"+b);
	}
	public static void testLambda(){
		//使用Lambda表达式代替上面的匿名内部类
		boolean b = func((Integer x) -> {
			if(x == 1){
				return true;
			}else{
				return false;
			}
		});
		System.out.println("----"+b);
	}
	public static boolean func(FunctionInterface<Integer> functionInterface) {
		int x = 1;
		return functionInterface.test(x);
	}
}
