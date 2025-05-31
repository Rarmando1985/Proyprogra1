/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

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
import proybiblioteca.ControladorCorreo;



/**
 *
 * @author ronychoche
 */
public class formDevoluciones extends javax.swing.JFrame {

    
    public formDevoluciones() {
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
    txtIdLibro.setText("");
    txtIsbn.setText("");
    txtGenero.setText("");
    txtid.setText("");

    
}
    
    public void cargarTablaUsuarios(){
         try {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Prestamo");
        modelo.addColumn("Id Libro");
        modelo.addColumn("Isbn");
        modelo.addColumn("Titulo");
        modelo.addColumn("Usuario");
        modelo.addColumn("Fecha_Prestamo");
        modelo.addColumn("Fecha_Devolucion");
        modelo.addColumn("Devuelto");
        String usuario= nombreUsuario.toUpperCase();
        
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        Statement st= (Statement) cn.createStatement();
        ResultSet rs= st.executeQuery("select a.id_prestmo,a.ID_libro,a.isbn,b.titulo,a.usuario, a.fecha_prestamo,a.fecha_devolucion,a.devuelto from prestamos a left join libros b on a.id_libro= b.id_libro where a.usuario = '"+usuario+"' and a.devuelto= 'NO'");   
    
        while (rs.next()){
            String[] fila= new String[8];
            fila [0]= rs.getString(1);
            fila [1]= rs.getString(2);
            fila [2]= rs.getString(3);
            fila [3]= rs.getString(4);
            fila [4]= rs.getString(5);
            fila [5]= rs.getString(6);
            fila [6]= rs.getString(7);
            fila [7]= rs.getString(8);
            
            modelo.addRow(fila); 
        }
        tabla.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
            
        }
    }
    
        public void cargarRolesEnComboBox() {
 
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblusuario = new javax.swing.JLabel();
        txtIdLibro = new javax.swing.JTextField();
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
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbValoracion = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtComentario = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblusuario.setText("Id libro");

        txtIdLibro.setEditable(false);
        txtIdLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdLibroActionPerformed(evt);
            }
        });
        txtIdLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdLibroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdLibroKeyReleased(evt);
            }
        });

        jLabel4.setText("Titulo");

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

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        jButton2.setText("Libros");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Valoracion");

        cbValoracion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        txtComentario.setColumns(20);
        txtComentario.setRows(5);
        jScrollPane3.setViewportView(txtComentario);

        jLabel5.setText("Comentarios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusuario)
                            .addComponent(jLabel1))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(cboxTipoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbValoracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(txtIdLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboxTipoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cbValoracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdLibroActionPerformed

    private void txtIdLibroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdLibroKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdLibroKeyPressed

    private void txtIdLibroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdLibroKeyReleased
          // TODO add your handling code here:
    }//GEN-LAST:event_txtIdLibroKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
int fila = tabla.getSelectedRow(); // ✔️ Esto sí detecta la fila que se hizo clic

if (fila >= 0 && fila < tabla.getRowCount()) {
    txtid.setText(tabla.getValueAt(fila, 0).toString());
    txtIdLibro.setText(tabla.getValueAt(fila, 1).toString());
    txtIsbn.setText(tabla.getValueAt(fila, 2).toString());
    txtGenero.setText(tabla.getValueAt(fila, 3).toString());
 

    String valorParaCombo = tabla.getValueAt(fila, 4).toString();

} else {
    JOptionPane.showMessageDialog(null, "Selección no válida en la tabla.");
}
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String fechaDevolucion="";
        String fechaDevuelto = new java.text.SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        long diferenciaEnMilisegundos=0;
        long diasDeRetraso=0;
        int montoMora=0;
        
        if (txtid.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para continuar.");
} else {
    try {
        String usuario = nombreUsuario.toUpperCase();
 
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

        PreparedStatement psSelect = cn.prepareStatement("SELECT fecha_devolucion FROM prestamos WHERE id_prestmo = ?");
        int idLibro = Integer.parseInt(txtIdLibro.getText().trim());
        int id = Integer.parseInt(txtid.getText().trim());
        psSelect.setInt(1, id);
        ResultSet rsSelect = psSelect.executeQuery();

        if (rsSelect.next()) {
            fechaDevolucion = rsSelect.getString("fecha_devolucion");
        }

        String mora = "NO";
  
        if (fechaDevolucion != null) {
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaDevolucionDate = dateFormat.parse(fechaDevolucion);
            java.util.Date fechaDevueltoDate = dateFormat.parse(fechaDevuelto);

            if (fechaDevueltoDate.after(fechaDevolucionDate)) {
                mora = "SI";
                diferenciaEnMilisegundos = fechaDevueltoDate.getTime() - fechaDevolucionDate.getTime();
                diasDeRetraso = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24); 
                montoMora = (int) (diasDeRetraso * 15); 

                int respuesta = JOptionPane.showConfirmDialog(
                    null, 
                    "Este libro tiene días de retraso " + diasDeRetraso + 
                    " y una mora de Q. " + String.format("%,.2f", (double)montoMora) + 
                    ".\n¿Desea continuar con la actualización?", 
                    "Confirmar Mora", 
                    JOptionPane.YES_NO_OPTION
                );

                if (respuesta != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                    return;
                }
            }

            // Actualizar el préstamo
            PreparedStatement psUpdate = cn.prepareStatement(
                "UPDATE prestamos SET devuelto = ?, fecha_devuelto = ?, mora = ?, monto_mora = ? WHERE id_prestmo = ?");
            psUpdate.setString(1, "SI");
            psUpdate.setString(2, fechaDevuelto);
            psUpdate.setString(3, mora);
            psUpdate.setInt(4, montoMora);
            psUpdate.setInt(5, id);
            psUpdate.executeUpdate();


            PreparedStatement psUpdateCantidad = cn.prepareStatement(
                "UPDATE libros SET cantidad = cantidad + 1 WHERE id_libro = ?");
            psUpdateCantidad.setInt(1, idLibro);
            psUpdateCantidad.executeUpdate();

      
            PreparedStatement psResenia = cn.prepareStatement(
                "INSERT INTO resenia (id_libro, titulo, isbn, ranking, fecha, usuario,comentario) VALUES (?,?, ?, ?, ?, ?, ?)");
            psResenia.setInt(1, idLibro);
            psResenia.setString(2, txtGenero.getText().trim());
            psResenia.setString(3, txtIsbn.getText().trim());
            psResenia.setInt(4, Integer.parseInt(cbValoracion.getSelectedItem().toString()));
            psResenia.setString(5, fechaDevuelto);
            psResenia.setString(6, usuario);
            psResenia.setString(7, txtComentario.getText().trim());
            psResenia.executeUpdate();

           
            PreparedStatement psPromedio = cn.prepareStatement(
                "SELECT AVG(ranking) AS promedio FROM resenia WHERE id_libro = ?");
            psPromedio.setInt(1, idLibro);
            ResultSet rsPromedio = psPromedio.executeQuery();

            if (rsPromedio.next()) {
                double promedio = rsPromedio.getDouble("promedio");

            
                PreparedStatement psUpdatePromedio = cn.prepareStatement(
                    "UPDATE libros SET rating_promedio = ? WHERE id_libro = ?");
                psUpdatePromedio.setDouble(1, promedio);
                psUpdatePromedio.setInt(2, idLibro);
                psUpdatePromedio.executeUpdate();

                // Cierre de PreparedStatements
                psUpdatePromedio.close();
            }

            
            
            
            cargarTablaUsuarios();
            limpiarcampos();
            JOptionPane.showMessageDialog(null, "Libro devuelto, reseña registrada y rating actualizado.");

              Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT email FROM usuarios WHERE usuario = '" + usuario + "' AND ACTIVO = 'SI'");
            String email = "";
                   if (rs.next()) {
            email = rs.getString("email");
                   String correoDestino = email;
        String asunto = "Devolucion Libro";
        String mensaje = "Se ha realizado la devolucion de libro: " + txtIsbn.getText().trim() + "\n" +
                "Fecha de Devuelto: " + fechaDevuelto + "\n" +
                 "Fecha de Devolución Programada: " + fechaDevolucion + "\n" +
                 "Dias atraso; "+diasDeRetraso+ " Mora: Q." +montoMora;

            ControladorCorreo.enviarCorreo(correoDestino, asunto, mensaje);
        } else {
            JOptionPane.showConfirmDialog(null, "No se encontro correo electornico");
        }
      
            
            // Cierres
            rsPromedio.close();
            psPromedio.close();
            psResenia.close();
            psUpdateCantidad.close();
            psUpdate.close();
            
            
            
        }

        rsSelect.close();
        psSelect.close();
        cn.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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
        modelo.addColumn("ID Prestamo");
        modelo.addColumn("Id Libro");
        modelo.addColumn("Isbn");
        modelo.addColumn("Titulo");
        modelo.addColumn("Usuario");
        modelo.addColumn("Fecha_Prestamo");
        modelo.addColumn("Fecha_Devolucion");
        modelo.addColumn("Devuelto");
        String usuario= nombreUsuario.toUpperCase();

        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        Statement st= (Statement) cn.createStatement();
        ResultSet rs = st.executeQuery(
          "SELECT a.id_prestmo, a.ID_libro, a.isbn, b.titulo, a.usuario, a.fecha_prestamo, a.fecha_devolucion, a.devuelto " +
          "FROM prestamos a " +
          "LEFT JOIN libros b ON a.id_libro = b.id_libro " +
          "WHERE a.usuario = '" + usuario + "' AND a.devuelto = 'NO' AND (" +
          "CAST(a.id_prestmo AS CHAR) LIKE '%" + busc + "%' OR " +
          "CAST(a.ID_libro AS CHAR) LIKE '%" + busc + "%' OR " +
          "a.isbn LIKE '%" + busc + "%' OR " +
          "b.titulo LIKE '%" + busc + "%' OR " +
          "a.usuario LIKE '%" + busc + "%' OR " +
          "a.fecha_prestamo LIKE '%" + busc + "%' OR " +
          "a.fecha_devolucion LIKE '%" + busc + "%' OR " +
          "a.devuelto LIKE '%" + busc + "%')"
      );  
        while (rs.next()){
            String[] fila= new String[8];
            fila [0]= rs.getString(1);
            fila [1]= rs.getString(2);
            fila [2]= rs.getString(3);
            fila [3]= rs.getString(4);
            fila [4]= rs.getString(5);
            fila [5]= rs.getString(6);
            fila [6]= rs.getString(7);
            fila [7]= rs.getString(8);
            
            modelo.addRow(fila); 
        }
        tabla.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
            
        }
        
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        autor aut=new autor();
        aut.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(formDevoluciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formDevoluciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formDevoluciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formDevoluciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formDevoluciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbValoracion;
    private javax.swing.JComboBox<String> cboxTipoArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextArea txtComentario;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtIdLibro;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
