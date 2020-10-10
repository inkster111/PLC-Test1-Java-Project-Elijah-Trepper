package com.elitrepper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Main {

    private static HashMap<Character, TokenType> BrainFuckMap = new HashMap<Character, TokenType>();

    public static void main(String[] args) throws Exception {
        createMap();
        String fileContent = readFile("./src/com/elitrepper/text.txt", StandardCharsets.US_ASCII);
        System.out.println(separateLexemes(fileContent));
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String separateLexemes(String initialString) throws Exception{
        String finalString = "";
        for (int i = 0; i < initialString.length(); i++){
            char c = initialString.charAt(i);
            if(BrainFuckMap.get(c) != null) {
                finalString += c + "\n";
            } else {
                throw new Exception("Lexical Error");
            }
        }
        return finalString;
    }

    private static enum TokenType {
        IncrementPointer, DecrementPointer, IncrementByte, DecrementByte, OutputByte, AcceptInput, JumpForward, JumpBackward
    }

    private static void createMap() {
        BrainFuckMap.put('>', TokenType.IncrementPointer);
        BrainFuckMap.put('<', TokenType.DecrementPointer);
        BrainFuckMap.put('+', TokenType.IncrementByte);
        BrainFuckMap.put('-', TokenType.DecrementByte);
        BrainFuckMap.put('.', TokenType.OutputByte);
        BrainFuckMap.put(',', TokenType.AcceptInput);
        BrainFuckMap.put('[', TokenType.JumpForward);
        BrainFuckMap.put(']', TokenType.JumpBackward);
    }

}
