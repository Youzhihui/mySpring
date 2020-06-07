package ioc;

public class Person {
	private String name;
	private Integer age;//在javaBean中最好写基本类型所对应的包装类
	private String sex;
	
	public Person() {}
	public Person(String name, Integer age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String toString() {
		return "{"+name+","+age+","+sex+"}";
	}
}
