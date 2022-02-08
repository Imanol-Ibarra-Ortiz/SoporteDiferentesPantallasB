package net.ivanvega.soportediferentespantallasb;

import android.content.Context;

import java.util.Vector;

public class MiAdaptadorPersonalizaFiltro extends MiAdaptadorPersonaliza{
    private Vector<Libro> vectorSinFiltro; //Vector con todos los libros
    private Vector<Integer> indiceFiltro;  //Indice en vectorSinFiltro de Cada elementode vectorLibros

    private String busqueda = "";          //Busqueda sobre autor o titulo
    private String genero = "";            //Genero seleccionado
    private boolean novedad = false;       //Si queremos ver solo novedades
    private boolean leido = false;         //Si queremos ver solo leidos

    public MiAdaptadorPersonalizaFiltro(Context contexto,
                                                    Vector<Libro> libros) {
        super(contexto, libros);
        vectorSinFiltro = libros;
        recalculaFiltro();
    }

    public MiAdaptadorPersonalizaFiltro(Vector<Libro> ejemplosLibros) {
        super(ejemplosLibros);
    }


    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda.toLowerCase();
        recalculaFiltro();
    }

    public void setGenero(String genero) {
        this.genero = genero;
        recalculaFiltro();
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
        recalculaFiltro();
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
        recalculaFiltro();
    }

    private void recalculaFiltro() {
        libros = new Vector<Libro>();
        indiceFiltro = new Vector<Integer>();
        for (int i = 0; i < vectorSinFiltro.size(); i++){
            Libro libro = vectorSinFiltro.elementAt(i);
            if ((libro.getTitulo().toLowerCase().contains(busqueda) ||
                    libro.getAutor().toLowerCase().contains(busqueda))
                    && (libro.getGenero().startsWith(genero))
                    && (!novedad || (novedad && libro.getNovedad()))
                    && (!leido || (leido && libro.getLeido()))){
                libros.add(libro);
                indiceFiltro.add(i);
            }
        }
    }

    public Libro getItem(int posicion){
        return  vectorSinFiltro.elementAt(indiceFiltro.elementAt(posicion));
    }

    public long getItemId(int posicion){
        return indiceFiltro.elementAt(posicion);
    }

    public void borrar(int posicion){
        vectorSinFiltro.remove((int)getItemId(posicion));
        recalculaFiltro();
    }

    public void  insertar(Libro libro){
        vectorSinFiltro.add(libro);
        recalculaFiltro();
    }

}
