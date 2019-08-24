package jsonFiles;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** The example demonstrates reading from a JSON file */
public class JsonParser {
    public static void main(String[] args) {
        JsonParser jp = new JsonParser();
        jp.parseSimple("src/main/java/jsonFiles/exJsonSimple.json");
        //jp.parse("src/main/java/jsonFiles/exJsonSimple.json");
    }

    public void parseSimple(String filePath) {
        Gson gson = new Gson();

        try (FileReader br = new FileReader(filePath))  {
            Fruit fruit = gson.fromJson(br, Fruit.class);
            System.out.println(fruit);
        }

        catch(IOException e) {
            System.out.println("Could not read the file");
        }

    }

    public void parse(String filePath) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            jsonReader.beginObject();

            while (jsonReader.hasNext()) {

                String name = jsonReader.nextName();
                if (name.equals("name")) {
                    String nameOfFruit = jsonReader.nextString();
                    System.out.println(nameOfFruit);
                }
                else if (name.equals("price")){
                    System.out.println(jsonReader.nextLong());
                }
                else if (name.equals("color")) {
                    System.out.println(jsonReader.nextString());
                }
                else if (name.equals("organic")) {
                    System.out.println(jsonReader.nextBoolean());
                }
            }

            jsonReader.endObject();

        } catch (IOException e) {
            System.out.println("Could not read from file");
        }
    }
}
