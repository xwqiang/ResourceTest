package com.test.classLoader;

import java.lang.reflect.Method;

public class Test {

	public void testClassIdentity() {
		this.getClass().getClassLoader().getResource("Sample.class");
		String classDataRootPath = System.getProperty("user.dir")+"/bin";// "C:\\workspace\\Classloader\\classData";
		FileSystemClassLoader fscl1 = new FileSystemClassLoader(
				classDataRootPath);
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(
				"D:/MyEclipse8/ResourceTest/bintest");
		String className = "com.test.classLoader.Sample";
		try {
			Class<?> class1 = fscl1.loadClass(className);
			Object obj1 = class1.newInstance();
			Class<?> class2 = fscl2.loadClass(className);
			Object obj2 = class2.newInstance();
			
			System.out.println(obj1 instanceof Sample);
			System.out.println(obj2 instanceof Sample);
			Method setSampleMethod = class1.getMethod("setSample",
					java.lang.Object.class);
			setSampleMethod.invoke(obj1, obj2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Test t = new Test();
		t.testClassIdentity();
	}
}
