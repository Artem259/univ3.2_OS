package os.lexer;


public record Token(TokenType type, String value, Position position) {
    @Override
    public String toString() {
        return type + "(" + value + ")";
    }
}
