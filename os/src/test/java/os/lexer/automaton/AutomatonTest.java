package os.lexer.automaton;

import os.lexer.Constants;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AutomatonTest {
    private static final Automaton automaton = Constants.keywordsAutomaton;
    private static final List<String> keywords = Constants.KEYWORDS;

    @Test
    void isValid() {
        for (String kw : keywords) {
            assertTrue(automaton.isValid(kw));
        }

        if (keywords.isEmpty()) {
            return;
        }
        String str = keywords.get(0);
        assertFalse(automaton.isValid(str + "a"));
        assertFalse(automaton.isValid(str.substring(0, str.length()-1)));
    }
}