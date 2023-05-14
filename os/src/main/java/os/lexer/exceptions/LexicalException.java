package os.lexer.exceptions;

import os.lexer.token.Token;
import os.lexer.token.TokenType;

public class LexicalException extends Exception {
    public LexicalException(String message) {
        super(message);
    }

    public Token getToken() {
        return new Token(TokenType.ERROR, getMessage());
    }
}
