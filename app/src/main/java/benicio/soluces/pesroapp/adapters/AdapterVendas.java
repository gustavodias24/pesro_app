package benicio.soluces.pesroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.soluces.pesroapp.R;
import benicio.soluces.pesroapp.models.Venda;

public class AdapterVendas extends RecyclerView.Adapter<AdapterVendas.MyViewHolder> {
    List<Venda> vendas;
    Context c;

    public AdapterVendas(List<Venda> vendas, Context c) {
        this.vendas = vendas;
        this.c = c;
    }

    @NonNull
    @Override
    public AdapterVendas.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterVendas.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exibicao_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Venda venda = vendas.get(position);

        holder.infos.setText(
                venda.toString()
        );
    }

    @Override
    public int getItemCount() {
        return vendas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView infos;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            infos = itemView.findViewById(R.id.text_infos);
        }
    }
}
