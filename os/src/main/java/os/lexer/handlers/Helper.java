package os.lexer.handlers;

import java.util.List;

public class Helper {
    static private final List<Character> separators = List.of(' ', '\t', '\n', '\r');

    static public boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    static public boolean isLetter(char ch) {
        return isSmallLetter(ch) || isCapitalLetter(ch);
    }

    static public boolean isSmallLetter(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    static public boolean isCapitalLetter(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    static public boolean isSeparator(char ch) {
        return separators.contains(ch);
    }
}
