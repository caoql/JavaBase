package cn.cal.javase.datastructure.array;

/**
 * 描述：自己定义的动态数组
 * 
 * @author 曹启龙
 * @date 2019-03-26 14:07
 */
public class DynamicArray<E> {
	// 存储数据容器
	private E[] data;
	// 存储元素个数
	private int size;
	// 默认初始容量
	private static final int INIT_CAPACITY = 10;

	// 构造函数，传入数组的容量capacity构造Array
	public DynamicArray(int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException("capacity can not <= 0" + capacity);
		// 只能这样写
		data = (E[]) new Object[capacity];
		size = 0;
	}

	// 无参数的构造函数，默认数组的容量capacity=10
	public DynamicArray() {
		this(INIT_CAPACITY);
	}

	// 获取数组的容量,O(1)
	public int getCapacity() {
		return data.length;
	}

	// 获取数组中的元素个数,O(1)
	public int getSize() {
		return size;
	}

	// 返回数组是否为空,O(1)
	public boolean isEmpty() {
		return size == 0;
	}

	// 在index索引的位置插入一个新元素e,O(n/2)=>O(n),如果扩容，扩容是O(n)=>O(n)
	public void add(int index, E e) {
		if (index < 0 || index > size)
			throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
		if (size == data.length)
			// 扩容1倍
			resize(2 * data.length);
		// 插入位置原来及往后的元素要统统向后移动一个位置,从最后一个位置开始=》从后往前
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = e;
		size++;
	}

	// 默认在数组末尾添加元素，O(1)，如果扩容，就是O(n),均摊一下，不是每次都发生，O(2)=>O(1)
	public void add(E e) {
		addLast(e);
	}

	// 在所有元素后添加一个新元素，O(1)，如果扩容，就是O(n)
	public void addLast(E e) {
		add(size, e);
	}

	// 在所有元素前添加一个新元素，O(n)
	public void addFirst(E e) {
		add(0, e);
	}

	// 获取index索引位置的元素，O(1)
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Get failed. Index is illegal.");
		return data[index];
	}

	public E getLast() {
		return get(size - 1);
	}

	public E getFirst() {
		return get(0);
	}

	// 修改index索引位置的元素为e,返回原來的旧值，O(1)
	public E set(int index, E e) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Set failed. Index is illegal.");
		// 旧值
		E oldValue = data[index];
		data[index] = e;
		return oldValue;
	}

	// 查找数组中是否有元素e，O(n)
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(e)) {
				return true;
			}
		}
		return false;
	}

	// 查找数组中元素e所在的索引，找到的第一个。如果不存在元素e，则返回-1，O(n)
	public int find(E e) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	// 从数组中删除index位置的元素, 返回删除的元素
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Remove failed. Index is illegal.");
		E ret = data[index];
		// 从删除元素往后的每个元素向前移动一个位置=>从前往后
		for (int i = index + 1; i < size; i++)
			data[i - 1] = data[i];
		size--;
		// loitering objects != memory leak
		data[size] = null;
		// 缩容一半,避免复杂度震荡(Lazy),缩容不要着急，只有是4分之1的时候才缩容一半
		if (size == data.length / 4 && data.length / 2 != 0)
			resize(data.length / 2);
		return ret;
	}

	// 从数组中删除第一个元素, 返回删除的元素,O(n)
	public E removeFirst() {
		return remove(0);
	}

	// 从数组中删除最后一个元素, 返回删除的元素,O(1),如果缩容，O(n)
	public E removeLast() {
		return remove(size - 1);
	}

	// 从数组中删除元素e,刪除找到的第一元素,O(n)
	public boolean removeElement(E e) {
		int index = find(e);
		if (index != -1) {
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(String.format("Array: size=%d, capacity=%d ", size, data.length));
		sBuilder.append("[");
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				sBuilder.append(data[i]);
			} else {
				sBuilder.append(" " + data[i]);
			}
		}
		sBuilder.append("]");
		return sBuilder.toString();
	}

	// 将数组空间的容量变成newCapacity大小,内部用，所以不用在这个方法做些基础参数校验，调用的地方保证,O(n)
	private void resize(int newCapacity) {
		// size,数据丢失等问题由调用方保证，及扩容不会增加数据，size不变，缩容不会丢失数据，size不会比实际多
		E[] newData = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}
}
