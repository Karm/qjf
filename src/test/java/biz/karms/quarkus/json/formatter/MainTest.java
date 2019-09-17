package biz.karms.quarkus.json.formatter;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class MainTest {

    @Test
    public void testJSON() throws UnsupportedEncodingException {
        String input = "                {\"This\":\"Is\", \"Not\": [\"Very\", \"Pretty\"], \"Format\":{\"Right?\":\"true\"}}";
        String expectedOutput = "\n{\n" +
                "    \"This\": \"Is\",\n" +
                "    \"Not\": [\n" +
                "        \"Very\",\n" +
                "        \"Pretty\"\n" +
                "    ],\n" +
                "    \"Format\": {\n" +
                "        \"Right?\": \"true\"\n" +
                "    }\n" +
                "}\n";
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream o = new PrintStream(baos, true, StandardCharsets.UTF_8.name())) {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(o);
            Main.main(ArrayUtils.EMPTY_STRING_ARRAY);
            String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
            assertEquals(expectedOutput, output, "Expected output does not match.");
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }
}