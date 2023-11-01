package benicio.soluces.pesroapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import benicio.soluces.pesroapp.models.Motorista;

public class MotoristaSave {
    private static final String PREF_NAME = "motorista_pref";
    private static final String KEY = "motorista";

    public static void saveMotoristas(Context c , List<Motorista> motoristas){
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(motoristas);

        editor.putString(KEY, json);
        editor.apply();
    }

    public static List<Motorista> getMotoristas(Context c){
        try{
            SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(KEY, "");
            Type type = new TypeToken<List<Motorista>>(){}.getType();
            return gson.fromJson(json, type);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
