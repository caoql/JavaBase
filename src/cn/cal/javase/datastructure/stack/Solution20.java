package cn.cal.javase.datastructure.stack;

/**
 * 描述：基于栈的LeetCode的刷题学习,第20题
 *
 * @author 曹启龙
 * @date 2019-03-26 17:23
 */
public class Solution20 {

    public boolean isValid(String s) {
        if (s == null || s.trim().length() == 0)
            return true;
        Stack<Character> stack = new ArrayStack<>();
        for (int i = 0, len = s.trim().length(); i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(')
                    return false;
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '{')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println((new Solution20()).isValid("()[]{}"));
        System.out.println((new Solution20()).isValid("([)]"));
    }
}
