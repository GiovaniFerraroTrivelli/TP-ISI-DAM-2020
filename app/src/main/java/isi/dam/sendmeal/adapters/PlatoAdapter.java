package isi.dam.sendmeal.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import isi.dam.sendmeal.activities.ListaPlatosActivity;
import isi.dam.sendmeal.dataAccess.PlatoDao;
import isi.dam.sendmeal.model.Plato;
import isi.dam.sendmeal.repositories.PlatoRepository;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {
    private static List<Plato> listaPlatos;
    private static AppCompatActivity activity;

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
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final PlatoViewHolder holder, int position) {


        final Plato plato = listaPlatos.get(position);
        holder.textTituloPlato.setText(plato.getTitulo());
        holder.textDescripcionPlato.setText(plato.getDescripcion());
        DecimalFormat df = new DecimalFormat("#.00");
        holder.textPrecioPlato.setText("$".concat(df.format(plato.getPrecio())));
        holder.textTituloPlato.setTag(position);
        holder.textDescripcionPlato.setTag(position);
        holder.textPrecioPlato.setTag(position);
        holder.imagenPlato.setTag(position);
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

    public List<Plato> getPlatos() { return listaPlatos; }

    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView textTituloPlato;
        TextView textDescripcionPlato;
        TextView textPrecioPlato;
        ImageView imagenPlato;

        public PlatoViewHolder(@NonNull final View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_view);
            textTituloPlato = itemView.findViewById(R.id.fila_titulo_plato);
            textDescripcionPlato = itemView.findViewById(R.id.fila_descripcion_plato);
            textPrecioPlato = itemView.findViewById(R.id.fila_precio_plato);
            imagenPlato = itemView.findViewById(R.id.imagen_plato);
        }

    }
}
