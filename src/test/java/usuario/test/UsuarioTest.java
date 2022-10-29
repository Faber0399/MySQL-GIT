package usuario.test;

import java.io.UTFDataFormatException;
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
        int idUsuario = -1;
        List<Usuario> miarreglo = new ArrayList();
        Scanner sc = new Scanner(System.in);
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PeliculaDao peliculaDao = new PeliculaDao(conexion);
            UsuarioDao usuario = new UsuarioDao(conexion);
            while (eleccion != 0) {
                System.out.println(
                        "Elige la opcion\n 1.- Registrarse \n 2.- Iniciar sesion\n 0.- Salir ");
                System.out.print("-->");
                eleccion = sc.nextInt();
                sc.nextLine();
                if (eleccion == 1) {
                    do {
                        System.out.print("Primer nombre: ");
                        firstName = sc.nextLine();
                    } while (firstName.isEmpty());
                    do {
                        System.out.print("Apellidos: ");
                        lastName = sc.nextLine();
                    } while (lastName.isEmpty());
                    String address;
                    do {
                        System.out.print("Direccion: ");
                        address = sc.nextLine();
                    } while (address.isEmpty());
                    do {
                        System.out
                                .println("Por favor introduce una contrasena que tenga al menos un caracter especial");
                        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
                        contrasena = sc.nextLine();
                        valor = regex.matcher(contrasena).find();
                    } while (valor == false);
                    do {
                        System.out.print("Nombre de usuario: ");
                        nameUsuario = sc.nextLine();
                    } while (nameUsuario.isEmpty());
                    Usuario usuario1 = new Usuario(firstName, lastName, address, contrasena, nameUsuario);
                    usuario.insertar(usuario1);
                    
                }
                if (eleccion == 2) {
                    do {
                        System.out.print("Por favor introduce tu nombre de usuario: ");
                        nameUsuario = sc.nextLine();
                    } while (nameUsuario.isEmpty());
                    do {
                        System.out.print("Por favor introduce tu contrasena: ");
                        contrasena = sc.nextLine();
                    } while (nameUsuario.isEmpty());
                    miarreglo = usuario.seleccionar();
                    int i = 0;
                    for (Usuario usuario1 : miarreglo) {
                        i++;
                        if (usuario1.getNameUsuario().equals(nameUsuario)
                                && usuario1.getContrasena().equals(contrasena)) {
                            System.out.println(
                                    "Muchas gracias " + usuario1.getFirstName() + " por ingresar a nuestro portal.\n ");
                            idUsuario = usuario1.getId_usuario();
                            
                            while(eleccion!=0){
                                System.out.print(
                                    "Por favor selecciona tu proxima accion: \n 1.- Ingresar pelicula \n 2.- Buscar pelicula \n 3.- Revisar peliculas agregadas \n 4.- Eliminar pelicula \n 0.- Salir  \n--> ");
                            eleccion = sc.nextInt();
                            sc.nextLine();
                            List<Pelicula> miarreglo1=new ArrayList();
                            switch(eleccion)
                            {
                                case 1:
                                    System.out.print("Nombre Pelicula: ");
                                    String nombrePelicula= sc.nextLine();
                                    System.out.print("Duracion en (min): ");
                                    int duracion=sc.nextInt();
                                    sc.nextLine();
                                    System.out.print("Genero: ");
                                    String genero=sc.nextLine();
                                    System.out.print("Descripcion: ");
                                    String descripcion=sc.nextLine();
                                    Pelicula pelicula=new Pelicula(nombrePelicula, duracion, genero, descripcion, idUsuario);
                                    peliculaDao.insertar(pelicula);
                                    conexion.commit();
                                    break;
                                case 2:
                                    
                                    System.out.print("Por favor digita el nombre de la pelicula que desea buscar: ");
                                    String nombrePelicula1=sc.nextLine();
                                    miarreglo1 = peliculaDao.seleccionarBuscar(nombrePelicula1,idUsuario);
                                    for(Pelicula pelicula1: miarreglo1){
                                        System.out.println(pelicula1);
                                    }
                                    break;
                                case 3:
                                System.out.println("Peliculas agregadas: ");
                                    miarreglo1=peliculaDao.seleccionar(idUsuario);
                                    for(Pelicula pelicula1: miarreglo1){
                                        System.out.println(pelicula1);
                                    }
                                    break;

                            }
                            continue;}
                        }
                        if (miarreglo.size() == i) {
                            System.out.println(
                                    "Error en el campo de usuario o contraseña, por favor vuelve a intentarlo");
                        }
                    }
                }
            }
            conexion.commit();
            sc.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
