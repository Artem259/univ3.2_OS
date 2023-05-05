package os.lexer.automaton;

import java.util.List;

public class Automaton {
    private final State startState;

    public Automaton(List<String> strings) {
        this.startState = new State();
        for (String string : strings) {
            State currentState = startState;
            for (int i = 0; i < string.length(); i++) {
                char ch = string.charAt(i);
                int index = currentState.getTransValues().indexOf(ch);
                if (index == -1) {
                    State newState = new State();
                    currentState.getTransStates().add(newState);
                    currentState.getTransValues().add(ch);
                    currentState = newState;
                } else {
                    currentState = currentState.getTransStates().get(index);
                }
            }
            currentState.setAccepting(true);
        }
    }

    public boolean isValid(String string) {
        State currentState = startState;
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            int index = currentState.getTransValues().indexOf(ch);
            if (index == -1) {
                return false;
            }
            currentState = currentState.getTransStates().get(index);
        }
        return currentState.isAccepting();
    }
}
