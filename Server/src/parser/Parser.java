package parser;

import collection.HumanList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import elements.Car;
import elements.Coordinates;
import elements.HumanBeing;
import parser.deserialization.CarDeserializer;
import parser.deserialization.CoordinatesDeserializer;
import parser.deserialization.HumanDeserializer;
import parser.deserialization.HumanListDeserializer;
import parser.serialization.CarSerializer;
import parser.serialization.CoordinatesSerializer;
import parser.serialization.HumanListSerializer;
import parser.serialization.HumanSerializer;


public class Parser {

    public static String serialize(HumanList humanList) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(HumanList.class, new HumanListSerializer())
                .registerTypeAdapter(HumanBeing.class, new HumanSerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerializer())
                .registerTypeAdapter(Car.class, new CarSerializer())
                .create();
        return gson.toJson(humanList);
    }

    public static HumanList deserialize(String json) throws NullPointerException, JsonParseException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(HumanList.class, new HumanListDeserializer())
                .registerTypeAdapter(HumanBeing.class, new HumanDeserializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
                .registerTypeAdapter(Car.class, new CarDeserializer())
                .create();
        return gson.fromJson(json, HumanList.class);
    }
}
