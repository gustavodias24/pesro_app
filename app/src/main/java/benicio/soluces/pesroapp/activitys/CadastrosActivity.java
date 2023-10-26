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

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import benicio.soluces.pesroapp.Produto;
import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.adapters.AdapterProduto;
import benicio.soluces.pesroapp.databinding.ActivityCadastrosBinding;
import benicio.soluces.pesroapp.databinding.AdicionarEmpresaLayoutBinding;
import benicio.soluces.pesroapp.databinding.AdicionarMotoristaLayoutBinding;
import benicio.soluces.pesroapp.databinding.AdicionarVendaBinding;

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

    @SuppressLint({"DefaultLocale", "NotifyDataSetChanged"})
    public void criarDialogCadastro(){
        AlertDialog.Builder b = new AlertDialog.Builder(CadastrosActivity.this);
//        b.setCancelable(false);
        if ( secao.equals("Motoristas")){
            b.setTitle("Cadastrar um motorista.");
            b.setView(AdicionarMotoristaLayoutBinding.inflate(getLayoutInflater()).getRoot());

        }
        if ( secao.equals("Empresas") ){
            b.setTitle("Cadastrar uma empresa.");
            b.setView(AdicionarEmpresaLayoutBinding.inflate(getLayoutInflater()).getRoot());
        }

        if ( secao.equals("Vendas") ){
            AdicionarVendaBinding viewVendas = AdicionarVendaBinding.inflate(getLayoutInflater());
            List<Produto> listaProduto = new ArrayList<>();
            AdapterProduto adapterProduto = new AdapterProduto(listaProduto, getApplicationContext());

            MaterialSpinner spinner = viewVendas.produtosField;
            spinner.setItems("Areia fina", "Areia media", "Areia grossa", "Pedrisco", "Traço", "Barro/aterro", "Brita", "Piçarro");
            spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> Snackbar.make(view,  spinner.getText().toString(), Snackbar.LENGTH_LONG).show());

            RecyclerView recyclerProdutos = viewVendas.listaProdutosRecycler;
            recyclerProdutos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerProdutos.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL));
            recyclerProdutos.setHasFixedSize(true);
            recyclerProdutos.setAdapter(adapterProduto);

            viewVendas.adicionarProdutoVenda.setOnClickListener( view -> {
                int quantidade = Integer.parseInt(viewVendas.quantidadeField.getEditText().getText().toString());
                float valor = Float.parseFloat(viewVendas.precoField.getEditText().getText().toString());
                float ValorTotal = quantidade * valor;

                listaProduto.add(new Produto(
                        spinner.getText().toString(),
                        String.format("%d", quantidade),
                        String.format("%.2f", ValorTotal)
                ));

                adapterProduto.notifyDataSetChanged();
                viewVendas.quantidadeField.getEditText().setText("");
                viewVendas.precoField.getEditText().setText("");

            });

            b.setTitle("Cadastrar uma venda.");
            b.setView(viewVendas.getRoot());
        }
        dialogCadastro = b.create();

    }
}