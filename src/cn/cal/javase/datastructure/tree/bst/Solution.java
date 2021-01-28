package cn.cal.javase.datastructure.tree.bst;

/**
 * 
 * 描述：leetcode-804
 * 
 * @author 曹启龙
 * @date 2019-03-28 13:14
 */
public class Solution {
	public static void main(String[] args) {
		String[] words = { null, "", "  ", "cab", "abc", "abc" };
		System.out.println(new Solution().uniqueMorseRepresentations(words));
	}

	public int uniqueMorseRepresentations(String[] words) {
		// a-0,b-1,c-2
		String[] codes = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
				"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
		if (words == null)
			return 0;
		// 容器來存，满足去重特性
		BSTree<String> set = new BSTree<>();
		for (String word : words) {
			if (word == null || word.trim().length() == 0)
				continue;
			StringBuilder sBuilder = new StringBuilder();
			boolean isValid = true;
			for (int i = 0, len = word.trim().length(); i < len; i++) {
				char c = word.charAt(i);
				int index = c - 'a';
				if (index > codes.length - 1) {
					isValid = false;
					break;
				}
				String sc = codes[index];
				sBuilder.append(sc);
			}
			if (isValid)
				set.add(sBuilder.toString());
		}
		return set.size();
	}

}
