package usuario.datos;

import java.sql.*;
import java.util.*;

import usuario.domain.Usuario;

public class UsuarioDao {
    
        private static final String SQL_SELECT_USU = "SELECT * FROM peliculas.usuarios;";
        private static final String SQL_INSERT_USU = "INSERT INTO peliculas.usuarios (first_name,last_name,address,contrasena,nameusuario) VALUES (?,?,?,?,?)";
        private static final String SQL_UPDATED_USU = "UPDATE peliculas.usuarios SET first_name=?,last_name=?,address=?,contrasena=?,nameusuario=? WHERE idUsuario=?";
        private static final String SQL_DELETE_USU = "DELETE FROM peliculas.usuarios WHERE idUsuario=?";
        private Connection ConexionTransaccional;
        
    
        public UsuarioDao() {
        }
        

        public UsuarioDao(Connection conexionTransaccional) {
            ConexionTransaccional = conexionTransaccional;
        }


        public List<Usuario> seleccionar() throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Usuario usuarioL = null;
            List<Usuario> usuarios = new ArrayList();
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
    
        public int insertar(Usuario persona) throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            int registro = 0;
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_INSERT_USU);
                stmt.setString(1, persona.getFirstName());
                stmt.setString(2, persona.getLastName());
                stmt.setString(3, persona.getAddress());
                stmt.setString(4, persona.getContrasena());
                stmt.setString(5, persona.getNameUsuario());
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
        public int actualizar(Usuario persona) throws SQLException{
            Connection conn = null;
            PreparedStatement stmt = null;
            int registro=0;
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_UPDATED_USU);
                stmt.setString(1, persona.getFirstName());
                stmt.setString(2, persona.getLastName());
                stmt.setString(3, persona.getAddress());
                stmt.setString(4, persona.getContrasena());
                stmt.setString(5, persona.getNameUsuario());
                stmt.setInt(6, persona.getId_usuario());
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
        public int eliminar (Usuario persona) throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            int registro=0;
            try {
                conn = this.ConexionTransaccional!=null ? this.ConexionTransaccional: Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_DELETE_USU);
                stmt.setInt(1, persona.getId_usuario());
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
