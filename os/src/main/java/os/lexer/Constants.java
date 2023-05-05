package os.lexer;

import os.lexer.automaton.Automaton;

import java.util.List;

public class Constants {
    private static final String[] keywords = {
            "and", "file", "mod", "repeat", "array", "for", "nil", "set", "begin", "forward", "not", "then",
            "case","function", "of", "to", "const", "goto", "or", "type", "div", "if", "packed", "until", "do",
            "in", "procedure", "var", "downto", "label", "program", "while", "else", "main", "record", "with"};
    private static final String[] operators = {
            "+", "-", "*", "/", "div", "mod", "and", "or",
            "not", "in", "=", "<", ">", "<>", "<=", ">=", ":="};
    private static final String[] punctuation = {"[", "]", ".", ",", ":", ";", "(", ")", "..", "^"};
    private static final String[] leftComments = {"{", "(*", "\"", "/*"};
    private static final String[] rightComments = {"}", "*)", "\"", "*/"};

    public static final List<String> KEYWORDS = List.of(keywords);
    public static final List<String> OPERATORS = List.of(operators);
    public static final List<String> PUNCTUATION = List.of(punctuation);
    public static final List<String> LEFT_COMMENTS = List.of(leftComments);
    public static final List<String> RIGHT_COMMENTS = List.of(rightComments);

    public static final Automaton keywordsAutomaton = new Automaton(KEYWORDS);
}
