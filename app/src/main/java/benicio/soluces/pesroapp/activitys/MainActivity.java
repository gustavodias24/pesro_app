package benicio.soluces.pesroapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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


        mainBinding.vendas.setOnClickListener(this);
        mainBinding.cadastro.setOnClickListener(this);
        mainBinding.faturamento.setOnClickListener(this);
        mainBinding.compras.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), SelecionarEmpresaActivity.class);

        if ( view.getId() == mainBinding.vendas.getId() ){
            i.putExtra("secao", "Vendas");
        } else if ( view.getId() == mainBinding.faturamento.getId()  || view.getId() == mainBinding.compras.getId()){
            i = new Intent(getApplicationContext(), RelatorioActivity.class);
        }else if( view.getId() == mainBinding.cadastro.getId() ){
            i = new Intent(getApplicationContext(), CadastroMainActivity.class);
        }
        startActivity(i);
    }


}