package benicio.soluces.pesroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.soluces.pesroapp.models.Produto;
import benicio.soluces.pesroapp.R;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    List<Produto> list;
    Context context;

    public AdapterProduto(List<Produto> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto p = list.get(position);

        holder.nome.setText(p.getNome());
        holder.quantidade.setText(String.format("x%s", p.getQuantidade()));
        holder.valor.setText(String.format("R$ %s", p.getValor()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, quantidade, valor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeProduto);
            quantidade = itemView.findViewById(R.id.quantidadeProduto);
            valor = itemView.findViewById(R.id.valorTotalProduto);
        }
    }
}
