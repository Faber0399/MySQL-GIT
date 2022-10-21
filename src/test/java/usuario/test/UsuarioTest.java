package usuario.test;

import java.sql.*;
import java.util.*;
import java.util.regex.Pattern;

import usuario.datos.*;
import usuario.domain.*;

public class UsuarioTest {
    public static void main(String[] args) {
    int eleccion = -2;
    String firstName;
    String lastName;
    boolean valor;
    String contrasena;
    String nameUsuario;
    List<Usuario> miarreglo=new ArrayList();
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
        System.out.println(
                    "Elige la opcion\n 1.- Registrarse \n 2.- Iniciar sesion\n ");
            System.out.print("-->");
            eleccion = sc.nextInt();
            sc.nextLine();
        if (eleccion==1) {
            do{
                System.out.print( "Primer nombre: ");
                firstName=sc.nextLine();
            }while(firstName.isEmpty());
            do{
                System.out.print( "Apellidos: ");
                lastName=sc.nextLine();
            }while(lastName.isEmpty());
            String address;
            do{
                System.out.print( "Direccion: ");
                address=sc.nextLine();
            }while(address.isEmpty());
            do{
                System.out.println( "Por favor introduce una contrasena que tenga al menos un caracter especial");
                Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
                contrasena=sc.nextLine();
                valor = regex.matcher(contrasena).find();
            }while (valor==false);
            do{
                System.out.print( "Nombre de usuario: ");
                nameUsuario=sc.nextLine();
            }while(nameUsuario.isEmpty());
            Usuario usuario1 = new Usuario(firstName,lastName,address,contrasena,nameUsuario);
            usuario.insertar(usuario1);
            conexion.commit();
        }
        if(eleccion ==2){
            do{
                System.out.println( "Por favor introduce tu nombre de usuario");
                nameUsuario=sc.nextLine();
            }while (nameUsuario.isEmpty());
            do{
                System.out.println( "Por favor introduce tu contrasena");
                contrasena=sc.nextLine();
            }while (nameUsuario.isEmpty());
            miarreglo = usuario.seleccionar();
            int i=0;
            for (Usuario usuario1 : miarreglo){
                i++;
                if (usuario1.getNameUsuario().equals(nameUsuario) && usuario1.getContrasena().equals(contrasena)){
                    System.out.println("Muchas gracias "+usuario1.getFirstName()+" por ingresar a nuestro portal");
                    break;
                }
                if(miarreglo.size()==i){System.out.println("Error en el campo de usuario o contrasena");}
            }
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
