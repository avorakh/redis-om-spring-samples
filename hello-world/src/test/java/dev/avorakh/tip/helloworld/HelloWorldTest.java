package dev.avorakh.tip.helloworld;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HelloWorldTest {
    private static final PrintStream STANDARD_OUT = System.out;
    private static final ByteArrayOutputStream OUTPUT_STREAM_CAPTOR = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));
    }

    @Test
    void shouldSuccessfullyOutputHelloWorld() {

        HelloWorld.main(null);

        Assertions.assertEquals("Hello, World!", OUTPUT_STREAM_CAPTOR.toString().trim());
    }

    @AfterAll
    static void afterAll() {
        System.setOut(STANDARD_OUT);
    }
}
