package br.com.hemobile.hecommomclasses_android.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public abstract class JSONHelper {

    public static JSONObject merge(JSONObject object1, JSONObject object2) throws JSONException {
        JSONObject merged = new JSONObject();

        merged = copyProperties(merged, object1);
        merged = copyProperties(merged, object2);

        return merged;
    }

    public static JSONObject copyProperties(JSONObject target, JSONObject source) throws JSONException {
        Iterator<String> iterator = source.keys();

        while(iterator.hasNext()) {
            String key = iterator.next();

            target.put(key, source.get(key));
        }

        return target;
    }
}
