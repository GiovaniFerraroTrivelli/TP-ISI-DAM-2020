package isi.dam.sendmeal;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

import isi.dam.sendmeal.model.Plato;

public class Converters {
    @TypeConverter
    public static ArrayList<Plato> fromString(Plato plato) {
       /*JSONArray jsonArray = new JSONArray();
        for (int i=0; i < myCustomList.size(); i++) {
            jsonArray.put(myCustomList.get(i).getJSONObject());
        }*/
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Plato> list) {
        /*Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;*/
    }
}
