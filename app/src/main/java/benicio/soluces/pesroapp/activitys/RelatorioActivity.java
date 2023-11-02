package benicio.soluces.pesroapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.adapters.AdapterTransacao;
import benicio.soluces.pesroapp.databinding.ActivityRelatorioBinding;
import benicio.soluces.pesroapp.databinding.AdicionarTransacaoBinding;
import benicio.soluces.pesroapp.models.Produto;
import benicio.soluces.pesroapp.models.Transacao;
import benicio.soluces.pesroapp.models.Venda;
import benicio.soluces.pesroapp.utils.TransacaoSave;
import benicio.soluces.pesroapp.utils.VendaSave;

public class RelatorioActivity extends AppCompatActivity {

    private ActivityRelatorioBinding mainBinding;
    private RecyclerView r;
    private List<Transacao> transacoes = new ArrayList<>();
    private AdapterTransacao adapterTransacao;
    private Dialog dialogCadastro;
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
            dialogCadastro.show();
        });
        criarDialogCadastro();
        configurarRecycler();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configurarRecycler(){
        r = mainBinding.recyclerView;
        r.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        r.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);

        if (TransacaoSave.getTransacao(getApplicationContext()) != null){
            transacoes.addAll(TransacaoSave.getTransacao(getApplication()));
        }

        if ( !transacoes.isEmpty() ){
            mainBinding.textInfo.setVisibility(View.GONE);
        }else{
            mainBinding.textInfo.setVisibility(View.VISIBLE);
        }

        adapterTransacao = new AdapterTransacao(transacoes, getApplicationContext());
        r.setAdapter(adapterTransacao);
        calculcarFaturamento();

    }

    @SuppressLint("DefaultLocale")
    private void calculcarFaturamento(){

        float receita = 0.0f, despesa = 0.0f, faturamento = 0.0f;

        for ( Transacao transacao : transacoes){
            if ( transacao.getTipo() == 0){
                despesa +=  transacao.getValor();
            }else{
                receita += transacao.getValor();
            }
        }

        faturamento = receita - despesa;

        mainBinding.faturamenteText.setText(
                String.format("Faturamento\nR$%.2f", faturamento)
        );

        mainBinding.receitaText.setText(
                String.format(
                        "Receita\nR$%.2f", receita
                )
        );

        mainBinding.despesaText.setText(
                String.format(
                        "Despesa\nR$%.2f", despesa
                )
        );
    }

    @SuppressLint("NotifyDataSetChanged")
    private void criarDialogCadastro(){
        AlertDialog.Builder b = new AlertDialog.Builder(RelatorioActivity.this);
        AdicionarTransacaoBinding transacaoBinding = AdicionarTransacaoBinding.inflate(getLayoutInflater());

        transacaoBinding.cadastrar.setOnClickListener( view -> {
            float valor;
            String data, descricao;
            int isReceita = transacaoBinding.receitaRadio.isChecked() ? 1 : 0;

            data = transacaoBinding.dataField.getEditText().getText().toString();
            descricao = transacaoBinding.descricaoField.getEditText().getText().toString();
            valor = Float.parseFloat(
                    transacaoBinding.valorField.getEditText().getText().toString()
            );

            transacoes.add(
                    new Transacao(data, descricao, valor, isReceita)
            );

            TransacaoSave.saveTransacao(
                    getApplicationContext(),
                    transacoes
            );
            Toast.makeText(this, "Transação salva!", Toast.LENGTH_SHORT).show();
            dialogCadastro.dismiss();

            transacaoBinding.dataField.getEditText().setText("");
            transacaoBinding.descricaoField.getEditText().setText("");
            calculcarFaturamento();
            adapterTransacao.notifyDataSetChanged();
        });

        b.setView(transacaoBinding.getRoot());
        b.setTitle("Adicionar transação");
        dialogCadastro = b.create();
    }
}