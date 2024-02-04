package com.aeternal.driverservice.config;

import com.aeternal.driverservice.model.EntityChange;
import com.aeternal.driverservice.model.Log;
import com.google.gson.*;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.json.JsonTypeAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomJsonTypeAdapter implements JsonTypeAdapter<Diff> {

    @Override
    public Diff fromJson(JsonElement jsonElement, JsonDeserializationContext jsonDeserializationContext) {
        return null;
    }

    @Override
    public JsonElement toJson(Diff diff, JsonSerializationContext jsonSerializationContext) {
        Log log = buildLog(diff);
        return jsonSerializationContext.serialize(log);
    }

    @Override
    public List<Class> getValueTypes() {
        return Arrays.asList(Diff.class, ValueChange.class);
    }

    private Log buildLog(Diff diff) {
        Log log = new Log();
        List<EntityChange> changes = new ArrayList<>();
        diff.getChanges().forEach(change -> {
            if (change instanceof ValueChange) {
                changes.add(buildChange((ValueChange) change));
            }
        });
        log.setTimestamp(String.valueOf(LocalDateTime.now()));
        log.setChanges(changes);
        return log;
    }

    private EntityChange buildChange(ValueChange change) {
        EntityChange entityChange = new EntityChange();
        entityChange.setProperty(change.getPropertyName());
        entityChange.setLeft((String) change.getLeft());
        entityChange.setRight((String) change.getRight());
        return entityChange;
    }

}
