class Solution {
    public boolean isValid(String s) {
        // Add every opening bracket to a stack
        // The next closing bracket will have to be of the same type
        // as the top of the stack
        // Then pop from the stack
        // If stack is empty, then it's wrong
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : arr) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if (c == ')' && top != '('
                || c == '}' && top != '{'
                || c == ']' && top != '[') {
                return false;
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
    }
}