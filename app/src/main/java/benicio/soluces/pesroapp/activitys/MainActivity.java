package benicio.soluces.pesroapp.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().setTitle("Área inicial");

        mainBinding.motoristas.setOnClickListener(this);
        mainBinding.empresas.setOnClickListener(this);
        mainBinding.vendas.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), CadastrosActivity.class);

        if ( view.getId() == mainBinding.motoristas.getId() ){
            i.putExtra("secao", "Motoristas");
        }else if ( view.getId() == mainBinding.empresas.getId() ){
            i.putExtra("secao", "Empresas");
        }else if ( view.getId() == mainBinding.vendas.getId() ){
            i.putExtra("secao", "Vendas");
        }
        startActivity(i);
    }
}