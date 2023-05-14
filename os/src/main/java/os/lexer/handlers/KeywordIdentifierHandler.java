package os.lexer.handlers;

import os.lexer.handlers.automaton.Automaton;
import os.lexer.token.Token;
import os.lexer.token.TokenType;

public class KeywordIdentifierHandler extends Handler {
    private final Automaton keywordAutomaton = new Automaton(LexemesSource.KEYWORDS);
    private boolean isKeyword = false;

    @Override
    protected char charPreprocessing(char ch) {
        return Character.toLowerCase(ch);
    }

    @Override
    protected boolean includeLastChar() {
        return false;
    }

    @Override
    protected HandlingStatus appendCharQuery(char ch) {
        if (Helper.isDigit(ch) && index() == 0) {
            return HandlingStatus.REJECTED;
        }
        if (!Helper.isDigit(ch) && !Helper.isLetter(ch)) {
            if (keywordAutomaton.appendString(accumulatorString()) == HandlingStatus.ACCEPTED) {
                isKeyword = true;
                return HandlingStatus.ACCEPTED;
            }
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
        if (isKeyword) {
            return new Token(TokenType.KEYWORD, accumulatorString());
        }
        return new Token(TokenType.IDENTIFIER, accumulatorString());
    }

    @Override
    protected void resetQuery() {
        keywordAutomaton.reset();
        isKeyword = false;
    }
}
