/**
 * 描述：动态数组：数组的最大优势就是支持随机访问 - 要对比Java中的ArrayList去理解
 * 1.借助扩容,缩容进行拷贝，数组本身长度还是不可变的
 * 2.时间复杂度分析： 增：O(n) 删：O(n) 改：以知索引O(1),未知索引O(n)
 * 查：以知索引O(1),未知索引O(n) resize: O(n)
 * 3.均摊复杂度和避免复杂度震荡(Lazy)
 * @author 曹启龙
 * @date 2019-03-26 13:46
 */
package cn.cal.javase.datastructure.array;