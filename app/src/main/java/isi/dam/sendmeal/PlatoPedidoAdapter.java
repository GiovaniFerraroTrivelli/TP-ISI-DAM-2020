package isi.dam.sendmeal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

public class PlatoPedidoAdapter extends RecyclerView.Adapter<PlatoPedidoAdapter.PlatoViewHolder> {
    private static AppCompatActivity activity;
    private List<Plato> listaPlatos;

    public PlatoPedidoAdapter(List<Plato> listaPlatos, AppCompatActivity activity) {
        this.listaPlatos = listaPlatos;
        PlatoPedidoAdapter.activity = activity;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_plato_pedido, parent, false);

        PlatoViewHolder pvh = new PlatoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
        Plato plato = listaPlatos.get(position);
        holder.textTituloPlato.setText(plato.getTitulo());
        DecimalFormat df = new DecimalFormat("#.00");
        holder.textPrecioPlato.setText("$" + df.format(plato.getPrecio()));
        holder.textTituloPlato.setTag(position);
        holder.textPrecioPlato.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        TextView textTituloPlato;
        TextView textPrecioPlato;

        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);
            textTituloPlato = itemView.findViewById(R.id.fila_titulo_plato);
            textPrecioPlato = itemView.findViewById(R.id.fila_precio_plato);
        }
    }
}
