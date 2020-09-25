package isi.dam.sendmeal.model;

public class Plato {
    private String titulo;
    private String descripcion;
    private Integer precio;
    private Integer calorias;

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

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
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
