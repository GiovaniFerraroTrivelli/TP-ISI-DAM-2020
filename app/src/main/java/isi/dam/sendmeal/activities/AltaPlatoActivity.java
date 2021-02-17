package isi.dam.sendmeal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import isi.dam.sendmeal.InputValidator;
import isi.dam.sendmeal.R;
import isi.dam.sendmeal.adapters.PlatoAdapter;
import isi.dam.sendmeal.model.Plato;
import isi.dam.sendmeal.repositories.PlatoRepository;

public class AltaPlatoActivity extends AppCompatActivity implements PlatoRepository.OnResultCallback, View.OnClickListener {
    Button buttonGuardar;
    EditText textTituloPlato;
    EditText textDescripcionPlato;
    EditText textPrecioPlato;
    EditText textCaloriasPlato;
    PlatoRepository repository;
    static final int CAMARA_REQUEST = 1;
    static final int GALERIA_REQUEST = 2;
    static final int CAMERA_PERMISSION_CODE = 100;
    static final int GALLERY_PERMISSION_CODE = 200;
    byte[] img;
    StorageReference platosImagesRef, storageRef = FirebaseStorage.getInstance().getReference();
    Plato plato = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_plato);
        repository = new PlatoRepository(this.getApplication(), this);

        buttonGuardar = (Button) findViewById(R.id.guardar_plato);
        buttonGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        textTituloPlato = (EditText) findViewById(R.id.TituloPlato);
        textDescripcionPlato = (EditText) findViewById(R.id.DescripcionPlato);
        textPrecioPlato = (EditText) findViewById(R.id.PrecioPlato);
        textCaloriasPlato = (EditText) findViewById(R.id.CaloriasPlato);

        String titulo = String.valueOf(textTituloPlato.getText());
        String descripcion = String.valueOf(textDescripcionPlato.getText());
        String precio = String.valueOf(textPrecioPlato.getText());
        String calorias = String.valueOf(textCaloriasPlato.getText());

        if(!InputValidator.validarAltaPlato(new String[]{titulo, descripcion, precio, calorias})) {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }

        else {
            plato = new Plato();
            plato.setTitulo(titulo);
            plato.setDescripcion(descripcion);
            plato.setPrecio(Double.parseDouble(precio));
            plato.setCalorias(Integer.parseInt(calorias));

            Log.d("plato", plato.toString());

            saveImg();
            repository.insertar(plato);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (requestCode == CAMARA_REQUEST){
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                img = baos.toByteArray();
                Log.d("PHOTO", String.valueOf(img.toString().length()));
            }
            else if(requestCode == GALERIA_REQUEST){
                Uri imageData = data.getData();
                InputStream stream;

                try {
                    stream = getContentResolver().openInputStream(imageData);
                    BufferedInputStream bis = new BufferedInputStream(stream);
                    Bitmap bitmap = BitmapFactory.decodeStream(bis);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    img = baos.toByteArray();

                    Log.d("PHOTO", String.valueOf(img.toString().length()));

                } catch (FileNotFoundException e) {}
            }
        }
    }

    private void saveImg() {

        final Context context = this;

        if(img != null){
            platosImagesRef = storageRef.child("images/"+plato.getTitulo()+".jpg");

            UploadTask uploadTask = platosImagesRef.putBytes(img);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return platosImagesRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                    } else {
                        // Fallo
                        Toast.makeText(context,"No se pudo cargar la imagen", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public void onResult(List result) {
    }

    @Override
    public void onInsert() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AltaPlatoActivity.this, "¡Plato agregado!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickUploadPhoto(View view) {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, GALLERY_PERMISSION_CODE);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERIA_REQUEST);
    }

    public void onClickTakePhoto(View view) {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMARA_REQUEST);
    }

}