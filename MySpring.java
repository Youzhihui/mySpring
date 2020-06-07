package ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MySpring {
	
	
	//设计一个方法  帮我来控制对象的创建
	//是否有参数？   给一个String类型的字符串（类全名）
	//是否需要返回值？  返回值是创建的对象  但为了通用性我们返回值为Object
	public Object getBean(String className) {
		Object object = null;
		Scanner input = new Scanner(System.in);
		System.out.println("请给"+className+"类的对象赋值");
		try {
			//获取方法传递进来的参数对应的Class
			Class clazz = Class.forName(className);
			//通过clazz创建一个对象
			object = clazz.newInstance();
			//在这里自动的DI注入   对象中的所有属性值  找寻属性对应的set方法来赋值
			//找到每一个不同对象的所有set方法    给属性赋值
			//自己通过拼接字符的方法来处理名字
			//1.先通过clazz找寻类中的所有私有属性---->获取每一个属性的名字---> set属性
			Field[] fields = clazz.getDeclaredFields();
			for(Field field:fields) {
				//获取属性名
				String fieldName = field.getName();
				//1.手动的拼串  拼接属性对应的set方法名  setTitle  setAnswer
				String firstLetter = fieldName.substring(0,1).toUpperCase();//属性的首字母大写
				String otherLetter = fieldName.substring(1);//属性除了首字母之外的其他字母
				StringBuilder setMethodName = new StringBuilder("set");
				setMethodName.append(firstLetter);
				setMethodName.append(otherLetter);
				//3.获取field对应的属性类型 --->找寻set方法时候传递参数用
				Class fieldClass = field.getType();
				//4.通过处理好的set方法名  寻找类中的set方法
				Method setMethod = clazz.getMethod(setMethodName.toString(), fieldClass);
				//5.找到了setMethod一致性，属性就赋值成功
				System.out.println("请给"+fieldName+"属性提供值");
				String value = input.nextLine();
				//需要执行的是属性对应的set方法  给属性赋值
				//属性的值在这里是用过Scanner接收的，以后可以从文件内（配置文件）读取，基本上全是String类型的
				//而执行set方法的时候  方法需要的值不一定都是String类型  比如可能是Integer等属性类型的值
				//如何将所有的String类型的值--->转化为属性类型的值
				//八个包装类中有七个（除了Char类型）是含有带String的构造方法
				//可以利用包装类带String的构造方法  属性类型对应的带String参数的构造方法
				Constructor constructor = fieldClass.getConstructor(String.class);
				setMethod.invoke(object, constructor.newInstance(value));//值的类型应该是属性的类型
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
}
