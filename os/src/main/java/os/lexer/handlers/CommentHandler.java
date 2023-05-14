package os.lexer.handlers;

import os.lexer.exceptions.LexicalException;
import os.lexer.handlers.automaton.Automaton;
import os.lexer.token.Token;
import os.lexer.token.TokenType;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CommentHandler extends Handler {
    private boolean inComment = false;
    private final Automaton startAutomaton = new Automaton(LexemesSource.LEFT_COMMENTS);
    private final List<Automaton> endAutomatonsList = new LinkedList<>();

    @Override
    protected HandlingStatus appendCharQuery(char ch) {
        if (!inComment) {
            HandlingStatus status = startAutomaton.appendChar(ch);
            if (status == HandlingStatus.REJECTED) {
                return HandlingStatus.REJECTED;
            }
            if (status == HandlingStatus.ACCEPTED) {
                inComment = true;
            }
            return HandlingStatus.WAITING;
        }

        endAutomatonsList.add(new Automaton(LexemesSource.RIGHT_COMMENTS));
        ListIterator<Automaton> iterator = endAutomatonsList.listIterator();
        while (iterator.hasNext()) {
            Automaton automaton = iterator.next();
            HandlingStatus status = automaton.appendChar(ch);
            if (status == HandlingStatus.REJECTED) {
                iterator.remove();
            }
            if (status == HandlingStatus.ACCEPTED) {
                return HandlingStatus.ACCEPTED;
            }
        }
        return HandlingStatus.WAITING;
    }

    @Override
    protected HandlingStatus appendEOFQuery() throws LexicalException {
        if (inComment) {
            throw new LexicalException("Unclosed comment.");
        }
        return HandlingStatus.REJECTED;
    }

    @Override
    protected int numCharsConsumedQuery() {
        return accumulatorString().length();
    }

    @Override
    protected Token getTokenQuery() {
        return new Token(TokenType.COMMENT, accumulatorString());
    }

    @Override
    protected void resetQuery() {
        inComment = false;
        startAutomaton.reset();
        endAutomatonsList.clear();
    }
}
