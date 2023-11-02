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

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import benicio.soluces.pesroapp.adapters.AdapterEmpresa;
import benicio.soluces.pesroapp.adapters.AdapterMotorista;
import benicio.soluces.pesroapp.adapters.AdapterVendas;
import benicio.soluces.pesroapp.models.Empresa;
import benicio.soluces.pesroapp.models.Motorista;
import benicio.soluces.pesroapp.models.Produto;
import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.adapters.AdapterProduto;
import benicio.soluces.pesroapp.databinding.ActivityCadastrosBinding;
import benicio.soluces.pesroapp.databinding.AdicionarEmpresaLayoutBinding;
import benicio.soluces.pesroapp.databinding.AdicionarMotoristaLayoutBinding;
import benicio.soluces.pesroapp.databinding.AdicionarVendaBinding;
import benicio.soluces.pesroapp.models.Transacao;
import benicio.soluces.pesroapp.models.Venda;
import benicio.soluces.pesroapp.utils.EmpresaSave;
import benicio.soluces.pesroapp.utils.MotoristaSave;
import benicio.soluces.pesroapp.utils.TransacaoSave;
import benicio.soluces.pesroapp.utils.VendaSave;

public class CadastrosActivity extends AppCompatActivity {

    private ActivityCadastrosBinding mainBinding;
    private Bundle b;
    private String secao;
    private Dialog dialogCadastro;
    private RecyclerView recyclerGereneric;

    private AdapterEmpresa adapterEmpresa;
    private List<Empresa> empresas = new ArrayList<>();

    private AdapterMotorista adapterMotorista;
    private List<Motorista> motoristas = new ArrayList<>();

    private AdapterVendas adapterVendas;
    private List<Venda> vendas = new ArrayList<>();

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

        configurarRecyclerGeneric();
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
        b.setCancelable(false);
        if ( secao.equals("Motoristas")){
            b.setTitle("Cadastrar um motorista.");
            b.setView(configurarCadastroMotorista());

        }
        if ( secao.equals("Empresas") ){
            b.setTitle("Cadastrar uma empresa.");
            b.setView(configurarCadastroEmpresa());
        }

        if ( secao.equals("Vendas") ){
            b.setTitle("Cadastrar uma venda.");
            b.setView(configurarCadastroVenda());
        }

        dialogCadastro = b.create();

    }
    private void configurarRecyclerGeneric(){

        recyclerGereneric = mainBinding.recyclerGeneric;
        recyclerGereneric.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerGereneric.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerGereneric.setHasFixedSize(true);

        if ( secao.equals("Empresas") ) {

            if ( EmpresaSave.getEmpresas(getApplicationContext()) != null){
                empresas.addAll(EmpresaSave.getEmpresas(getApplicationContext()));
            }

            if (!empresas.isEmpty()){ mainBinding.textInfo.setVisibility(View.GONE);}

            adapterEmpresa = new AdapterEmpresa(
                    empresas,
                    getApplicationContext()
            );
            recyclerGereneric.setAdapter(adapterEmpresa);
        }else if ( secao.equals("Motoristas") ){

            if ( MotoristaSave.getMotoristas(getApplicationContext()) != null){
                motoristas.addAll(MotoristaSave.getMotoristas(getApplicationContext()));
            }

            if (!motoristas.isEmpty()){ mainBinding.textInfo.setVisibility(View.GONE);}

            adapterMotorista = new AdapterMotorista(
                    motoristas,
                    getApplicationContext()
            );

            recyclerGereneric.setAdapter(adapterMotorista);

        }else if ( secao.equals("Vendas") ){

            if ( VendaSave.getVendas(getApplicationContext()) != null){
                vendas.addAll(VendaSave.getVendas(getApplicationContext()));
            }

            if (!vendas.isEmpty()){ mainBinding.textInfo.setVisibility(View.GONE);}

            adapterVendas = new AdapterVendas(
                    vendas,
                    getApplicationContext()
            );

            recyclerGereneric.setAdapter(adapterVendas);

        }

    }
    @SuppressLint("NotifyDataSetChanged")
    private View configurarCadastroEmpresa(){
        AdicionarEmpresaLayoutBinding bindingEmpresa = AdicionarEmpresaLayoutBinding.inflate(getLayoutInflater());

        bindingEmpresa.cancelButton.setOnClickListener(view -> {
            dialogCadastro.dismiss();
        });

        bindingEmpresa.cadastroButton.setOnClickListener( view -> {
            String nome, cnpj, funcionarios, whatsapp, endereco = "";

            nome = bindingEmpresa.nomeField.getEditText().getText().toString();
            cnpj = bindingEmpresa.cnpjField.getEditText().getText().toString();
            funcionarios = bindingEmpresa.funcionariosField.getEditText().getText().toString();
            whatsapp = bindingEmpresa.WhatsAppField.getEditText().getText().toString();
            endereco = bindingEmpresa.EnderecoField.getEditText().getText().toString();

            if ( nome.isEmpty() || cnpj.isEmpty() || funcionarios.isEmpty() || whatsapp.isEmpty() || endereco.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            }else{
                empresas.add(new Empresa(nome, cnpj, funcionarios, whatsapp, endereco));

                EmpresaSave.saveEmpresas(
                        getApplicationContext(),
                        empresas

                );
                if (!empresas.isEmpty()){ mainBinding.textInfo.setVisibility(View.GONE);}
                dialogCadastro.dismiss();
                bindingEmpresa.nomeField.getEditText().setText("");
                bindingEmpresa.cnpjField.getEditText().setText("");
                bindingEmpresa.funcionariosField.getEditText().setText("");
                bindingEmpresa.WhatsAppField.getEditText().setText("");
                bindingEmpresa.EnderecoField.getEditText().setText("");
                empresas.clear();
                empresas.addAll(EmpresaSave.getEmpresas(getApplicationContext()));
                adapterEmpresa.notifyDataSetChanged();
                Toast.makeText(this, "Empresa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
        return bindingEmpresa.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private View configurarCadastroMotorista(){
        AdicionarMotoristaLayoutBinding motoristaBinding = AdicionarMotoristaLayoutBinding.inflate(getLayoutInflater());

        motoristaBinding.cancelar.setOnClickListener( view -> {
            dialogCadastro.dismiss();
        });


        motoristaBinding.cadastrar.setOnClickListener( view -> {
            String nome, rg, cpf, whatsapp, endereco, empresaParticular;

            nome = motoristaBinding.nomeField.getEditText().getText().toString();
            rg = motoristaBinding.rgField.getEditText().getText().toString();
            cpf = motoristaBinding.cpfField.getEditText().getText().toString();
            whatsapp = motoristaBinding.WhatsAppField.getEditText().getText().toString();
            endereco = motoristaBinding.EnderecoField.getEditText().getText().toString();
            empresaParticular = motoristaBinding.EmpresaParticularField.getEditText().getText().toString();

            motoristas.add(new Motorista(
                    nome, rg, cpf, whatsapp, endereco, empresaParticular
            ));

            MotoristaSave.saveMotoristas(
                    getApplicationContext(),
                    motoristas
            );
            adapterMotorista.notifyDataSetChanged();

            motoristaBinding.nomeField.getEditText().setText("");
            motoristaBinding.rgField.getEditText().setText("");
            motoristaBinding.cpfField.getEditText().setText("");
            motoristaBinding.WhatsAppField.getEditText().setText("");;
            motoristaBinding.EnderecoField.getEditText().setText("");
            motoristaBinding.EmpresaParticularField.getEditText().setText("");

            dialogCadastro.dismiss();
            mainBinding.textInfo.setVisibility(View.GONE);
            Toast.makeText(this, "Motorista cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        });


        return motoristaBinding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private View configurarCadastroVenda(){
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

        viewVendas.cancelar.setOnClickListener( view -> {
            dialogCadastro.dismiss();
        });

        viewVendas.cadastrar.setOnClickListener( view -> {
            String dataVenda, dataRetirada, localRetirada;

            dataVenda = viewVendas.dataVendaField.getEditText().getText().toString();
            dataRetirada = viewVendas.dataRetiradaField.getEditText().getText().toString();
            localRetirada = viewVendas.localField.getEditText().getText().toString();

            Venda venda = new Venda( dataVenda, dataRetirada, localRetirada, listaProduto);
            vendas.add(venda);

            cadastrarVendaFaturamento(venda);

            VendaSave.saveVendas(
                    getApplicationContext(),
                    vendas
            );
            mainBinding.textInfo.setVisibility(View.GONE);
            dialogCadastro.dismiss();
            adapterVendas.notifyDataSetChanged();

            viewVendas.dataVendaField.getEditText().setText("");
            viewVendas.dataRetiradaField.getEditText().setText("");
           viewVendas.localField.getEditText().setText("");


        });

        return  viewVendas.getRoot();
    }

    private void cadastrarVendaFaturamento(Venda venda){
        List<Transacao> transacoes = TransacaoSave.getTransacao(getApplicationContext()) == null
                ? new ArrayList<>(): TransacaoSave.getTransacao(getApplicationContext());

        Transacao transacaoVenda = new Transacao();
        transacaoVenda.setData(venda.getDataVenda());
        transacaoVenda.setDescricao("Venda de produtos.");
        transacaoVenda.setTipo(1);

        float valor = 0.0f;
        for (Produto produto : venda.getListaProdutos()){
            valor += Float.parseFloat(produto.getValor().replace(",", "."));
        }
        transacaoVenda.setValor(valor);

        if ( !transacoes.contains(transacaoVenda)){
            transacoes.add(transacaoVenda);
        }

        TransacaoSave.saveTransacao(getApplicationContext(), transacoes);
    }

}