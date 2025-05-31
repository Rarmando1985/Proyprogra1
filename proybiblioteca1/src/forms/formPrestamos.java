/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;
import proybiblioteca.ControladorCorreo;
import static forms.login.nombreUsuario;
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
import javax.swing.table.DefaultTableCellRenderer;



/**
 *
 * @author ronychoche
 */
public class formPrestamos extends javax.swing.JFrame {

    
    public formPrestamos() {
        initComponents();
      colorfondo();
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
    txtNombre.setText("");
    txtIsbn.setText("");
    txtGenero.setText("");
    txtid.setText("");
    txtDisponibilidad.setText("");
    
}
    
        public void cargarTablaUsuarios(){
     try {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Titulo");
        modelo.addColumn("Isbn");
        modelo.addColumn("Genero");
        modelo.addColumn("Disponibles");
        modelo.addColumn("Autor");
        modelo.addColumn("Año Publicacion");
        modelo.addColumn("Editorial");
        modelo.addColumn("Rating"); 

        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_libro, titulo, isbn, genero, cantidad, autor, anio_publicacion, editorial, rating_promedio FROM libros");

        while (rs.next()) {
            String[] fila = new String[9];
            fila[0] = rs.getString(1);
            fila[1] = rs.getString(2);
            fila[2] = rs.getString(3);
            fila[3] = rs.getString(4);
            fila[4] = rs.getString(5);
            fila[5] = rs.getString(6);
            fila[6] = rs.getString(7);
            fila[7] = rs.getString(8);
            fila[8] = rs.getString(9); 

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);

        tabla.getColumnModel().getColumn(8).setCellRenderer(new StarRenderer());

    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e);
    }
}

class StarRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        if (value != null) {
            try {
        
                double ratingDouble = Double.parseDouble(value.toString());
                int rating = (int) Math.round(ratingDouble);
           
                StringBuilder estrellas = new StringBuilder();
                for (int i = 0; i < rating; i++) {
                    estrellas.append("★"); 
                }                
                setText(estrellas.toString());
                setHorizontalAlignment(CENTER); // Centra las estrellas
            } catch (NumberFormatException e) {
                setText("");
            }
        } else {
            setText("");
        }
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

        String sql = "select id_libro,titulo,isbn,genero,cantidad,autor,anio_publicacion,editorial from libros";
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

    
}

 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblusuario = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        txtIsbn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        cboxTipoArchivo = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        lblDisponibilidad = new javax.swing.JLabel();
        txtDisponibilidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblusuario.setText("Nombre");

        txtNombre.setEditable(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        jLabel4.setText("Genero");

        jLabel3.setText("ISBN");

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

        jButton4.setText("Exportar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cboxTipoArchivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JSON", "XML", "CSV" }));

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

        lblDisponibilidad.setText("Disponibilidad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblusuario)
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDisponibilidad)
                                .addGap(18, 18, 18)
                                .addComponent(txtDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addComponent(cboxTipoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblusuario)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboxTipoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisponibilidad)
                    .addComponent(txtDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
          // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
int fila = tabla.getSelectedRow(); // ✔️ Esto sí detecta la fila que se hizo clic

if (fila >= 0 && fila < tabla.getRowCount()) {
    txtid.setText(tabla.getValueAt(fila, 0).toString());
    txtNombre.setText(tabla.getValueAt(fila, 1).toString());
    txtIsbn.setText(tabla.getValueAt(fila, 2).toString());
    txtGenero.setText(tabla.getValueAt(fila, 3).toString());
    txtDisponibilidad.setText(tabla.getValueAt(fila, 4).toString());

    String valorParaCombo = tabla.getValueAt(fila, 4).toString();

} else {
    JOptionPane.showMessageDialog(null, "Selección no válida en la tabla.");
}
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if (txtid.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para continuar.");
} else if (txtDisponibilidad.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "El campo Disponibilidad no puede estar vacío.");
} else {
    Connection cn = null;
    PreparedStatement psUpdate = null;
    PreparedStatement psInsert = null;
    Statement st = null;
    ResultSet rs = null;

    try {
        int disponibilidad = Integer.parseInt(txtDisponibilidad.getText().trim());

        if (disponibilidad == 0) {
            JOptionPane.showMessageDialog(null, "No se puede prestar este libro porque no hay unidades disponibles.");
            return;
        }

        int cantidad = disponibilidad - 1;
        Conexion cc = new Conexion();
        cn = cc.conectar();

        psUpdate = cn.prepareStatement("UPDATE libros SET cantidad = ? WHERE id_libro = ?");
        int idLibro = Integer.parseInt(txtid.getText().trim());
        psUpdate.setInt(1, cantidad);
        psUpdate.setInt(2, idLibro);
        psUpdate.executeUpdate();

        psInsert = cn.prepareStatement("INSERT INTO prestamos (Id_libro, ISBN, usuario, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, ?, ?, ?, ?)");

        String isbn = txtIsbn.getText().trim();
        String usuario = nombreUsuario.toUpperCase();
        String fechaPrestamo = java.time.LocalDate.now().toString();
        String fechaDevolucion = java.time.LocalDate.now().plusDays(7).toString();
        String devuelto = "NO";

        psInsert.setInt(1, idLibro);
        psInsert.setString(2, isbn);
        psInsert.setString(3, usuario);
        psInsert.setString(4, fechaPrestamo);
        psInsert.setString(5, fechaDevolucion);
        psInsert.setString(6, devuelto);
        psInsert.executeUpdate();

        cargarTablaUsuarios();
        limpiarcampos();
        JOptionPane.showMessageDialog(null, "Préstamo registrado y disponibilidad actualizada con éxito.");

        st = cn.createStatement();
        rs = st.executeQuery("SELECT email FROM usuarios WHERE usuario = '" + usuario + "' AND ACTIVO = 'SI'");

        if (rs.next()) {
            String email = rs.getString("email");
            String asunto = "Prestamo Libro";
            String mensaje = "Se ha realizado un préstamo de libro: " + isbn + "\n" +
                    "Fecha de Prestamo: " + fechaPrestamo + "\n" +
                    "Fecha de devolución: " + fechaDevolucion + "\n" +
                    "Se le recuerda que por día tarde se cobrarán Q15.00";
            ControladorCorreo.enviarCorreo(email, asunto, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró correo electrónico.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: los campos deben contener valores numéricos válidos.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (psInsert != null) psInsert.close();
            if (psUpdate != null) psUpdate.close();
            if (cn != null) cn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + ex.getMessage());
        }
    }
}


    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        String busc= txtBuscar.getText();
        
        try {

            DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Titulo");
        modelo.addColumn("Isbn");
        modelo.addColumn("Genero");
        modelo.addColumn("Disponibles");
        modelo.addColumn("Autor");
        modelo.addColumn("Año Publicacion");
        modelo.addColumn("Editorial");
        modelo.addColumn("Rating");  
       
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        Statement st= (Statement) cn.createStatement();
  ResultSet rs= st.executeQuery(
    "SELECT id_libro, titulo, isbn, genero, cantidad, autor, anio_publicacion, editorial, rating_promedio FROM libros " +
    "WHERE titulo LIKE '%" + busc + "%' " +
    "OR isbn LIKE '%" + busc + "%' " +
    "OR autor LIKE '%" + busc + "%' " +
    "OR editorial LIKE '%" + busc + "%' " +
    "OR genero LIKE '%" + busc + "%' " +
    "OR anio_publicacion LIKE '%" + busc + "%'"+
    "OR rating_promedio LIKE '%" + busc + "%'"      
);  
     while (rs.next()) {
            String[] fila = new String[9];
            fila[0] = rs.getString(1);
            fila[1] = rs.getString(2);
            fila[2] = rs.getString(3);
            fila[3] = rs.getString(4);
            fila[4] = rs.getString(5);
            fila[5] = rs.getString(6);
            fila[6] = rs.getString(7);
            fila[7] = rs.getString(8);
            fila[8] = rs.getString(9); 

            modelo.addRow(fila);
        }

        tabla.setModel(modelo);

        tabla.getColumnModel().getColumn(8).setCellRenderer(new StarRenderer());

    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e);
    }
        
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(formPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formPrestamos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboxTipoArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDisponibilidad;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDisponibilidad;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
