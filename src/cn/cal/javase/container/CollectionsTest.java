package cn.cal.javase.container;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 描述： 集合工具类测试
 * 
 * @author 曹启龙
 * @date 2019-03-21 10:58
 */
public class CollectionsTest {

	public static void main(String[] args) {
		Integer[] array = {10,8,9,7,14,13,15};
		List<Integer> list = Arrays.asList(array);
		System.out.println(list);
		List<Object> emptyList = Collections.emptyList();
		System.out.println(emptyList);
	}

}
