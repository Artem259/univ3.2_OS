package os.lexer.handlers;

import os.lexer.token.Token;
import os.lexer.token.TokenType;

public class OperatorHandler extends SequenceHandler {
    public OperatorHandler() {
        super(LexemesSource.OPERATORS, ' ');
    }

    @Override
    protected Token getTokenQuery() {
        return new Token(TokenType.OPERATOR, accumulatorString().substring(0, numCharsConsumed()));
    }
}
