package net.ivanvega.soportediferentespantallasb;

import android.app.Application;

import java.util.Vector;

public class Applicacion extends Application {
    private Vector<Libro> libros;
    private MiAdaptadorPersonalizaFiltro adaptador;

    @Override
    public void onCreate() {
        libros = Libro.ejemplosLibros();
        adaptador = new MiAdaptadorPersonalizaFiltro(this, libros);
        super.onCreate();
    }

    public MiAdaptadorPersonalizaFiltro getAdaptador() {
        return adaptador;
    }

    public Vector<Libro> getLibros() {
        return libros;
    }

}
