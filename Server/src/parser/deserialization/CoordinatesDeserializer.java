package parser.deserialization;

import com.google.gson.*;
import elements.Coordinates;

import java.lang.reflect.Type;


public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {

    @Override
    public Coordinates deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new Coordinates(jsonObject.get("x").getAsFloat(), jsonObject.get("y").getAsFloat());
    }
}
