package parser.deserialization;

import collection.HumanList;
import com.google.gson.*;
import elements.HumanBeing;

import java.lang.reflect.Type;


public class HumanListDeserializer implements JsonDeserializer<HumanList> {

    @Override
    public HumanList deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        HumanList result = new HumanList();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray humans = jsonObject.getAsJsonArray("humans");

        for(JsonElement human : humans) {
            result.addHuman((HumanBeing) jsonDeserializationContext.deserialize(human, HumanBeing.class));
        }

        return result;
    }
}
