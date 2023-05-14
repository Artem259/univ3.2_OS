package os.lexer.handlers;

import os.lexer.exceptions.LexicalException;
import os.lexer.token.Token;
import os.lexer.token.TokenType;

public class StringLitHandler extends Handler {
    private boolean inString = false;

    @Override
    protected HandlingStatus appendCharQuery(char ch) {
        if (ch == '\'') {
            if (!inString) {
                inString = true;
                return HandlingStatus.WAITING;
            }
            return HandlingStatus.ACCEPTED;
        }
        if (inString) {
            return HandlingStatus.WAITING;
        }
        return HandlingStatus.REJECTED;
    }

    @Override
    protected HandlingStatus appendEOFQuery() throws LexicalException {
        if (inString) {
            throw new LexicalException("Unclosed string literal.");
        }
        return HandlingStatus.REJECTED;
    }

    @Override
    protected int numCharsConsumedQuery() {
        return accumulatorString().length();
    }

    @Override
    protected Token getTokenQuery() {
        return new Token(TokenType.STRING_LIT, accumulatorString());
    }

    @Override
    protected void resetQuery() {
        inString = false;
    }
}
