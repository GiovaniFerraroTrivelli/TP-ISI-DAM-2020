package isi.dam.sendmeal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {
    private List<Plato> listaPlatos;
    private AppCompatActivity activity;

    public PlatoAdapter(List<Plato> listaPlatos, AppCompatActivity activity) {
        this.listaPlatos = listaPlatos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_plato, parent, false);

        PlatoViewHolder pvh = new PlatoViewHolder(v);
        // TODO: Setear acción a CardView
        v.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // do someting
            }
        });

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {
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
    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView textTituloPlato;
        TextView textDescripcionPlato;
        TextView textPrecioPlato;
        ImageView imagenPlato;

        public PlatoViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_view);
            textTituloPlato = itemView.findViewById(R.id.fila_titulo_plato);
            textDescripcionPlato = itemView.findViewById(R.id.fila_descripcion_plato);
            textPrecioPlato = itemView.findViewById(R.id.fila_precio_plato);
            imagenPlato = itemView.findViewById(R.id.imagen_plato);
        }

    }
}
