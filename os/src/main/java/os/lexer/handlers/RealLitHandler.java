package os.lexer.handlers;

import os.lexer.token.Token;
import os.lexer.token.TokenType;

public class RealLitHandler extends Handler {
    private boolean containsPeriod = false;
    private boolean containsDigit = false;

    @Override
    protected boolean includeLastChar() {
        return false;
    }

    @Override
    protected HandlingStatus appendCharQuery(char ch) {
        if (Helper.isLetter(ch)) {
            return HandlingStatus.REJECTED;
        }
        if (ch == '.') {
            if (containsPeriod) {
                return HandlingStatus.REJECTED;
            }
            containsPeriod = true;
            return HandlingStatus.WAITING;
        }
        if (!Helper.isDigit(ch)) {
            if (containsPeriod && containsDigit) {
                return HandlingStatus.ACCEPTED;
            }
            return HandlingStatus.REJECTED;
        }
        containsDigit = true;
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
        return new Token(TokenType.REAL_LIT, accumulatorString());
    }

    @Override
    protected void resetQuery() {
        containsPeriod = false;
        containsDigit = false;
    }
}
