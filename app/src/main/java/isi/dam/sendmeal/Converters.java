package isi.dam.sendmeal;

import androidx.room.TypeConverter;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import isi.dam.sendmeal.model.Plato;

public class Converters {
    @TypeConverter
    public static ArrayList<Plato> fromString(String plato) {
        Type listType = new TypeToken<ArrayList<Plato>>() {}.getType();
        return new Gson().fromJson(plato, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Plato> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static String LatngToString(LatLng value) {
        if(value == null){
            return null;
        } else {
            Double l1=value.latitude;
            Double l2=value.longitude;
            String coordl1 = l1.toString();
            String coordl2 = l2.toString();
            return new String(coordl1 + "," + coordl2);
        }
    }

    @TypeConverter
    public static LatLng stringToLatng(String valor) {

        if(valor == null){
            return null;
        } else {
            String[] parts = valor.split(",");
            String part1 = parts[0];
            String part2 = parts[1];

            LatLng latLng = new LatLng(Double.parseDouble(part1),Double.parseDouble(part2));
            return latLng;
        }
    }
}
