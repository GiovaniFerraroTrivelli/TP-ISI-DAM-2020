package isi.dam.sendmeal;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

public class PlatoAdapterFromAltaPedido extends RecyclerView.Adapter<PlatoAdapterFromAltaPedido.PlatoFromPedidoViewHolder> {
    private List<Plato> listaPlatos;
    private static AppCompatActivity activity;

    public PlatoAdapterFromAltaPedido(List<Plato> listaPlatos, AppCompatActivity activity) {
        this.listaPlatos = listaPlatos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PlatoFromPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_plato_from_alta_pedido, parent, false);

        PlatoFromPedidoViewHolder pvh = new PlatoFromPedidoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoFromPedidoViewHolder holder, int position) {
        Plato plato = listaPlatos.get(position);

        holder.textTituloPlato.setText(plato.getTitulo());
        holder.textDescripcionPlato.setText(plato.getDescripcion());

        DecimalFormat df = new DecimalFormat("#.00");

        holder.textPrecioPlato.setText("$" + df.format(plato.getPrecio()));
        holder.imagenPlato.setImageResource(R.drawable.sopa_maruchan);

        holder.textTituloPlato.setTag(position);
        holder.textDescripcionPlato.setTag(position);
        holder.textPrecioPlato.setTag(position);
        holder.imagenPlato.setTag(position);
        holder.botonAgregarPlato.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public static class PlatoFromPedidoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView textTituloPlato;
        TextView textDescripcionPlato;
        TextView textPrecioPlato;
        ImageView imagenPlato;
        Button botonAgregarPlato;

        public PlatoFromPedidoViewHolder(@NonNull final View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_view);
            textTituloPlato = itemView.findViewById(R.id.fila_titulo_plato);
            textDescripcionPlato = itemView.findViewById(R.id.fila_descripcion_plato);
            textPrecioPlato = itemView.findViewById(R.id.fila_precio_plato);
            imagenPlato = itemView.findViewById(R.id.imagen_plato);
            botonAgregarPlato = itemView.findViewById(R.id.agregar_plato_a_pedido);

            botonAgregarPlato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Plato p = new Plato();
                    p.setTitulo((String) textTituloPlato.getText());
                    p.setPrecio(Double.parseDouble(((String) textPrecioPlato.getText()).substring(1)));
                    PedidoActivity.addToListaPlatosPedido(p);

                    Intent intent = new Intent();
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                }
            });
        }


    }
}

