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
import benicio.soluces.pesroapp.models.Motorista;

public class AdapterMotorista extends RecyclerView.Adapter<AdapterMotorista.MyViewHolder> {
        List<Motorista> motoristas;
        Context c;

public AdapterMotorista(List<Motorista> empresas, Context c) {
        this.motoristas = empresas;
        this.c = c;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exibicao_layout, parent, false));
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Motorista motorista = motoristas.get(position);

        holder.infos.setText(
        motorista.toString()
        );
        }

@Override
public int getItemCount() {
        return motoristas.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView infos;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        infos = itemView.findViewById(R.id.text_infos);
    }
}
}
