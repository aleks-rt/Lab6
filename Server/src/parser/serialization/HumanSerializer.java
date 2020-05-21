package parser.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import elements.HumanBeing;

import java.lang.reflect.Type;


public class HumanSerializer implements JsonSerializer<HumanBeing> {

    @Override
    public JsonElement serialize(HumanBeing human, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("name", human.getName());
        jsonObject.add("coordinates", jsonSerializationContext.serialize(human.getCoordinates()));
        jsonObject.addProperty("creationDate", human.getCreationDate().toString());
        jsonObject.addProperty("realHero", human.getRealHero());
        jsonObject.addProperty("hasToothpick", human.getHasToothpick());
        jsonObject.addProperty("impactSpeed", human.getImpactSpeed());
        jsonObject.addProperty("weaponType", human.getWeaponType().toString());
        jsonObject.addProperty("mood", human.getMood().toString());
        jsonObject.add("car", jsonSerializationContext.serialize(human.getCar()));

        return jsonObject;
    }
}
