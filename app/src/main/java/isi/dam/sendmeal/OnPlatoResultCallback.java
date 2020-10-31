package isi.dam.sendmeal;

import java.util.List;

import isi.dam.sendmeal.model.Plato;

public interface OnPlatoResultCallback {
    void onResult(List<Plato> plato);
}