package com.demo.common.util;

public class JavaTest {
	private static int a = 0;
	static{
		a++;
	}
	void add(){
		a++;
	};
	class A{
		int i;
	}
	class B{
	    int i;
	    int j;
	}
	public static void main(String[] args) {
		JavaTest jt = new JavaTest();
//		jt.add();
//		System.out.println(a);
		JavaTest.A a = jt.new A();
		a.i=1;
		JavaTest.A b = a;
		b.i=2;
		System.out.println(a.i);
		
		JavaTest.B bb = jt.new B();
		bb.i =3;
		setB(bb);
		System.out.println(bb.j);
		
	}
	static void setB(B b){
	    b.j=2;
	}
}
