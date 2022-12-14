package usuario.domain;

public class Pelicula {
    private int idNombrePelicula;
    private String NombrePelicula;
    private int duracion;
    private String genero;
    private String descripcion;
    private int actualizarId;
    private int idUsuario;
    
    public Pelicula(int idNombrePelicula) {
        this.idNombrePelicula = idNombrePelicula;
    }

    public Pelicula(String nombrePelicula, int duracion, String genero, String descripcion,int idUsuario) {
        NombrePelicula = nombrePelicula;
        this.duracion = duracion;
        this.genero = genero;
        this.descripcion = descripcion;
        this.idUsuario=idUsuario;
    }

    public Pelicula(int idNombrePelicula, String nombrePelicula, int duracion, String genero, String descripcion) {
        this.idNombrePelicula = idNombrePelicula;
        NombrePelicula = nombrePelicula;
        this.duracion = duracion;
        this.genero = genero;
        this.descripcion = descripcion;
    }
    

    public Pelicula(int idNombrePelicula, String nombrePelicula, int duracion, String genero, String descripcion,
            int actualizarId) {
        this.idNombrePelicula = idNombrePelicula;
        NombrePelicula = nombrePelicula;
        this.duracion = duracion;
        this.genero = genero;
        this.descripcion = descripcion;
        this.actualizarId = actualizarId;
    }

    public int getIdNombrePelicula() {
        return idNombrePelicula;
    }

    public void setIdNombrePelicula(int idNombrePelicula) {
        this.idNombrePelicula = idNombrePelicula;
    }

    public String getNombrePelicula() {
        return NombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        NombrePelicula = nombrePelicula;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "pelicula [idNombrePelicula=" + idNombrePelicula + ", NombrePelicula=" + NombrePelicula + ", duracion="
                + duracion + ", genero=" + genero + ", descripcion=" + descripcion + "]";
    }

    public int getActualizarId() {
        return actualizarId;
    }

    public void setActualizarId(int actualizarId) {
        this.actualizarId = actualizarId;
    }
    
    
    
}
