package main;

import main.*;

public class AlgParse {
    public static void main(String[] args){
        String input = "";

        for(String arg : args){
            input = input + arg;
        }

        Parser parser = new Parser();
        parser.parseOut(input);
    }
}
