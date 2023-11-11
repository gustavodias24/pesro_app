package benicio.soluces.pesroapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.databinding.ActivityCadastroMainBinding;
import benicio.soluces.pesroapp.databinding.ActivityMainBinding;

public class CadastroMainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityCadastroMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCadastroMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainBinding.motoristas.setOnClickListener(this);
        mainBinding.empresas.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getApplicationContext(), CadastrosActivity.class);
        if ( view.getId() == mainBinding.motoristas.getId() ){
            i.putExtra("secao", "Motoristas");
        }else if ( view.getId() == mainBinding.empresas.getId() ){
            i.putExtra("secao", "Empresas");
        }
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}