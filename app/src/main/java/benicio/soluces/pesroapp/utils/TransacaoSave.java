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
import benicio.soluces.pesroapp.models.Transacao;

public class TransacaoSave {
    private static final String PREF_NAME = "transacao_pref";
    private static final String KEY = "transacao";

    public static void saveTransacao(Context c , List<Transacao> transacoes){
        SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(transacoes);

        editor.putString(KEY, json);
        editor.apply();
    }

    public static List<Transacao> getTransacao(Context c){
        try{
            SharedPreferences sharedPreferences = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(KEY, "");
            Type type = new TypeToken<List<Transacao>>(){}.getType();
            return gson.fromJson(json, type);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
