package benicio.soluces.pesroapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.activitys.CadastrosActivity;
import benicio.soluces.pesroapp.models.Empresa;
import benicio.soluces.pesroapp.models.Venda;

public class AdapterEmpresa extends RecyclerView.Adapter<AdapterEmpresa.MyViewHolder> {
    List<Empresa> empresas;
    Context c;

    int t = 0;

    public AdapterEmpresa(List<Empresa> empresas, Context c, int t ) {
        this.empresas = empresas;
        this.c = c;
        this.t = t;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exibicao_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Empresa empresa = empresas.get(position);

        holder.infos.setText(
                empresa.toString()
        );

        if ( t == 1 ){
            holder.itemView.getRootView().setOnClickListener( view -> {
                Intent i = new Intent(c, CadastrosActivity.class);
                i.putExtra("secao", "Vendas");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(i);
            });
        }
    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView infos;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            infos = itemView.findViewById(R.id.text_infos);
        }
    }
}
