package benicio.soluces.pesroapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.List;

import benicio.soluces.pesroapp.models.Empresa;

public class EmpresaSave {
    private static final String PREF_NAME = "empresas_pref";
    private static final String KEY = "empresas";

    public static void saveEmpresas(Context c , List<Empresa> empresas){
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(empresas);

        editor.putString(KEY, json);
        editor.apply();
    }

    public static List<Empresa> getEmpresas(Context c){
        try{
            SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(KEY, "");
            Type type = new TypeToken<List<Empresa>>(){}.getType();
            return gson.fromJson(json, type);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
