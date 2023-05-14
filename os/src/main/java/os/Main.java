package os;

import os.lexer.Lexer;
import os.lexer.token.PositionedToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final String inputFilePath = "files/input.txt";
    private static final String outputFilePath = "files/output.txt";

    public static void main(String[] args) throws IOException {
        String text = Files.readString(Paths.get(inputFilePath));
        Lexer lexer = new Lexer();
        List<PositionedToken> tokens = lexer.process(text);
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            int prevLine = 1;
            for (PositionedToken token : tokens) {
                if (token.getPosition().line() != prevLine) {
                    writer.write("\n");
                }
                writer.write(token + "\n");
                prevLine = token.getPosition().line();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
