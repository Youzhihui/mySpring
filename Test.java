package ioc;

public class Test {
	public static void main(String[] args) {
		//创建一个Question对象  将对象的创建控制权交由别人处理
		MySpring spring = new MySpring();
		Person person = (Person)spring.getBean("ioc.Person");
		System.out.println(person);
		Question question = (Question)spring.getBean("ioc.Question");
		System.out.println(question);
	
	}
}
