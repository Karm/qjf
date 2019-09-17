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

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Michal Karm Babacek <karm@redhat.com>
 */
public class Main {
    public static void main(String[] args) {
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        Scanner s = new java.util.Scanner(System.in).useDelimiter("\\A");
        StringReader reader = new StringReader(s.hasNext() ? s.next() : "");
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject newJsonObject = jsonReader.readObject();
        JsonWriterFactory jwf = Json.createWriterFactory(properties);
        JsonWriter jw = jwf.createWriter(System.out);
        jw.writeObject(newJsonObject);
        System.out.println();
    }
}
