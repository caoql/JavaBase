package cn.cal.javase.datastructure.array;

/**
 * 
 * 描述：泛型不能是基本数据类型，一定要就用包装类
 * 
 * @author 曹启龙
 * @date 2019-03-26 15:56
 */
public class Student {
	private String name;
	private int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("Student(name: %s, score: %d)", name, score);
	}

	public static void main(String[] args) {
		DynamicArray<Student> arr = new DynamicArray<>();
		arr.addLast(new Student("Alice", 100));
		arr.addLast(new Student("Bob", 66));
		arr.addLast(new Student("Charlie", 88));
		System.out.println(arr);
	}
}
