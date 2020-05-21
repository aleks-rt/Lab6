package parser.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import elements.Car;

import java.lang.reflect.Type;

public class CarSerializer implements JsonSerializer<Car> {

    @Override
    public JsonElement serialize(Car car, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", car.getName());

        return jsonObject;
    }
}
