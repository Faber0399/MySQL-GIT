package usuario.test;

import java.sql.*;
import java.util.*;
import usuario.datos.*;
import usuario.domain.*;

public class UsuarioTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    Connection conexion=null;

    try
    {
        conexion = Conexion.getConnection();
        if (conexion.getAutoCommit()) {
            conexion.setAutoCommit(false);
        }
        PeliculaDao pelicula = new PeliculaDao(conexion);
        UsuarioDao usuario = new UsuarioDao(conexion);
        int eleccion = -2;
        System.out.println(
                    "Elige la opcion\n 1.- Iniciar catalogo películas \n 2. Revisar catalogo existente \n 0.- Salir ");
            System.out.print("-->");
            eleccion = sc.nextInt();
            sc.nextLine();
        while (eleccion != 0 && eleccion != 2) {

            System.out.println(
                    " Por favor registrese para continuar\n -->");
            System.out.println( " Primer nombre");
            String firstName=sc.nextLine();
            System.out.println( " Apellidos");
            String lastName=sc.nextLine();
            System.out.println( "Direccion");
            String address=sc.nextLine();
            System.out.println( "contrasena");
            String contrasena=sc.nextLine();
            System.out.println( "Nombre de usuario");
            String nameUsuario=sc.nextLine();;
            Usuario usuario1 = new Usuario(firstName,lastName,address,contrasena,nameUsuario);
            usuario.insertar(usuario1);
            conexion.commit();
            break;
        }
        sc.close();
    }catch(SQLException e)
    {
        e.printStackTrace();
        try {
            conexion.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    }
    
}
