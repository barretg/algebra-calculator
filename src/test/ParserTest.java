package test;

import main.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    public static Parser parser;

    @BeforeAll
    static void setup(){
        parser = new Parser();
    }

    @Test
    void AddTest1(){
        assertEquals((1 + 2), parser.parse("1 + 2"));
    }

    @Test
    void AddTest2(){
        assertEquals((1 + 2 + 3), parser.parse("1 + 2 + 3"));
    }

    @Test
    void SubTest(){
        assertEquals((1 - 2), parser.parse("1 - 2"));
    }

    @Test
    void SubTest2(){
        assertEquals((1 - 2 - 3), parser.parse("1 - 2 - 3"));
    }

    @Test
    void MultTest(){
        assertEquals((2 * 2), parser.parse("2 * 2"));
    }

    @Test
    void MultTest2(){
        assertEquals((2 * 3* 8 * 27 * 130), parser.parse("2 * 3* 8 * 27 * 130"));
    }

    @Test
    void MixTest(){
        assertEquals(((2 + 3) * 4 * 8 + 27 * (36 - 4)), parser.parse("(2 + 3) * 4 * 8 + 27 * (36 - 4)"));
    }
}
