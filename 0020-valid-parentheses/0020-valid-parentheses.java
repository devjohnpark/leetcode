class Solution {

    // Problem
    // Is include in String same type brackets like {}. (), []?

    // Output for input
    // {()[]()()} -> true
    // {()[][)} -> false

    // constraint   
    // 1. 1 <= s.length <= 104
    // 2. s consists of parentheses only '()[]{}'.

    // Logic
    // 1. Search the input string one by one.
    // 2. Input open brackets save for data structure.
    // 3. Check is it same type the last input data in data struture and searched character.
    // 4. If searched character is close bracket and data structure is empty, return false.
    // 5. If is same type, pass, otherwise return false.
    // 6. If end of string the data structure is emtpy return false, othersiwe return false

    // Data structure
    // It neeeds access the last data for data strucrue and the last data is goging to add or remove.
    // So stack is best choice. because it easy access a top element and add or remove element.

    // prepare pair brackets for a two character is matching
    private char[][] pairBrackets = {{'{', '}'}, {'[', ']'}, {'(', ')'}};

    // check is it oppen bracket
    private boolean isOpenBracket(char c) {
        for (char[] pairBracket: pairBrackets) {
            if (pairBracket[0] == c) {
                return true;
            }
        }
        return false;
    }

    // check is it close bracket
    private boolean isCloseBracket(char c) {
        for (char[] pairBracket: pairBrackets) {
            if (pairBracket[1] == c) {
                return true;
            }
        }
        return false;
    }

    // check are their pair brackets
    private boolean isPairBrackets(char openBracket, char closeBracket) {
        for (char[] pairBracket: pairBrackets) {
            if (pairBracket[0] == openBracket && pairBracket[1] == closeBracket) {
                return true;
            }
        } 
        return false;
    }

    // valid is it blanced parenthese 
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        char[] chars = s.toCharArray();
        for (char c: chars) {
            if (isOpenBracket(c)) {
                stack.push(c);
            } else if (isCloseBracket(c)) {
                if (stack.isEmpty() || !isPairBrackets(stack.pop(), c)) {
                    return false;
                }
              
            }
        } 
        return stack.isEmpty();
    }
}