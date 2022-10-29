package usuario.datos;

import java.sql.*;
import java.util.*;
import usuario.domain.Pelicula;

public class PeliculaDao {
        private static final String SQL_SELECT_PEL = "SELECT * FROM peliculas.peliculas WHERE idusuario=?;";
        private static final String SQL_SELECT_PELB = "SELECT * FROM peliculas.peliculas WHERE nombre_pelicula=? AND idusuario=?";
        private static final String SQL_INSERT_PEL = "INSERT INTO peliculas.peliculas (nombre_pelicula,duracion,genero,descripcion,idusuario) VALUES (?,?,?,?,?)";
        private static final String SQL_UPDATED_PEL = "UPDATE peliculas.peliculas SET idnombrepelicula=?, nombre_pelicula=?,duracion=?,genero=?,descripcion=? WHERE nombre_pelicula=?";
        private static final String SQL_DELETE_PEL = "DELETE FROM peliculas.peliculas WHERE idnombrepelicula=?";
        private Connection ConexionTransaccional;
        public PeliculaDao() {
        }
        public PeliculaDao(Connection conexionTransaccional) {
            ConexionTransaccional = conexionTransaccional;
        }
        public List<Pelicula> seleccionar(int id) throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            List<Pelicula> peliculas = new ArrayList();
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_SELECT_PEL);
                stmt.setInt(1, id);
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
        public List<Pelicula> seleccionarBuscar(String name, int id) throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            List<Pelicula> peliculas = new ArrayList();
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_SELECT_PELB);
                stmt.setString(1, name);
                stmt.setInt(2, id);
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
                stmt.setInt(5, pelicula.getIdUsuario());
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
                stmt.setInt(1, pelicula.getActualizarId());
                stmt.setString(2, pelicula.getNombrePelicula());
                stmt.setInt(3, pelicula.getDuracion());
                stmt.setString(4, pelicula.getGenero());
                stmt.setString(5, pelicula.getDescripcion());
                stmt.setString(6, pelicula.getNombrePelicula());
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
