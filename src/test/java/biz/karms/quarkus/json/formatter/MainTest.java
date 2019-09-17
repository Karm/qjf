/*
 * Copyright (c) 2019 Contributors to the qjf project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
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