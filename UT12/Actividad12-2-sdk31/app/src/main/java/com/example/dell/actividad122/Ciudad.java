package com.example.dell.actividad122;

/**
 * Created by alumnom on 24/04/2018.
 */

public class Ciudad {
    String nombre, pais, poblacion, latitud, longitud;
    int imagen;
    int likes;

    public Ciudad(String nombre, String pais, String poblacion, String latitud, String longitud, int imagen){
        this.nombre = nombre;
        this.pais = pais;
        this.poblacion = poblacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagen = imagen;
    }

    public Ciudad() { } //Constructor por defecto

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String lat) {
        this.latitud = lat;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }


}
