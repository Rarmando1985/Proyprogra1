package forms;

import static forms.login.perfil_color;
import static forms.login.nombreUsuario;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proybiblioteca.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import proybiblioteca.HashUtil;
import java.sql.Statement;
/**
 *
 * @author ronychoche
 */
public class formAutores extends javax.swing.JFrame {

    public formAutores() {
        initComponents();
        cargarRolesEnComboBox();
        colorfondo();
        cargarTablaUsuarios();
        String col=perfil_color;
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
                    setVisible(false); 
                }
            }
        });
    }
    
        public void colorfondo(){
        try {
    getContentPane().setBackground(Color.decode(perfil_color));
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "El valor de perfil_color no es un código de color válido.");
}

    }
    
    
    public void cargarRolesEnComboBox() {

}
        public void cargarTablaUsuarios() {
      try {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Biografía");
        modelo.addColumn("País");
        modelo.addColumn("Fecha Ingreso");
        modelo.addColumn("Usuario Ingreso");

        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        Statement st = cn.createStatement();

        String sql = "SELECT id_autor, nombre, biografia, pais, fecha_ingreso, usuario_ingreso FROM autores";
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String[] fila = new String[6];
            fila[0] = rs.getString("id_autor");
            fila[1] = rs.getString("nombre");
            fila[2] = rs.getString("biografia");
            fila[3] = rs.getString("pais");
            fila[4] = rs.getString("fecha_ingreso");
            fila[5] = rs.getString("usuario_ingreso");

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al cargar la tabla de autores: " + e.getMessage());
    }
    }
    


public void limpiarcampos(){
    txtautor.setText("");
    txtPais.setText("");

    txtFotografia.setText("");
    
}
    
public void guardarAutor() {
  String nombre = txtautor.getText().trim();
String biografia = txtBiografia.getText().trim();
String pais = txtPais.getText().trim();
String rutaImagen = txtFotografia.getText().trim();
String usuarioIngreso = nombreUsuario;

File archivoFoto = new File(rutaImagen);

Connection cn = null;
PreparedStatement psCheck = null;
PreparedStatement psInsert = null;
ResultSet rs = null;
FileInputStream fis = null;

try {
    Conexion cc = new Conexion();
    cn = cc.conectar();

    // Verificar que el autor no se repita
    String sqlCheck = "SELECT COUNT(*) FROM autores WHERE LOWER(nombre) = LOWER(?)";
    psCheck = cn.prepareStatement(sqlCheck);
    psCheck.setString(1, nombre);
    rs = psCheck.executeQuery();

    if (rs.next() && rs.getInt(1) > 0) {
        JOptionPane.showMessageDialog(null, "El autor ya existe. Intente con otro nombre.");
        return;
    }

    // Preparar imagen
    fis = new FileInputStream(archivoFoto);
    long longitud = archivoFoto.length();

    // Insertar autor
    String sqlInsert = "INSERT INTO autores (nombre, biografia, pais, foto, fecha_ingreso, usuario_ingreso) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";
    psInsert = cn.prepareStatement(sqlInsert);
    psInsert.setString(1, nombre.toUpperCase());
    psInsert.setString(2, biografia);
    psInsert.setString(3, pais.toUpperCase());
    psInsert.setBinaryStream(4, fis, (int) longitud);
    psInsert.setDate(5, new java.sql.Date(System.currentTimeMillis()));
    psInsert.setString(6, usuarioIngreso.toLowerCase());

    psInsert.executeUpdate();
    JOptionPane.showMessageDialog(null, "Autor guardado exitosamente");

} catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error al guardar autor: " + ex.getMessage());
} finally {
    try {
        if (rs != null) rs.close();
        if (psCheck != null) psCheck.close();
        if (psInsert != null) psInsert.close();
        if (fis != null) fis.close();
        if (cn != null) cn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblusuario = new javax.swing.JLabel();
        txtautor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtBiografia = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtFotografia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        ltbFoto = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nuevo Autor");

        lblusuario.setText("Autor");

        txtautor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtautorActionPerformed(evt);
            }
        });
        txtautor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtautorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtautorKeyReleased(evt);
            }
        });

        jLabel1.setText("Biografia");

        txtBiografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBiografiaActionPerformed(evt);
            }
        });

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jLabel3.setText("Pais");

        jLabel7.setText("Fotografia");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtFotografia.setEditable(false);

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

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFotografia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnguardar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(37, 37, 37)
                                        .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(lblusuario))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtautor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBiografia, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(35, 35, 35)
                        .addComponent(ltbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblusuario)
                            .addComponent(txtautor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBiografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtFotografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(btnguardar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(ltbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBiografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBiografiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBiografiaActionPerformed

    private void txtautorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtautorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtautorActionPerformed

    private void txtautorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtautorKeyPressed
        // TODO add your handling code here:
 
    }//GEN-LAST:event_txtautorKeyPressed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if (txtautor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el usuario.");
        } else if (txtPais.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del usuario.");
        } else if(txtFotografia.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe Seleccionar Fotografia de perfil");
        } else {
            guardarAutor();
            limpiarcampos();
             cargarTablaUsuarios();
        }

     
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtautorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtautorKeyReleased
           // TODO add your handling code here:
    }//GEN-LAST:event_txtautorKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        File archivoSeleccionado = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg", "gif"));

        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = fileChooser.getSelectedFile();
            System.out.println("Ruta de imagen seleccionada: " + archivoSeleccionado.getAbsolutePath());
            txtFotografia.setText(archivoSeleccionado.getAbsolutePath());
}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
int fila = tabla.getSelectedRow();

if (fila >= 0 && fila < tabla.getRowCount()) {
    txtautor.setText(tabla.getValueAt(fila, 1).toString());
    txtBiografia.setText(tabla.getValueAt(fila, 2).toString());
    txtPais.setText(tabla.getValueAt(fila, 3).toString());

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String nombreAutor = tabla.getValueAt(fila, 1).toString();

        Conexion cc = new Conexion();
        cn = cc.conectar();

        String sql = "SELECT foto FROM autores WHERE nombre = ?";
        ps = cn.prepareStatement(sql);
        ps.setString(1, nombreAutor);
        rs = ps.executeQuery();

        if (rs.next()) {
            byte[] imagenBytes = rs.getBytes("foto");

            if (imagenBytes != null) {
                ImageIcon icono = new ImageIcon(imagenBytes);
                Image img = icono.getImage().getScaledInstance(ltbFoto.getWidth(), ltbFoto.getHeight(), Image.SCALE_SMOOTH);
                ltbFoto.setIcon(new ImageIcon(img));
            } else {
                ltbFoto.setIcon(null);
                JOptionPane.showMessageDialog(null, "Este autor no tiene una foto asignada.");
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al cargar la foto del autor: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cn != null) cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

} else {
    JOptionPane.showMessageDialog(null, "Selección no válida en la tabla.");
}


    }//GEN-LAST:event_tablaMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        String busc = txtBuscar.getText().trim();

try {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Biografía");
    modelo.addColumn("País");
    modelo.addColumn("Fecha Ingreso");
    modelo.addColumn("Usuario Ingreso");

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
    Statement st = cn.createStatement();

    String sql = "SELECT id_autor, nombre, biografia, pais, fecha_ingreso, usuario_ingreso " +
                 "FROM autores " +
                 "WHERE LOWER(nombre) LIKE LOWER('%" + busc + "%') " +
                 "OR LOWER(biografia) LIKE LOWER('%" + busc + "%') " +
                 "OR LOWER(pais) LIKE LOWER('%" + busc + "%') " +
                 "OR LOWER(usuario_ingreso) LIKE LOWER('%" + busc + "%') " +
                 "OR fecha_ingreso LIKE '%" + busc + "%'";

    ResultSet rs = st.executeQuery(sql);

    while (rs.next()) {
        String[] fila = new String[6];
        fila[0] = rs.getString("id_autor");
        fila[1] = rs.getString("nombre");
        fila[2] = rs.getString("biografia");
        fila[3] = rs.getString("pais");
        fila[4] = rs.getString("fecha_ingreso");
        fila[5] = rs.getString("usuario_ingreso");

        modelo.addRow(fila);
    }

    tabla.setModel(modelo);

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error al buscar autores: " + e.getMessage());
}


    }//GEN-LAST:event_txtBuscarKeyPressed

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
            java.util.logging.Logger.getLogger(formAutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formAutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formAutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formAutores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formAutores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JLabel ltbFoto;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBiografia;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFotografia;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtautor;
    // End of variables declaration//GEN-END:variables
}
