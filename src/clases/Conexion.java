package clases;



import java.sql.*;
import java.util.ArrayList;
import formularios.Administrador;
import clases.administrador;

public class Conexion {
   Connection Cn;
    PreparedStatement Ps;
     ResultSet Rs;

    public Conexion() {
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Inicializar la conexión
            Cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/econoregist?serverTimezone=America/Bogota",
                "root", 
                "" 
            );
            System.out.println("Conectado a la base de datos");
        } catch (Exception e) {
            System.out.println("Error de conexión de BD: " + e.getMessage());
        }
    }

    public int RegUsuarios(String EMAIL, String contraseña, String NombreUsuario, String NumeroTelefonico, String FECHA_NACIMIENTO) {
        int respuesta = 0;
        try {
            Ps = Cn.prepareStatement("INSERT INTO usuarios (EMAIL, contraseña, NombreUsuario, NumeroTelefonico, FECHA_NACIMIENTO) VALUES (?, ?, ?, ?, ?)");
            Ps.setString(1, EMAIL);
            Ps.setString(2, contraseña);
            Ps.setString(3, NombreUsuario);
            Ps.setString(4, NumeroTelefonico);
            Ps.setString(5, FECHA_NACIMIENTO);

            respuesta = Ps.executeUpdate();
            System.out.println("Usuario registrado correctamente");
            
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
        } finally {
            // Cerrar los recursos
            try {
                if (Ps != null) Ps.close();
                if (Cn != null) Cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return respuesta;
    }
    
    public ArrayList<Login>login(String correo, String contraseña){
        ArrayList<Login> respuesta2 = new  ArrayList<>();
        try {
            Ps =Cn.prepareStatement("SELECT * FROM USUARIOS WHERE EMAIL=? AND contraseña=?");
            Ps.setString(1, correo);
            Ps.setString(2, contraseña);
            Rs=Ps.executeQuery();
            while(Rs.next()){
                Login login = new Login();
                login.setCorreo(Rs.getString("EMAIL"));
                login.setContraseña(Rs.getString("contraseña"));
                respuesta2.add(login);
                if (respuesta2.isEmpty()) {
                    System.out.println("acceso denegado");
                }else{
                    System.out.println("login exitoso");
                }
       
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       return respuesta2;
    }
    
    public ArrayList<administrador>admin(String correo, String contraseña){
        
        ArrayList<administrador> respuesta3 = new ArrayList<>();
        try {
            Ps=Cn.prepareStatement("SELECT * FROM admin WHERE email=? AND contraseña=?");
            Ps.setString(1, correo);
            Ps.setString(2, contraseña);
            Rs=Ps.executeQuery();
           while(Rs.next()){
           administrador admin = new administrador();
           admin.setCorreo(Rs.getString("email"));
           admin.setContraseña(Rs.getString("contraseña"));
           respuesta3.add(admin);
           if (respuesta3.isEmpty()) {
                    System.out.println("acceso denegado");
                }else{
                    System.out.println("login exitoso");
                }
           
           
           
           }
            
                    
        } catch (Exception e) {
        }
        return respuesta3;
        
    }
    
    public int actualizarUser(String EMAIL, String contraseña, String NombreUsuario, String NumeroTelefonico, String FECHA_NACIMIENTO, String id){
     int respuestaaaa = 0;
        try {
             Ps=Cn.prepareStatement("UPDATE  usuarios SET EMAIL=?, contraseña=?, NombreUsuario=?, NumeroTelefonico=?, FECHA_NACIMIENTO=? WHERE ID_USUARIO=?");
             Ps.setString(1, EMAIL);
             Ps.setString(2, contraseña);
             Ps.setString(3, NombreUsuario);
             Ps.setString(4, NumeroTelefonico);
             Ps.setString(5, FECHA_NACIMIENTO);
             Ps.setString(6, id);
             respuestaaaa = Ps.executeUpdate();
             System.out.println("datos modificados correctamente");
        } catch (Exception e) {
            System.out.println("error de modificacion");
        }
        return respuestaaaa;
    }
    public int borrarRegistro( String id){
    int RESpuesta = 0;
        try {
             Ps=Cn.prepareStatement("DELETE FROM usuarios WHERE ID_USUARIO=?");
            
             Ps.setString(1, id);
             RESpuesta = Ps.executeUpdate();
             System.out.println("datos elimanados correctamente");
        } catch (Exception e) {
            System.out.println("ah currido un error");
        }
    return RESpuesta;
    }
    
    public ArrayList<tablaAdmin> tAdmin(){
    ArrayList<tablaAdmin> respuesta = new ArrayList();
        try {
            Ps = Cn.prepareStatement("SELECT * FROM usuarios");
            Rs=Ps.executeQuery();
            while(Rs.next()){
            tablaAdmin tAdmin = new tablaAdmin();
            tAdmin.setEMAIL(Rs.getString("Email"));
            tAdmin.setContraseña(Rs.getString("contraseña"));
            tAdmin.setNombreUsuario(Rs.getString("NombreUsuario"));
            tAdmin.setNumeroTelefonico(Rs.getString("NumeroTelefonico"));
            tAdmin.setFECHA_NACIMIENTO(Rs.getString("FECHA_NACIMIENTO"));
            tAdmin.setID_USUARIO(Rs.getInt("ID_USUARIO"));
           
            respuesta.add(tAdmin);
            
            
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return respuesta;
    }
}
