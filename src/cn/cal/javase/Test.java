package cn.cal.javase;

import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Test().isValid("]"));
		System.out.println(new Test().isValid(""));
		System.out.println(new Test().isValid("   "));
		System.out.println(new Test().isValid("({[]})"));
		System.out.println(new Test().isValid("({[)]})"));
		System.out.println(new Test().isValid("({[123]})"));
	}

	public boolean isValid(String s) {
		if (s == null || s.trim().length() == 0)
			return true;
		Stack<Character> stack = new Stack<>();
		for (int i = 0, len = s.trim().length(); i < len; i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char sc = stack.pop();
				if (sc == '(' && c != ')') {
					return false;
				} else if (sc == '[' && c != ']') {
					return false;
				} else if (sc == '{' && c != '}') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

}
