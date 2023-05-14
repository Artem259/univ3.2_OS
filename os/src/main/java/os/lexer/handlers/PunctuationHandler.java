package os.lexer.handlers;

import os.lexer.token.Token;
import os.lexer.token.TokenType;

public class PunctuationHandler extends SequenceHandler {
    public PunctuationHandler() {
        super(LexemesSource.PUNCTUATION, ' ');
    }

    @Override
    protected Token getTokenQuery() {
        return new Token(TokenType.PUNCTUATION, accumulatorString().substring(0, numCharsConsumed()));
    }
}
