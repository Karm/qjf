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
