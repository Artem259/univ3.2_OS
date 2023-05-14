package os.lexer.token;

import os.lexer.token.position.Position;

public class PositionedToken extends Token {
    private final Position position;

    public PositionedToken(Token token, Position position) {
        super(token);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return type + "[" + position + "]" + ": " + value;
    }
}
