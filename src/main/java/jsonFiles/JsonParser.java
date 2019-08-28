package jsonFiles;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** The example demonstrates reading from a JSON file */
public class JsonParser {
    public static void main(String[] args) {
        JsonParser jp = new JsonParser();

        //jp.parseSimple("src/main/java/jsonFiles/exJsonSimple.json");
        //jp.parsePersonInfo("src/main/java/jsonFiles/exJsonPersonInfo.json");
        jp.parsePeople("src/main/java/jsonFiles/exJsonPeople.json");

        //jp.parseJSONObjectWithArray("src/main/java/jsonFiles/exJsonWithArray.json");
    }

    /** This method demonstrates how to parse a simple json file
     * that contains info about fruits; using GSON library.
     *
     * @param filePath path to the json file
     */
    public void parseSimple(String filePath) {
        Gson gson = new Gson();

        try (FileReader br = new FileReader(filePath))  {
            Fruit fruit = gson.fromJson(br, Fruit.class);
            System.out.println(fruit);
        }
        catch(IOException e) {
            System.out.println("Could not read the file:" + e);
        }

    }

    /** This method demonstrates how to parse a json file
     * that contains info about the person using GSON library.
     * Person has an address that is also a Json object
     *
     * @param filePath path to the json file
     */
    public void parsePersonInfo(String filePath) {
        Gson gson = new Gson();

        try (FileReader br = new FileReader(filePath))  {
            Person p = gson.fromJson(br, Person.class);
            System.out.println(p);
        }
        catch(IOException e) {
            System.out.println("Could not read the file: " + e);
        }

    }

    /** This method demonstrates how to parse a json file
     * that contains info about several people (value stored in a JSON Array).
     * Uses GSON library.
     *
     * @param filePath path to the json file
     */
    public void parsePeople(String filePath) {
        Gson gson = new Gson();

        try (FileReader br = new FileReader(filePath))  {
            People people = gson.fromJson(br, People.class);
            System.out.println(people);
        }
        catch(IOException e) {
            System.out.println("Could not read the file: " + e);
        }

    }

    /** Demonstrates another way of reading json files using GSON library
     * Here we read each JSON token one by one.
     * @param filePath path to the file
     */
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

    /** Shows a way to read a JSON that contains a JSON array
     * a
     * @param filePath
     */
    public void parseJSONObjectWithArray(String filePath) {
        try (JsonReader jsonReader = new JsonReader(new FileReader(filePath))) {
            jsonReader.beginObject();

            List<Fruit> fruits = new ArrayList<Fruit>();

            if (jsonReader.hasNext()) {

                String name = jsonReader.nextName();
                if (name.equals("fruits")) {
                    // Read Json Array
                    jsonReader.beginArray();
                    String nameOfFruit = "";
                    double price = 0;
                    String color = "";
                    boolean isOrganic = true;

                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String name1 = jsonReader.nextName();
                            if (name1.equals("name")) {
                                nameOfFruit = jsonReader.nextString();
                                System.out.print(nameOfFruit + ", ");
                            } else if (name1.equals("price")) {
                                price = jsonReader.nextDouble();
                                System.out.print(price + ", ");
                            } else if (name1.equals("color")) {
                                color = jsonReader.nextString();
                                System.out.print(color + ", ");
                            } else if (name1.equals("organic")) {
                                isOrganic = jsonReader.nextBoolean();
                                System.out.println(isOrganic);
                            }
                        }

                        Fruit f = new Fruit(nameOfFruit, color, price, isOrganic);
                        fruits.add(f);
                        jsonReader.endObject();

                    }
                    jsonReader.endArray();

                }

            }
            jsonReader.endObject();
        }
        catch (IOException e) {
            System.out.println("Could not read from file: " + e);
        }
    }
}
