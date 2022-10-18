package usuario.test;

import java.sql.*;
import java.util.*;

import usuario.datos.*;
import usuario.domain.Pelicula;
import usuario.domain.Usuario;

public class TestUsario {
    public static void main(String[] args) {
        Connection conexion=null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            PeliculaDao peliculaDao= new PeliculaDao(conexion);
            Pelicula pelicula1 = new Pelicula("Rambo",135, "Accion", "Rambo es una popular saga de películas de acción protagonizadas por Sylvester Stallone");
            peliculaDao.insertar(pelicula1);

            /* UsuarioDao usuarioDao = new UsuarioDao(conexion);
            Usuario usuario = new Usuario(1,"Farid10","Rodriguez","Calle 4A 6-30","contrasena","farid10");
            Usuario usuario2=new Usuario(2);
            //usuarioDao.insertar(usuario);
            //usuarioDao.actualizar(usuario);
            //usuarioDao.eliminar(usuario2);
            List<Usuario> miarreglo=new ArrayList();
            miarreglo = usuarioDao.seleccionar();
            for (Usuario usuario1 : miarreglo){
                System.out.println(usuario1);
            }   */
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
