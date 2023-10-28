package benicio.soluces.pesroapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.MenuItem;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.databinding.ActivityRelatorioBinding;
import benicio.soluces.pesroapp.databinding.AdicionarTransacaoBinding;

public class RelatorioActivity extends AppCompatActivity {

    private ActivityRelatorioBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        mainBinding = ActivityRelatorioBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().setTitle("Faturamento");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainBinding.cadastroFab.setOnClickListener( view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(RelatorioActivity.this);
            AdicionarTransacaoBinding transacaoBinding = AdicionarTransacaoBinding.inflate(getLayoutInflater());
            b.setView(transacaoBinding.getRoot());
            b.setTitle("Adicionar transação");
            b.create().show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}