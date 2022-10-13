package usuario.domain;

public class Usuario {
    private int id_usuario;
    private String firstName;
    private String lastName;
    private String address;
    private String contrasena;
    private String nameUsuario;

    

    public Usuario(String firstName, String lastName, String address, String contrasena, String nameUsuario) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contrasena = contrasena;
        this.nameUsuario = nameUsuario;
    }

    public Usuario(int id_usuario, String firstName, String lastName, String address, String contrasena,
            String nameUsuario) {
        this.id_usuario = id_usuario;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contrasena = contrasena;
        this.nameUsuario = nameUsuario;
    }

    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNameUsuario() {
        return nameUsuario;
    }

    public void setNameUsuario(String nameUsuario) {
        this.nameUsuario = nameUsuario;
    }

    @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_usuario + ", firstName=" + firstName + ", lastName=" + lastName
                + ", address=" + address + ", contrasena=" + contrasena + ", nameUsuario=" + nameUsuario + "]";
    }



}
