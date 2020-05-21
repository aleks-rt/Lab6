package parser.deserialization;

import com.google.gson.*;
import elements.*;

import java.lang.reflect.Type;
import java.time.LocalDate;


public class HumanDeserializer implements JsonDeserializer<HumanBeing> {

    @Override
    public HumanBeing deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        HumanBeing human = new HumanBeing(jsonObject.get("name").getAsString(),
                (Coordinates) jsonDeserializationContext.deserialize(jsonObject.get("coordinates"), Coordinates.class),
                jsonObject.get("realHero").getAsBoolean(),
                jsonObject.get("hasToothpick").getAsBoolean(),
                jsonObject.get("impactSpeed").getAsFloat(),
                WeaponType.valueOf(jsonObject.get("weaponType").getAsString()),
                Mood.valueOf(jsonObject.get("mood").getAsString()),
                (Car) jsonDeserializationContext.deserialize(jsonObject.get("car"), Car.class));
                human.setCreationDate(LocalDate.parse(jsonObject.get("creationDate").getAsString()));
        if(human.getCoordinates() == null || human.getCar() == null)
            throw new JsonParseException("Некорректный json файл!");
        return human;
    }
}
