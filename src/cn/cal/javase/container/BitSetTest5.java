package cn.cal.javase.container;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * 描述：BitSet:Bitset中主要存储的是二进制位，做的也都是位运算，每一位只用来存储0，1值，主要用于对数据的标记。
 * 基本原理是，用1位来表示一个数据是否出现过，0为没有出现过，1表示出现过。使用的时候可以根据某一个位是否为0表示此数是否出现过。JDK中的BitSet集合对是布隆过滤器中经常使用的数据结构Bitmap的相对简单的实现。
 * 使用场景：整数，无重复。
 * 实现原理：底层实现是使用long数组作为内部存储结构的，这就决定了BitSet至少为一个long的大小，而且BitSet的大小为long类型大小(64位)的整数倍。
 * private long[] words;
 * 
 * @author 曹启龙
 * @date 2019-03-21 15:18
 */
public class BitSetTest5 {

	public static void main(String[] args) {
		BitSet bitSet = new BitSet();

		/** set */
		bitSet.set(0);
		bitSet.set(1, 10, true);
		bitSet.set(20, 25, true);
		System.out.println("toString:" + bitSet);

		/** cardinality 返回设置为true的位数 */
		System.out.println("cardinality: " + bitSet.cardinality());

		/** get */
		boolean b = bitSet.get(4);
		System.out.println(b);

		/** length */
		System.out.println("length: " + bitSet.length());

		/** hashCode */
		System.out.println("hashCode: " + bitSet.hashCode());

		/** clear */
		bitSet.clear(0);
		System.out.println("toString:" + bitSet);
		bitSet.clear();
		System.out.println("toString:" + bitSet);

		/** 应用场景 */
		// BitSet常见的应用场景是对海量数据的处理，可以用于对大数量的查找，去重，排序等工作，相比使用其他的方法，占用更少的空间，显著提高效率；也可以使用BitSet进行一些统计工作，比如日志分析、用户数统计等；还可以使用其方法做一些集合方面的运算，比如求并集、交集和补集等
		// 1）使用BitSet查找电话号码
		// 创建一个具有1亿位的bitset 初始所有位的值为false
		BitSet bitSet1 = new BitSet(100_000_000);
		// 将指定位的值设为true
		bitSet1.set(56866666);
		// 或者bitSet1.set(56866666,true);
		// 输出指定位的值
		System.out.println("56866666:" + bitSet1.get(56866666));
		System.out.println("56866667:" + bitSet1.get(56866667));

		// 2）使用BitSet统计随机数的个数
		// 有2万个随机数，随机数的范围在1到1万之间。现在要求写出一种算法，将1到1万之间没有在随机数中的数求出来？
		// 使用BitSet存放随机数并进行统计，减少内存的使用，代码如下：
		test2();
		
		// 3.使用BitSet查找某个范围内的所有素数的个数,使用BitSet进行排序,使用BitSet求并、交、补集,垃圾邮件的识别(布隆过滤器)
	}

	private static void test2() {
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		int len = 20000, max = 10000;
		for (int i = 0; i < len; i++) {
			int randomResult = random.nextInt(max);
			list.add(randomResult);
		}
		System.out.println("0~10000产生随机数的个数：" + len);
		BitSet bitSet = new BitSet(max);
		for (int i = 0; i < len; i++) {
			bitSet.set(list.get(i));
		}
		// public int cardinality()方法返回此BitSet中比特设置为true的数目
		// 就是BitSet中存放的有效位的个数，如果有重复运算会进行自动去重
		System.out.println("0~10000存在BitSet的随机数有: " + bitSet.cardinality() + "个");
		int count = 0;
		for (int i = 0; i < 10000; i++) {
			if (!bitSet.get(i)) {
				count++;
			}
		}
		// 0~10000不在产生的随机数中的个数就是10000减去存在BitSet的随机数个数
		System.out.print("0~10000不在产生的随机数中的个数为:" + count + "个");
	}

}
