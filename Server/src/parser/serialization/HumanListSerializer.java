package parser.serialization;

import collection.HumanList;
import com.google.gson.*;
import elements.HumanBeing;

import java.lang.reflect.Type;


public class HumanListSerializer implements JsonSerializer<HumanList> {

    @Override
    public JsonElement serialize(HumanList humanList, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        for (HumanBeing human : humanList.getHumans()) {
            jsonArray.add(jsonSerializationContext.serialize(human));
        }

        jsonObject.add("humans", jsonArray);

        return jsonObject;
    }
}
