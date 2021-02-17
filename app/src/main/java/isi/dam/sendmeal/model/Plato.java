package isi.dam.sendmeal.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Plato {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String titulo;
    public String descripcion;
    public Double precio;
    public Integer calorias;
    public String foto;

    public Plato() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Plato{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", calorias=" + calorias +
                '}';
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto(){
        return foto;
    }
}
