package cn.cal.javase.container;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * 描述： Vector学习，1.2重写过，实现List接口，成为新的集合框架的一员
 * 1.底层数据结构： 动态数组， protected Object[] elementData数组缓冲区，protected int elementCount实际存放元素个数，protected int capacityIncrement扩容量
 * 2.性能如何： 比ArrayList差点儿，因为有同步保证线程安全
 * 3.线程安全不安全： 同步的，线程安全
 * 4.常用API: 
 * 5.应用场景：
 * 
 * @author 曹启龙
 * @date 2019-03-21 11:14
 */
public class VectorTest3 {

	public static void main(String[] args) throws InterruptedException {
		// 初始容量是10-this(10)，扩容量是0
		Vector<String> vector = new Vector<>();
		
		/** ------添加方法------ **/
		vector.add("20");
		vector.add("30");
		vector.add("40");
		vector.add("50");
		// synchronized,，
		vector.add("10");
		// 容量检测-ensureCapacityHelper(elementCount + 1)
		vector.add("8");
		// 扩容- int newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity);
		vector.add("9");
		// elementData = Arrays.copyOf(elementData, newCapacity);
		vector.add("7");
		// 添加元素位置-elementData[elementCount++] = e;
		vector.add("13");
		// 时间复杂度-O(1)
		vector.add("11");
		// toString方法重写了
		System.out.println(Thread.currentThread().getName()+ "->Vector容器的元素内容是：" + vector);
		
		/** 修改元素 */
		// synchronized,拿旧值E oldValue = elementData(index)，设新值 elementData[index] = element
		vector.set(0, "60");
		// synchronized，只有设新值elementData[index] = obj;
		vector.setElementAt("70", 1);
		System.out.println(Thread.currentThread().getName()+ "->Vector容器的元素内容是：" + vector);
		
		/** 获取一个元素 */
		// synchronized,内部封装的方法elementData(index)，拿索引位置的元素，O(1)
		String value = vector.get(0);
		System.out.println(Thread.currentThread().getName()+ "->获取Vector容器位置0是：" + value);
		
		
		/** 删除一个元素 **/
		// 按照索引，synchronized，int numMoved = elementCount - index - 1元素移动个数，System.arraycopy(elementData, index+1, elementData, index,numMoved);数组拷贝，删除的是最后一个元素不用移动elementData[--elementCount] = null
		vector.remove(0);
		// 按照元素,第一个匹配的，synchronized， int i = indexOf(obj);元素拿索引位置，removeElementAt(i);
		vector.remove("70");
		// 同remove，synchronized，遗留的方法
		vector.removeElementAt(0);
		// O(n)
		vector.removeElement("50");
		System.out.println(Thread.currentThread().getName()+ "->Vector容器的元素内容是：" + vector);
		
		/** 迭代方法 */
		// 1.Enumeration,只有2个函数接口。通过Enumeration，我们只能读取集合的数据，而不能对数据进行修改。不支持fail-fast
		Thread t1 =  new Thread(()->{
			System.out.print(Thread.currentThread().getName()+ "->Vector容器Enumeration迭代是：[");
			Enumeration<String> enumeration = vector.elements();
			while (enumeration.hasMoreElements()) {
				String element = enumeration.nextElement();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print(" " + element);
			}
			System.out.println(" ]");
			// 没有元素之后抛java.util.NoSuchElementException
			// System.out.print("throw java.util.NoSuchElementException" + enumeration.nextElement());
		},"线程1");
		Thread t2 = new Thread(()->{
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("("+Thread.currentThread().getName()+ "->删除的元素[8]是：" + vector.remove("8") + ")");
		},"线程2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(Thread.currentThread().getName()+ "->Vector容器的元素内容是：" + vector);
		// 2.Iterator,只有3个函数接口。Iterator除了能读取集合的数据之外，也能数据进行删除操作。支持fail-fast机制
		Thread t3 = new Thread(()->{
			System.out.print(Thread.currentThread().getName()+ "->Vector容器Iterator迭代是：[");
			Iterator<String> it = vector.iterator();
			while (it.hasNext()) {
				String element = it.next();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if ("10".equals(element)) {
					//  迭代器本身删除了这个元素不会报错，但是必须在next方法之后调用，否则抛出此异常java.lang.IllegalStateException
					it.remove();
				}
				System.out.print(" " + element);
			}
			System.out.println(" ]");
			// 没有元素之后抛Exception in thread "线程3" java.util.NoSuchElementException
			//System.out.print("throw java.util.NoSuchElementException" + it.next());
		},"线程3");
		Thread t4 = new Thread((
				)->{
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Exception in thread "线程3" java.util.ConcurrentModificationException,会导致正在迭代这个容器的线程抛前面这个错
					System.out.println("("+Thread.currentThread().getName()+ "->删除的元素[13]是：" + vector.remove("13") + ")");
				},"线程4");
		t3.start();
		t4.start();
		t3.join();
		t4.join();
		System.out.println(Thread.currentThread().getName()+ "->Vector容器的元素内容是：" + vector);
		
		/** clone 深度拷贝*/
		Object cloneVector = vector.clone();
		System.out.println(Thread.currentThread().getName()+ "->拷贝的Vector:" + cloneVector);
		System.out.println(cloneVector == vector);
		vector.add("end");
		System.out.println(Thread.currentThread().getName()+ "->Vector容器的元素内容是：" + vector);
		System.out.println(Thread.currentThread().getName()+ "->拷贝的Vector:" + cloneVector);
	}

}
