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

    @Override
    public String toString(){
        return this.titulo + " " + String.valueOf(this.precio) + " " + String.valueOf(this.calorias);
    }
}
