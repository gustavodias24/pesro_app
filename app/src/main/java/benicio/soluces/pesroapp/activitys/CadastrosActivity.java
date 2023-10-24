package benicio.soluces.pesroapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.databinding.ActivityCadastrosBinding;
import benicio.soluces.pesroapp.databinding.AdicionarMotoristaLayoutBinding;

public class CadastrosActivity extends AppCompatActivity {

    private ActivityCadastrosBinding mainBinding;
    private Bundle b;
    private String secao;
    private Dialog dialogCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);
        mainBinding = ActivityCadastrosBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        b = getIntent().getExtras();
        secao = b.getString("secao", "");
        getSupportActionBar().setTitle(secao);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainBinding.textInfo.setText(
                String.format("%s não cadastrados.", secao)
        );

        criarDialogCadastro();
        mainBinding.cadastroFab.setOnClickListener( view -> {
            dialogCadastro.show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void criarDialogCadastro(){
        AlertDialog.Builder b = new AlertDialog.Builder(CadastrosActivity.this);
        if ( secao.equals("Motoristas")){
            b.setTitle("Cadastrar um motorista.");
            b.setView(AdicionarMotoristaLayoutBinding.inflate(getLayoutInflater()).getRoot());

        }
        dialogCadastro = b.create();

    }
}