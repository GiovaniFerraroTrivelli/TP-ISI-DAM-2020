package isi.dam.sendmeal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import isi.dam.sendmeal.R;
import isi.dam.sendmeal.model.Plato;

public class PlatoPedidoAdapter extends RecyclerView.Adapter<PlatoPedidoAdapter.PlatoPedidoViewHolder> {
    private static AppCompatActivity activity;
    private List<Plato> listaPlatos;

    public PlatoPedidoAdapter(List<Plato> listaPlatos, AppCompatActivity activity) {
        this.listaPlatos = listaPlatos;
        PlatoPedidoAdapter.activity = activity;
    }

    @NonNull
    @Override
    public PlatoPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_plato_pedido, parent, false);

        PlatoPedidoViewHolder pvh = new PlatoPedidoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoPedidoViewHolder holder, int position) {
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

    public static class PlatoPedidoViewHolder extends RecyclerView.ViewHolder {
        TextView textTituloPlato;
        TextView textPrecioPlato;

        public PlatoPedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            textTituloPlato = itemView.findViewById(R.id.titulo_plato_pedido);
            textPrecioPlato = itemView.findViewById(R.id.precio_plato_pedido);
        }
    }

}
