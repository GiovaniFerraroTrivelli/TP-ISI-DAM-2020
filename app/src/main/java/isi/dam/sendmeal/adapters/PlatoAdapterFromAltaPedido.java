package isi.dam.sendmeal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;
import java.util.List;

import isi.dam.sendmeal.R;
import isi.dam.sendmeal.activities.PedidoActivity;
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
    public void onBindViewHolder(@NonNull final PlatoFromPedidoViewHolder holder, int position) {
        Plato plato = listaPlatos.get(position);

        holder.textTituloPlato.setText(plato.getTitulo());
        holder.textDescripcionPlato.setText(plato.getDescripcion());

        DecimalFormat df = new DecimalFormat("#.00");

        holder.textPrecioPlato.setText("$" + df.format(plato.getPrecio()));
        holder.hiddenIdPlato.setText(plato.getId().toString());
        holder.textTituloPlato.setTag(position);
        holder.textDescripcionPlato.setTag(position);
        holder.textPrecioPlato.setTag(position);
        holder.imagenPlato.setTag(position);
        holder.botonAgregarPlato.setTag(position);
        holder.imagenPlato.setImageResource(R.drawable.food_placeholder);

        try{
            StorageReference gsReference = FirebaseStorage.getInstance().getReference("images/"+plato.getTitulo()+".jpg");

            final long THREE_MEGABYTE = 3 * 1024 * 1024;
            gsReference.getBytes(THREE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    // Exito
                    Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    DisplayMetrics dm = new DisplayMetrics();
                    ((Activity) activity).getWindowManager().getDefaultDisplay().getMetrics(dm);

                    holder.imagenPlato.setMinimumHeight(dm.heightPixels);
                    holder.imagenPlato.setMinimumWidth(dm.widthPixels);
                    holder.imagenPlato.setImageBitmap(bm);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    holder.imagenPlato = activity.findViewById(R.id.imagen_plato);
                    holder.imagenPlato.setImageResource(R.drawable.food_placeholder);
                }
            });
        } catch (Exception e){
            holder.imagenPlato = activity.findViewById(R.id.imagen_plato);
            holder.imagenPlato.setImageResource(R.drawable.food_placeholder);
        }


    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public static class PlatoFromPedidoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView textTituloPlato;
        TextView textDescripcionPlato;
        TextView hiddenIdPlato;
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
            hiddenIdPlato = itemView.findViewById(R.id.id_plato);

            botonAgregarPlato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Plato p = new Plato();
                    p.setTitulo((String) textTituloPlato.getText());
                    p.setPrecio(Double.parseDouble(((String) textPrecioPlato.getText()).substring(1)));
                    p.setDescripcion((String) textDescripcionPlato.getText());
                    p.setId(Long.parseLong((String) hiddenIdPlato.getText()));

                    PedidoActivity.addToListaPlatosPedido(p);

                    Intent intent = new Intent();
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                }
            });
        }


    }
}

