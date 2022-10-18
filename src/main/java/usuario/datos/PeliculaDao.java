package usuario.datos;

import java.sql.*;
import java.util.*;
import usuario.domain.Pelicula;

public class PeliculaDao {
        private static final String SQL_SELECT_PEL = "SELECT * FROM peliculas.peliculas;";
        private static final String SQL_INSERT_PEL = "INSERT INTO peliculas.peliculas (nombre_pelicula,duracion,genero,descripcion) VALUES (?,?,?,?)";
        private static final String SQL_UPDATED_PEL = "UPDATE peliculas.peliculas SET nombre_pelicula=?,duracion=?,genero=?,descripcion=? WHERE idnombrepelicula=?";
        private static final String SQL_DELETE_PEL = "DELETE FROM peliculas.pelicula WHERE idnombrepelicula=?";
        private Connection ConexionTransaccional;
        public PeliculaDao() {
        }
        public PeliculaDao(Connection conexionTransaccional) {
            ConexionTransaccional = conexionTransaccional;
        }
        public List<Pelicula> seleccionar() throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Pelicula peliculaL = null;
            List<Pelicula> peliculas = new ArrayList();
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_SELECT_PEL);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    int idNombrePelicula = rs.getInt("idnombrepelicula");
                    String nombrepelicula = rs.getString("nombre_pelicula");
                    int duracion = rs.getInt("duracion");
                    String genero = rs.getString("genero");
                    String descripcion = rs.getString("descripcion");
                    Pelicula Pelicula = new Pelicula(idNombrePelicula,nombrepelicula,duracion,genero,descripcion);
                    peliculas.add(Pelicula);
                }
            }  finally {
                try {
                    Conexion.close(rs);
                    Conexion.close(stmt);
                    if(ConexionTransaccional==null){
                        Conexion.close(conn);
                    }
                    
                } catch (SQLException e) {
    
                    e.printStackTrace();
                }
    
            }
            return peliculas;
    
        }
        public int insertar(Pelicula pelicula) throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            int registro = 0;
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_INSERT_PEL);
                stmt.setString(1, pelicula.getNombrePelicula());
                stmt.setInt(2, pelicula.getDuracion());
                stmt.setString(3, pelicula.getGenero());
                stmt.setString(4, pelicula.getDescripcion());
                registro = stmt.executeUpdate();
            }  finally {
    
                try {
                    Conexion.close(stmt);
                    if(ConexionTransaccional==null){
                        Conexion.close(conn);
                    }
                } catch (SQLException e) {
    
                    e.printStackTrace();
                }
            }
            return registro;
    
        }
        public int actualizar(Pelicula pelicula) throws SQLException{
            Connection conn = null;
            PreparedStatement stmt = null;
            int registro=0;
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_UPDATED_PEL);
                stmt.setString(1, pelicula.getNombrePelicula());
                stmt.setInt(2, pelicula.getDuracion());
                stmt.setString(3, pelicula.getGenero());
                stmt.setString(4, pelicula.getDescripcion());
                stmt.setInt(5, pelicula.getIdNombrePelicula());
                registro=stmt.executeUpdate();
            } finally{
                try {
                    Conexion.close(stmt);
                    if(ConexionTransaccional==null){
                        Conexion.close(conn);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
            }
            return registro;
    
        }
        public int eliminar (Pelicula pelicula) throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            int registro=0;
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_DELETE_PEL);
                stmt.setInt(1, pelicula.getIdNombrePelicula());
                registro=stmt.executeUpdate();
            } finally{
                try {
                    Conexion.close(stmt);
                    if(ConexionTransaccional==null){
                        Conexion.close(conn);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
            }
            return registro;
            
        }
}
