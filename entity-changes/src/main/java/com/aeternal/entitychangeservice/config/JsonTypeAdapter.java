package com.aeternal.entitychangeservice.config;

import com.aeternal.entitychangeservice.model.EntityChange;
import com.aeternal.entitychangeservice.model.Log;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTypeAdapter implements org.javers.core.json.JsonTypeAdapter<Diff> {

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
        log.setTimeStamp(String.valueOf(LocalDateTime.now()));
        log.setChanges(changes);
        return log;
    }

    private EntityChange buildChange(ValueChange change) {
        EntityChange entityChange = new EntityChange();
        entityChange.setProperty(change.getPropertyNameWithPath());
        entityChange.setOldValue(change.getLeft());
        entityChange.setNewValue(change.getRight());
        return entityChange;
    }

}
