package proybiblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conectar =null;
    public Connection conectar(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            conectar=DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
             //  System.out.println("Conexión exitosa a SQLite.");
               verificarYCrearTablaUsuariosYRoles();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return conectar;
    }
    private void verificarYCrearTablaUsuariosYRoles() {
    String sqlCheckUsuarios = "SELECT name FROM sqlite_master WHERE type='table' AND name='usuarios';";
    String sqlCreateUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "usuario TEXT NOT NULL UNIQUE, "
            + "password_salt TEXT NOT NULL, "
            + "password_hash TEXT NOT NULL, "
            + "nombre TEXT NOT NULL, "
            + "email TEXT NOT NULL UNIQUE, "
            + "rol TEXT NOT NULL, "
            + "perfil_color TEXT, "
            + "activo TEXT NOT NULL, "
            + "foto_perfil BLOB, "
            + "fecha_registro TEXT NOT NULL DEFAULT (datetime('now', 'localtime')));";

    String sqlInsertAdmin = "INSERT INTO usuarios (usuario, password_salt, password_hash, nombre, email, rol, perfil_color, activo, foto_perfil, fecha_registro) "
            + "SELECT 'ADMIN', '675721492b42219552d8c010b7db11b9aee02efe55ad7915d489fb5ea2245091', '675721492b42219552d8c010b7db11b9aee02efe55ad7915d489fb5ea2245091', "
            + "'Rony', 'rchochep@miumg.edu.gt', 'Administrador', '#eeeeee', 'SI', '', datetime('now', 'localtime') "
            + "WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE usuario = 'ADMIN');";

    String sqlCheckRoles = "SELECT name FROM sqlite_master WHERE type='table' AND name='roles';";
    String sqlCreateRoles = "CREATE TABLE IF NOT EXISTS roles ("
            + "nom_rol TEXT PRIMARY KEY, "
            + "activo TEXT NOT NULL);";

    String sqlInsertRoles = "INSERT INTO roles (nom_rol, activo) VALUES "
            + "('Administrador', 'SI'), "
            + "('Bibliotecario', 'SI'), "
            + "('Miembro', 'SI');";

    String sqlLibros = "CREATE TABLE IF NOT EXISTS Libros ("
            + "Id_libro INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Titulo TEXT, "
            + "ISBN TEXT, "
            + "genero TEXT, "
            + "cantidad INTEGER, "
            + "Autor TEXT, "
            + "Portada BLOB, "
            + "anio_publicacion TEXT, "
            + "editorial TEXT, "
            + "idioma TEXT, "
            + "Rating_promedio REAL, "
            + "Fecha_ingreso TEXT, "
            + "usuario_ingreso TEXT);";

    String sqlAutores = "CREATE TABLE IF NOT EXISTS Autores ("
            + "id_autor INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nombre TEXT, "
            + "biografia TEXT, "
            + "pais TEXT, "
            + "foto BLOB, "
            + "Fecha_ingreso TEXT, "
            + "usuario_ingreso TEXT);";

    String sqlLibrosEscritos = "CREATE TABLE IF NOT EXISTS Libros_escritos ("
            + "id_libro INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Titulo TEXT, "
            + "ISBN TEXT, "
            + "anio_publicacion TEXT, "
            + "Fecha_ingreso TEXT, "
            + "usuario_ingreso TEXT);";

    String sqlResenia = "CREATE TABLE IF NOT EXISTS Resenia ("
            + "ID_RESEÑA INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "id_libro INTEGER, "
            + "Titulo TEXT, "
            + "ISBN TEXT, "
            + "ranking INTEGER, "
            + "fecha TEXT, "
            + "comentario TEXT, "
            + "usuario TEXT);";


    String sqlCheckPrestamos = "SELECT name FROM sqlite_master WHERE type='table' AND name='prestamos';";
    String sqlCreatePrestamos = "CREATE TABLE IF NOT EXISTS prestamos ("
          + "id_prestmo INTEGER PRIMARY KEY AUTOINCREMENT, "
          + "Id_libro TEXT, "
          + "ISBN TEXT, "
          + "usuario TEXT, "
          + "fecha_prestamo TEXT, "
          + "fecha_devolucion TEXT, "
          + "fecha_devuelto TEXT, "
          + "mora TEXT, "
          + "monto_mora INTEGER, "
          + "devuelto TEXT);";

    try (Statement stmt = conectar.createStatement()) {

        // Usuarios
        try (ResultSet rsUsuarios = stmt.executeQuery(sqlCheckUsuarios)) {
            if (!rsUsuarios.next()) {
                stmt.execute(sqlCreateUsuarios);
                System.out.println("Tabla 'usuarios' creada correctamente.");
            }
        }

        stmt.execute(sqlInsertAdmin);

        // Roles
        try (ResultSet rsRoles = stmt.executeQuery(sqlCheckRoles)) {
            if (!rsRoles.next()) {
                stmt.execute(sqlCreateRoles);
                stmt.execute(sqlInsertRoles);
                System.out.println("Tabla 'roles' creada correctamente con roles base.");
            }
        }

      
        stmt.execute(sqlLibros);
        stmt.execute(sqlAutores);
        stmt.execute(sqlLibrosEscritos);
        stmt.execute(sqlResenia);

 
        try (ResultSet rsPrestamos = stmt.executeQuery(sqlCheckPrestamos)) {
            if (!rsPrestamos.next()) {
                stmt.execute(sqlCreatePrestamos);
                System.out.println("Tabla 'prestamos' creada correctamente.");
            }
        }

        System.out.println("Tablas adicionales creadas correctamente.");

    } catch (Exception e) {
        System.out.println("Error al verificar/crear tablas o insertar datos: " + e.getMessage());
    }
}


}



       