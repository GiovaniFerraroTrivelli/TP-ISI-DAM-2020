package isi.dam.sendmeal;

import java.util.List;

import isi.dam.sendmeal.model.Plato;

interface OnPlatoResultCallback {
    void onResult(List<Plato> plato);
}