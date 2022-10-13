package usuario.datos;

import java.sql.*;
import java.util.*;
import usuario.domain.Pelicula;

public class PeliculaDao {
        private static final String SQL_SELECT_USU = "SELECT * FROM peliculas.peliculas;";
        private static final String SQL_INSERT_USU = "INSERT INTO peliculas.usuarios (first_name,last_name,address,contrasena,nameusuario) VALUES (?,?,?,?,?)";
        private static final String SQL_UPDATED_USU = "UPDATE peliculas.usuarios SET first_name=?,last_name=?,address=?,contrasena=?,nameusuario=? WHERE idUsuario=?";
        private static final String SQL_DELETE_USU = "DELETE FROM peliculas.usuarios WHERE idUsuario=?";
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
                stmt = conn.prepareStatement(SQL_SELECT_USU);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    int idusuario = rs.getInt("idUsuario");
                    String name = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String address = rs.getString("address");
                    String contrasena = rs.getString("contrasena");
                    String nameUsuario = rs.getString("nameusuario");
                    usuarioL = new Usuario(idusuario,name,lastName,address,contrasena,nameUsuario);
                    usuarios.add(usuarioL);
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
            return usuarios;
    
        }
        
}
