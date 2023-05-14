package os.lexer.handlers;

import os.lexer.token.Token;
import os.lexer.token.TokenType;

public class IntegerLitHandler extends Handler {
    @Override
    protected boolean includeLastChar() {
        return false;
    }

    @Override
    protected HandlingStatus appendCharQuery(char ch) {
        if (Helper.isLetter(ch)) {
            return HandlingStatus.REJECTED;
        }
        if (!Helper.isDigit(ch)) {
            return HandlingStatus.ACCEPTED;
        }
        return HandlingStatus.WAITING;
    }

    @Override
    protected HandlingStatus appendEOFQuery() {
        return appendCharQuery(' ');
    }

    @Override
    protected int numCharsConsumedQuery() {
        return accumulatorString().length();
    }

    @Override
    protected Token getTokenQuery() {
        return new Token(TokenType.INTEGER_LIT, accumulatorString());
    }

    @Override
    protected void resetQuery() {
    }
}
