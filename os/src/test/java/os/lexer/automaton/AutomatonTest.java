package os.lexer.automaton;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class AutomatonTest {
    private static final String[] strings = {"string1", "str1", "string2", "str", "str2", "string"};
    private static final Automaton automaton = new Automaton(Arrays.asList(strings));

    @Test
    void process() {
        for (String str : strings) {
            assertTrue(automaton.process(str));
        }

        assertFalse(automaton.process("s"));
        assertFalse(automaton.process("st"));
        assertFalse(automaton.process("stt"));
        assertFalse(automaton.process("strr"));
        assertFalse(automaton.process("str3"));
        assertFalse(automaton.process("stri"));
        assertFalse(automaton.process("strin"));
        assertFalse(automaton.process("stringg"));
    }
}
