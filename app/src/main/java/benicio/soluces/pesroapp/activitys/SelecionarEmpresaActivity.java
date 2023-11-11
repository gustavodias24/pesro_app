package benicio.soluces.pesroapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.adapters.AdapterEmpresa;
import benicio.soluces.pesroapp.adapters.AdapterMotorista;
import benicio.soluces.pesroapp.adapters.AdapterVendas;
import benicio.soluces.pesroapp.databinding.ActivityCadastroMainBinding;
import benicio.soluces.pesroapp.databinding.ActivitySelecionarEmpresaBinding;
import benicio.soluces.pesroapp.models.Empresa;
import benicio.soluces.pesroapp.utils.EmpresaSave;
import benicio.soluces.pesroapp.utils.MotoristaSave;
import benicio.soluces.pesroapp.utils.VendaSave;

public class SelecionarEmpresaActivity extends AppCompatActivity {
    
    private ActivitySelecionarEmpresaBinding mainBinding;
    private AdapterEmpresa adapterEmpresa;
    private List<Empresa> empresas = new ArrayList<>();
    private RecyclerView r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivitySelecionarEmpresaBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().setTitle("Selecionar empresa");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        configurarRecyclerGeneric();
        
    }

    private void configurarRecyclerGeneric(){

        r = mainBinding.recycleEmpreas;
        r.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        r.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);


        if ( EmpresaSave.getEmpresas(getApplicationContext()) != null){
            empresas.addAll(EmpresaSave.getEmpresas(getApplicationContext()));
        }

        if (!empresas.isEmpty()){ mainBinding.textInfo.setVisibility(View.GONE);}

        adapterEmpresa = new AdapterEmpresa(
                empresas,
                getApplicationContext(),
                1
        );
        r.setAdapter(adapterEmpresa);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}