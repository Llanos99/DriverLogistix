package com.aeternal.driverservice.config;

import com.aeternal.driverservice.model.EntityChange;
import com.aeternal.driverservice.model.Log;
import com.google.gson.*;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.json.JsonTypeAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private JSONObject convertToJson(String entityName, Diff diff) throws JSONException {
        List<Change> changes = diff.getChanges();
        JSONObject json = new JSONObject();
        json.put("entityName", entityName);
        JSONArray changesArray = new JSONArray();
        for (Change change : changes) {
            /* Handle updates */
            if (change instanceof ValueChange valueChange) {
                JSONObject fieldChange = new JSONObject();
                fieldChange.put("field", valueChange.getPropertyName());
                fieldChange.put("oldValue", valueChange.getLeft());
                fieldChange.put("newValue", valueChange.getRight());
                changesArray.put(fieldChange);
            }
            /* Handle creation */
            else if (change instanceof NewObject newObject) {
                JSONObject fieldChange = new JSONObject();
                fieldChange.put("field", entityName);
                fieldChange.put("newValue", newObject.getAffectedObject());
                changesArray.put(fieldChange);
            }
            /* Handle deletion*/
            else if (change instanceof ObjectRemoved objectRemoved) {
                JSONObject fieldChange = new JSONObject();
                fieldChange.put("field", entityName);
                fieldChange.put("oldValue", objectRemoved.getAffectedObject());
                changesArray.put(fieldChange);
            }
        }
        json.put("changes", changesArray);
        return json;
    }

}
