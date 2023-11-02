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
import benicio.soluces.pesroapp.models.Produto;
import benicio.soluces.pesroapp.models.Transacao;
import benicio.soluces.pesroapp.models.Venda;

public class AdapterTransacao extends RecyclerView.Adapter<AdapterTransacao.MyViewHolder> {
    List<Transacao> transacoes;
    Context c;

    public AdapterTransacao(List<Transacao> transacoes, Context c) {
        this.transacoes = transacoes;
        this.c = c;
    }

    @NonNull
    @Override
    public AdapterTransacao.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterTransacao.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exibicao_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransacao.MyViewHolder holder, int position) {
        Transacao venda = transacoes.get(position);

        holder.infos.setText(
                venda.toString()
        );
    }

    @Override
    public int getItemCount() {
        return transacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView infos;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            infos = itemView.findViewById(R.id.text_infos);
        }
    }
}
