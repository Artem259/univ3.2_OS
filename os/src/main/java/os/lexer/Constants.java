package os.lexer;

import os.lexer.automaton.Automaton;

import java.util.List;

public class Constants {
    private static final String[] keywords = {
            "array", "begin", "case", "const", "do", "downto", "else", "end", "file", "for", "function",
            "goto", "if", "label", "nil", "of", "packed", "procedure", "program", "record", "repeat",
            "set", "then", "to", "type", "until", "var", "while", "with", "abs", "arctan", "Boolean",
            "char", "chr", "cos", "dispose", "eof", "eoln", "exp", "false", "get", "input", "integer",
            "ln", "maxint", "new", "odd", "ord", "output", "pack", "page", "pred", "put", "read",
            "readln", "real", "reset", "rewrite", "round", "sin", "sqr", "sqrt", "succ", "text", "true",
            "trunc", "unpack", "write", "writeln"};
    private static final String[] operators = {
            "+", "-", "*", "/", "div", "mod", "=", "<", ">", "<>", "<=", ">=", "in", "and", "or", "not"};
    private static final String[] punctuation = {
            "[", "]", ".", ",", ":", ";", "(", ")", "..", "^", ":="};
    private static final String[] leftComments = {
            "{", "(*"};
    private static final String[] rightComments = {
            "}", "*)"};

    public static final List<String> KEYWORDS = List.of(keywords);
    public static final List<String> OPERATORS = List.of(operators);
    public static final List<String> PUNCTUATION = List.of(punctuation);
    public static final List<String> LEFT_COMMENTS = List.of(leftComments);
    public static final List<String> RIGHT_COMMENTS = List.of(rightComments);

    public static final Automaton keywordsAutomaton = new Automaton(KEYWORDS);
    public static final Automaton operatorsAutomaton = new Automaton(OPERATORS);
}
