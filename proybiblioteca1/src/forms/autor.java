/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import static forms.login.perfil_color;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proybiblioteca.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import proybiblioteca.HashUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.sql.ResultSetMetaData;



/**
 *
 * @author ronychoche
 */
public class autor extends javax.swing.JFrame {

    
    public autor() {
        initComponents();
      colorfondo();
      cargarRolesEnComboBox();
      cargarTablaUsuarios();
           this.setLocationRelativeTo(null);
           setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(null, 
                    "¿Estás seguro de que deseas cerrar este formulario?", 
                    "Confirmar cierre", 
                    JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    setVisible(false); // Oculta el formulario
                }
            }
        });
    }
    

    public void cargarTablaUsuarios(){
         try {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Email");
        modelo.addColumn("Rol");
        modelo.addColumn("Activo");
        modelo.addColumn("fecha Registro");
       
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        Statement st= (Statement) cn.createStatement();
        ResultSet rs= st.executeQuery("select id_libro,titulo,isbn,genero,autor,anio_publicacion,editorial from libros");   
    
        while (rs.next()){
            String[] fila= new String[7];
            fila [0]= rs.getString(1);
            fila [1]= rs.getString(2);
            fila [2]= rs.getString(3);
            fila [3]= rs.getString(4);
            fila [4]= rs.getString(5);
            fila [5]= rs.getString(6);
            fila [6]= rs.getString(7);
            
            modelo.addRow(fila); 
        }
        tabla.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
            
        }
    }
    
        public void cargarRolesEnComboBox() {
    try {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        String sql = "SELECT nom_rol FROM roles";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String rol = rs.getString("nom_rol");

        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al cargar los roles.");
    }
}

        public void colorfondo(){
        try {
    getContentPane().setBackground(Color.decode(perfil_color));
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "El valor de perfil_color no es un código de color válido.");
}

    }
    
        public void exportarjson(){
               try {
    javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
    fileChooser.setDialogTitle("Guardar JSON");
    fileChooser.setSelectedFile(new java.io.File("usuarios.json"));

    int userSelection = fileChooser.showSaveDialog(null);
    if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
        java.io.File fileToSave = fileChooser.getSelectedFile();

        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        String sql = "select id_libro,titulo,isbn,genero,autor,anio_publicacion,editorial from libros";
        Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        JSONArray listaUsuarios = new JSONArray();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnas = metaData.getColumnCount();

        while (rs.next()) {
            JSONObject usuario = new JSONObject();
            for (int i = 1; i <= columnas; i++) {
                String nombreColumna = metaData.getColumnName(i);
                Object valor = rs.getObject(i);
                usuario.put(nombreColumna, valor);
            }
            listaUsuarios.put(usuario);
        }

        try (FileWriter fileWriter = new FileWriter(fileToSave)) {
            fileWriter.write(listaUsuarios.toString(4)); 
        }

        System.out.println("JSON exportado correctamente a: " + fileToSave.getAbsolutePath());

        stmt.close();
        cn.close();
    }
} catch (Exception ex) {
    ex.printStackTrace();
}

        }
        

 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
int fila = tabla.getSelectedRow(); // ✔️ Esto sí detecta la fila que se hizo clic

    }//GEN-LAST:event_tablaMouseClicked

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(autor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new autor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
