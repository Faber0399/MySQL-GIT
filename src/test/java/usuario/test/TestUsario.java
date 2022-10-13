package usuario.test;

import java.sql.*;
import java.util.*;

import usuario.datos.*;
import usuario.domain.Usuario;

public class TestUsario {
    public static void main(String[] args) {
        Connection conexion=null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            UsuarioDao usuarioDao = new UsuarioDao(conexion);
            Usuario usuario = new Usuario(1,"Farid10","Rodriguez","Calle 4A 6-30","contrasena","farid10");
            Usuario usuario2=new Usuario(2);
            //usuarioDao.insertar(usuario);
            //usuarioDao.actualizar(usuario);
            //usuarioDao.eliminar(usuario2);
            List<Usuario> miarreglo=new ArrayList();
            miarreglo = usuarioDao.seleccionar();
            for (Usuario usuario1 : miarreglo){
                System.out.println(usuario1);
            }  
            conexion.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Entramos al RollBack");
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
