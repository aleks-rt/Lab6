package parser.deserialization;

import com.google.gson.*;
import elements.Car;

import java.lang.reflect.Type;


public class CarDeserializer implements JsonDeserializer<Car> {

    @Override
    public Car deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new Car(jsonObject.get("name").getAsString());
    }
}
