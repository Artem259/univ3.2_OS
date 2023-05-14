package os.lexer;

import os.lexer.exceptions.LexicalException;
import os.lexer.handlers.*;
import os.lexer.token.PositionedToken;
import os.lexer.token.Token;
import os.lexer.textbuffer.TextBuffer;

import java.util.LinkedList;
import java.util.List;

public class Lexer {
    private final LinkedList<Handler> handlers;

    public Lexer() {
        this.handlers = new LinkedList<>();
        handlers.add(new RealLitHandler());
        handlers.add(new IntegerLitHandler());
        handlers.add(new StringLitHandler());
        handlers.add(new CommentHandler());
        handlers.add(new OperatorHandler());
        handlers.add(new PunctuationHandler());
        handlers.add(new KeywordIdentifierHandler());
    }

    private void skipSeparators(TextBuffer buffer) {
        while (buffer.hasChar() && Helper.isSeparator(buffer.getChar())) {
            buffer.next();
        }
    }

    public List<PositionedToken> process(String text) {
        TextBuffer buffer = new TextBuffer(text);
        List<PositionedToken> tokens = new LinkedList<>();
        try {
            while (true) {
                skipSeparators(buffer);
                if (!buffer.hasChar()) {
                    break;
                }
                buffer.rememberPosition();
                Token longestToken = null;
                int longestTokenSize = 0;
                for (Handler handler : handlers) {
                    handler.reset();
                    buffer.restorePosition();
                    while (buffer.hasChar()) {
                        char ch = buffer.getChar();
                        if (handler.appendChar(ch) == HandlingStatus.WAITING) {
                            buffer.next();
                        } else {
                            break;
                        }
                    }
                    if (!buffer.hasChar() && handler.getStatus() == HandlingStatus.WAITING) {
                        handler.appendEOF();
                    }
                    Token token = handler.getToken();
                    int tokenSize = handler.numCharsConsumed();
                    if (token != null && tokenSize > longestTokenSize) {
                        longestToken = token;
                        longestTokenSize = tokenSize;
                    }
                }
                if (longestToken == null) {
                    buffer.restorePosition();
                    throw new LexicalException("Unrecognized sequence.");
                }
                tokens.add(new PositionedToken(longestToken, buffer.rememberedPosition()));
                buffer.restorePosition();
                buffer.jump(longestTokenSize);
            }
        } catch (LexicalException e) {
            buffer.restorePosition();
            tokens.add(new PositionedToken(e.getToken(), buffer.currentPosition()));
        }

        return tokens;
    }
}
