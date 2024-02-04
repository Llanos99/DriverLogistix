package com.aeternal.driverservice.config;

import com.google.gson.*;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.json.JsonTypeAdapter;

import java.util.Arrays;
import java.util.List;

public class CustomJsonTypeAdapter implements JsonTypeAdapter<Diff> {

    @Override
    public Diff fromJson(JsonElement jsonElement, JsonDeserializationContext jsonDeserializationContext) {
        return null;
    }

    @Override
    public JsonElement toJson(Diff diff, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        diff.getChanges().forEach(change -> {
            if (change instanceof ValueChange) {
                jsonArray.add(buildChangeJson((ValueChange) change));
            }
        });
        jsonObject.add("changes", jsonArray);
        return jsonObject;
    }

    @Override
    public List<Class> getValueTypes() {
        return Arrays.asList(Diff.class, ValueChange.class);
    }

    private JsonObject buildChangeJson(ValueChange change) {
        JsonObject changeJson = new JsonObject();
        changeJson.addProperty("property", change.getPropertyName());
        changeJson.add("left", serialize(change.getLeft()));
        changeJson.add("right", serialize(change.getRight()));
        return changeJson;
    }

    private JsonElement serialize(Object value) {
        return new Gson().toJsonTree(value);
    }
}
