package main;

import java.util.*;

public class Parser {

    public enum Token {
        EOF,
        PLUS,
        MINUS,
        MULTIPLY,
        OPEN_PAREN,
        CLOSE_PAREN,
        INT_LITERAL
        ;
    }

    private int pos;
    private List<Integer> literals;

    public Parser(){
        //System.out.println("PARSER IS READY!");
    }

    public void parseOut(String input) {
        Integer output = parse(input);

        if(output == null){
            System.out.println("ERROR: Invalid Syntax");
        } else {
            System.out.println(output);
        }

    }

    public Integer parse(String input){
        this.pos = 0;
        return add_expr(string_to_tokens(input));
    }

    public List<Token> string_to_tokens (String input) {
        List<Token> result = new ArrayList<>();
        this.literals = new ArrayList<>();

        char temp;
        for(int i = 0; i < input.length(); i++){
            temp = input.charAt(i);
            if(temp != 32){
                result.add(char_to_token(temp));
                if(result.get(result.size()-1) == Token.INT_LITERAL){
                    this.literals.add(temp - 48);
                } else {
                    this.literals.add(null);
                }
            }
        }

        result.add(Token.EOF);
        this.literals.add(null);

        return result;
    }

    public Token char_to_token(int c){
        switch(c){
            case 40:
                return Token.OPEN_PAREN;
            case 41:
                return Token.CLOSE_PAREN;
            case 43:
                return Token.PLUS;
            case 45:
                return Token.MINUS;
            case 42:
                return Token.MULTIPLY;
            default:
                if(c >= 48 && c<= 57){
                    return Token.INT_LITERAL;
                } else {
                    return Token.EOF;
                }
        }
    }

    public Integer add_expr(List<Token> toks){
        Integer first = mult_expr(toks);

        if (first == null) {
            return null;
        }

        while(toks.get(this.pos) == Token.PLUS || toks.get(this.pos) == Token.MINUS){
            Token op = toks.get(this.pos);
            this.pos++;

            Integer next = mult_expr(toks);
            if(next == null){
                return null;
            }
            first = (op == Token.PLUS) ? first + next : first - next;

        }

        return first;
    }

    public Integer mult_expr(List<Token> toks){
        Integer first = atom(toks);
        if (first == null){
            return null;
        }

        Integer next;
        while(toks.get(this.pos) == Token.MULTIPLY){
            this.pos++;

            next = atom(toks);
            if (next == null){
                return null;
            }

            first = first * next;
        }

        return first;
    }

    public Integer atom(List<Token> toks){
        if(toks.get(this.pos) == Token.OPEN_PAREN){
            this.pos++;
            Integer result = add_expr(toks);

            if(toks.get(this.pos) != Token.CLOSE_PAREN){
                return null;
            }

            this.pos++;
            return result;
        } else if (toks.get(this.pos) == Token.INT_LITERAL) {
            int value = 0;

            while (toks.get(this.pos) == Token.INT_LITERAL){
                value = (10 * value) + this.literals.get(this.pos);
                this.pos++;
            }

            return value;
        } else {
            return null;
        }
    }
}
