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
public class formAdminUsers extends javax.swing.JFrame {

    
    public formAdminUsers() {
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
    
    public void limpiarcampos(){
    txtusuario.setText("");
    txtnombre.setText("");
    txtemail.setText("");
    txtid.setText("");
    
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
        ResultSet rs= st.executeQuery("select id,usuario,nombre, email, rol, activo, fecha_registro  from usuarios WHERE ACTIVO = 'SI'");   
    
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
            cboxRol.addItem(rol);
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

        String sql = "SELECT ID, USUARIO, PASSWORD_SALT, PASSWORD_HASH, NOMBRE, EMAIL, ROL, PERFIL_COLOR, ACTIVO, FECHA_REGISTRO FROM USUARIOS";
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
        
        
        public void reiniciarPassword() {
    String usuario = txtusuario.getText().trim();
    String rol = cboxRol.getSelectedItem().toString();
    String sashsalt=usuario.toUpperCase()+rol.toUpperCase();
    
    String hash = HashUtil.sha256(sashsalt);
     try {
            Conexion cc=new Conexion();
            Connection cn= cc.conectar();
            PreparedStatement ps= cn.prepareStatement("Update usuarios set password_salt=?, password_hash=? where id=?");
             int id = Integer.parseInt(txtid.getText());            
            ps.setString(1,hash);
            ps.setString(2,hash);
            ps.setInt(3,id);
            ps.executeUpdate();
            limpiarcampos();
            cargarTablaUsuarios();
            JOptionPane.showMessageDialog(null, "Password reiniciado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    

    
}

 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblusuario = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        cboxRol = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cboxTipoArchivo = new javax.swing.JComboBox<>();
        txtBucar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblusuario.setText("Usuario:");

        txtusuario.setEditable(false);
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        txtusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtusuarioKeyReleased(evt);
            }
        });

        jLabel2.setText("Rol");

        jLabel4.setText("email");

        jLabel3.setText("Nombre");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Id");

        txtid.setEditable(false);

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reiniciar Password");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Exportar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cboxTipoArchivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JSON", "XML", "CSV" }));

        txtBucar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBucarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtnombre))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtemail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboxRol, javax.swing.GroupLayout.Alignment.LEADING, 0, 284, Short.MAX_VALUE))))
                        .addGap(50, 50, 50)
                        .addComponent(cboxTipoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusuario)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(txtid))
                        .addGap(45, 45, 45)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblusuario)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboxTipoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBucar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void txtusuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtusuarioKeyPressed

    private void txtusuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyReleased
          // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
int fila = tabla.getSelectedRow(); // ✔️ Esto sí detecta la fila que se hizo clic

if (fila >= 0 && fila < tabla.getRowCount()) {
    txtid.setText(tabla.getValueAt(fila, 0).toString());
    txtusuario.setText(tabla.getValueAt(fila, 1).toString());
    txtnombre.setText(tabla.getValueAt(fila, 2).toString());
    txtemail.setText(tabla.getValueAt(fila, 3).toString());

    String valorParaCombo = tabla.getValueAt(fila, 4).toString();
    cboxRol.setSelectedItem(valorParaCombo);
} else {
    JOptionPane.showMessageDialog(null, "Selección no válida en la tabla.");
}
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (txtid.getText().trim().isEmpty()) {

       JOptionPane.showMessageDialog(null, "Debe selecciona un registro para continuar.");
   }else{
               int opcion = JOptionPane.showConfirmDialog(
                    null, 
                    "¿Estás seguro que deseas reiniciar la contraseña?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                         
            
        try {
            Conexion cc=new Conexion();
            Connection cn= cc.conectar();
            PreparedStatement ps= cn.prepareStatement("Update usuarios set Activo ='NO' where id=?");
             int id = Integer.parseInt(txtid.getText());            
            ps.setInt(1,id);
            ps.executeUpdate();
            limpiarcampos();
            cargarTablaUsuarios();
            JOptionPane.showMessageDialog(null, "Registro eliminado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
     }
    }
                
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    if (txtid.getText().trim().isEmpty()) {

       JOptionPane.showMessageDialog(null, "Debe selecciona un registro para continuar.");
   }else{
        

         try {
            Conexion cc=new Conexion();
            Connection cn= cc.conectar();
            PreparedStatement ps= cn.prepareStatement("Update usuarios set nombre =?,email =?,rol =? where id=?");
             int id = Integer.parseInt(txtid.getText());  
             String nombre=  txtnombre.getText();
             String emai=  txtemail.getText();
             String rol = cboxRol.getSelectedItem().toString();
                         
            ps.setString(1,nombre);
            ps.setString(2,emai);
            ps.setString(3,rol);
            ps.setInt(4,id);
            ps.executeUpdate();
            limpiarcampos();
            cargarTablaUsuarios();
            JOptionPane.showMessageDialog(null, "Registro Actualizado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(
                    null, 
                    "¿Estás seguro que deseas reiniciar la contraseña?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    reiniciarPassword(); 
                }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
String tipoSeleccionado = cboxTipoArchivo.getSelectedItem().toString().toLowerCase();
        if (tipoSeleccionado.equals("json")) {
             exportarjson();    
    } else if (tipoSeleccionado.equals("xml")) {
        JOptionPane.showMessageDialog(null, "Has seleccionado el tipo de archivo XML.");
    } else if (tipoSeleccionado.equals("csv")) {
        JOptionPane.showMessageDialog(null, "Has seleccionado el tipo de archivo CSV.");
    } else {
        JOptionPane.showMessageDialog(null, "Tipo de archivo no válido.", "Error", JOptionPane.ERROR_MESSAGE);
    }
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtBucarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBucarKeyPressed
        // TODO add your handling code here:
    String busc = txtBucar.getText().trim();  // Obtener el texto de búsqueda
    
    try {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Email");
        modelo.addColumn("Rol");
        modelo.addColumn("Activo");
        modelo.addColumn("Fecha Registro");

        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        Statement st = cn.createStatement();

        // Consulta SQL para buscar en todos los campos
        String sql = "SELECT id, usuario, nombre, email, rol, activo, fecha_registro " +
                     "FROM usuarios " +
                     "WHERE ACTIVO = 'SI' " +
                     "AND (CAST(id AS CHAR) LIKE '%" + busc + "%' OR " +
                     "usuario LIKE '%" + busc + "%' OR " +
                     "nombre LIKE '%" + busc + "%' OR " +
                     "email LIKE '%" + busc + "%' OR " +
                     "rol LIKE '%" + busc + "%' OR " +
                     "activo LIKE '%" + busc + "%' OR " +
                     "fecha_registro LIKE '%" + busc + "%')";

        ResultSet rs = st.executeQuery(sql);

        // Cargar los resultados en la tabla
        while (rs.next()) {
            String[] fila = new String[7];
            fila[0] = rs.getString(1);
            fila[1] = rs.getString(2);
            fila[2] = rs.getString(3);
            fila[3] = rs.getString(4);
            fila[4] = rs.getString(5);
            fila[5] = rs.getString(6);
            fila[6] = rs.getString(7);

            modelo.addRow(fila);
        }

        tabla.setModel(modelo); 

    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error: " + e.getMessage());
    }


    }//GEN-LAST:event_txtBucarKeyPressed

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
            java.util.logging.Logger.getLogger(formAdminUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formAdminUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formAdminUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formAdminUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formAdminUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboxRol;
    private javax.swing.JComboBox<String> cboxTipoArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBucar;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
