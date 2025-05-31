/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import proybiblioteca.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import forms.formAgregarLibro;
import forms.formActualizarLibro;
import java.util.Arrays;

/**
 *
 * @author esleyder-torres
 */
public final class formLibros extends javax.swing.JFrame {

    private final int registrosPorPagina = 10;
    private int paginaActual = 1;
    private List<Object[]> listaCompletaLibros = new ArrayList<>();
    private int totalPaginas;
    DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;

    public formLibros() {
        initComponents();
        colorfondo();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        modeloTabla = new DefaultTableModel();
        tblLibros.setModel(modeloTabla);

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Título");
        modeloTabla.addColumn("ISBN");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Autor");
        modeloTabla.addColumn("Portada");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Editorial");
        modeloTabla.addColumn("Idioma");
        modeloTabla.addColumn("Rating");
        modeloTabla.addColumn("Ingreso");
        modeloTabla.addColumn("Usuario Ingreso");

        sorter = new TableRowSorter<>(modeloTabla);
        tblLibros.setRowSorter(sorter);
        cargarTodosLosLibros();
        llenarComboBoxColumnas();
        actualizarEstadoPaginacion();

    }

    public void colorfondo() {
        try {
            getContentPane().setBackground(java.awt.Color.decode(login.perfil_color));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor de perfil_color no es un código de color válido.");
        }
    }

    private void llenarComboBoxColumnas() {
        for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
            jComboBoxBuscarPor.addItem(modeloTabla.getColumnName(i));
        }
    }

    public void cargarTodosLosLibros() {
        listaCompletaLibros.clear();
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "SELECT Id_libro, Titulo, ISBN, genero, cantidad, Autor, Portada, anio_publicacion, editorial, idioma, Rating_promedio, Fecha_ingreso, usuario_ingreso FROM Libros";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            Object[] datosLibro = new Object[13];
            while (rs.next()) {
                datosLibro[0] = rs.getInt("Id_libro");
                datosLibro[1] = rs.getString("Titulo");
                datosLibro[2] = rs.getString("ISBN");
                datosLibro[3] = rs.getString("genero");
                datosLibro[4] = rs.getInt("cantidad");
                datosLibro[5] = rs.getString("Autor");
                datosLibro[6] = rs.getBytes("Portada");
                datosLibro[7] = rs.getString("anio_publicacion");
                datosLibro[8] = rs.getString("editorial");
                datosLibro[9] = rs.getString("idioma");
                datosLibro[10] = rs.getDouble("Rating_promedio");
                datosLibro[11] = rs.getString("Fecha_ingreso");
                datosLibro[12] = rs.getString("usuario_ingreso");
                listaCompletaLibros.add(datosLibro.clone());
            }
            calcularTotalPaginas();
            mostrarLibrosPorPagina();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los libros: " + e.getMessage());
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    private void calcularTotalPaginas() {
        totalPaginas = (int) Math.ceil((double) listaCompletaLibros.size() / registrosPorPagina);
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
    }

    private void mostrarLibrosPorPagina() {
        modeloTabla.setRowCount(0);
        int inicio = (paginaActual - 1) * registrosPorPagina;
        int fin = Math.min(inicio + registrosPorPagina, listaCompletaLibros.size());

        for (int i = inicio; i < fin; i++) {
            modeloTabla.addRow(listaCompletaLibros.get(i));
        }
        actualizarEstadoPaginacion();
    }

    private void actualizarEstadoPaginacion() {
        if (lblPaginaActual != null) {
            lblPaginaActual.setText("Página " + paginaActual + " de " + totalPaginas);
        }
        if (btnPagAnterior != null) {
            btnPagAnterior.setEnabled(paginaActual > 1);
        }
        if (btnPagSiguiente != null) {
            btnPagSiguiente.setEnabled(paginaActual < totalPaginas);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBoxBuscarPor = new javax.swing.JComboBox<>();
        btnPagSiguiente = new javax.swing.JButton();
        btnPagAnterior = new javax.swing.JButton();
        lblPaginaActual = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnVerDetalles = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_libro", "Titulo", "ISBN", "genero", "cantidad", "Autor", "Portada", "anio_publicacion", "editorial", "idioma", "Rating_promedio", "Fecha_ingreso", "usuario_ingreso"
            }
        ));
        tblLibros.setRowHeight(25);
        jScrollPane1.setViewportView(tblLibros);

        jButton1.setText("ACTUALIZAR LISTA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnPagSiguiente.setText("Sig");
        btnPagSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagSiguienteActionPerformed(evt);
            }
        });

        btnPagAnterior.setText("Ant");
        btnPagAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagAnteriorActionPerformed(evt);
            }
        });

        lblPaginaActual.setText("lblPaginaActual");

        jButton3.setText("AGREGAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("ACTUALIZAR / ELIMINAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnVerDetalles.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        btnVerDetalles.setText("Ver Detalles");
        btnVerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetallesActionPerformed(evt);
            }
        });

        jLabel2.setText("Seleccione un libro para ver los detalles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnPagAnterior)
                                .addGap(10, 10, 10)
                                .addComponent(lblPaginaActual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPagSiguiente))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2)
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82)
                                        .addComponent(jButton4))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(466, 466, 466)
                                .addComponent(btnVerDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 320, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jComboBoxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPagSiguiente)
                    .addComponent(btnPagAnterior)
                    .addComponent(lblPaginaActual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVerDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String columnaSeleccionada = jComboBoxBuscarPor.getSelectedItem().toString();
        String textoBusqueda = txtBusqueda.getText().trim().toLowerCase(); // Obtiene el texto y lo convierte a minúsculas
        DefaultTableModel model = (DefaultTableModel) tblLibros.getModel();
        model.setRowCount(0); // Limpia la tabla antes de mostrar los resultados de la búsqueda

        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String nombreColumnaBD = "";

        switch (columnaSeleccionada) {
            case "Título":
                nombreColumnaBD = "Titulo";
                break;
            case "ISBN":
                nombreColumnaBD = "ISBN";
                break;
            case "Género":
                nombreColumnaBD = "genero";
                break;
            case "Cantidad":
                nombreColumnaBD = "cantidad";
                break;
            case "Autor":
                nombreColumnaBD = "Autor";
                break;
            case "Año":
                nombreColumnaBD = "anio_publicacion";
                break;
            case "Editorial":
                nombreColumnaBD = "editorial";
                break;
            case "Idioma":
                nombreColumnaBD = "idioma";
                break;
            default:
                JOptionPane.showMessageDialog(this, "Columna de búsqueda no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        String sql = "SELECT Titulo, ISBN, genero, cantidad, Autor, anio_publicacion, editorial, idioma FROM Libros WHERE LOWER(" + nombreColumnaBD + ") LIKE '%" + textoBusqueda + "%'";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            Object[] datosLibro = new Object[8];
            while (rs.next()) {
                datosLibro[0] = rs.getString("Titulo");
                datosLibro[1] = rs.getString("ISBN");
                datosLibro[2] = rs.getString("genero");
                datosLibro[3] = rs.getInt("cantidad");
                datosLibro[4] = rs.getString("Autor");
                datosLibro[5] = rs.getString("anio_publicacion");
                datosLibro[6] = rs.getString("editorial");
                datosLibro[7] = rs.getString("idioma");
                model.addRow(datosLibro);
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron libros con el criterio de búsqueda.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al realizar la búsqueda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnPagAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagAnteriorActionPerformed
        if (paginaActual > 1) {
            paginaActual--;
            mostrarLibrosPorPagina();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPagAnteriorActionPerformed

    private void btnPagSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagSiguienteActionPerformed
        if (paginaActual < totalPaginas) {
            paginaActual++;
            mostrarLibrosPorPagina();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPagSiguienteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        formAgregarLibro agregarForm = new formAgregarLibro();
        agregarForm.setVisible(true);
        agregarForm.setLocationRelativeTo(this);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        formActualizarLibro actualizarForm = new formActualizarLibro();
        actualizarForm.setVisible(true);
        actualizarForm.setLocationRelativeTo(this);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarTodosLosLibros();
        mostrarLibrosPorPagina();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnVerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetallesActionPerformed
    int filaSeleccionada = tblLibros.getSelectedRow();

if (filaSeleccionada == -1) {
    JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro para ver los detalles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    return;
}

DefaultTableModel model = (DefaultTableModel) tblLibros.getModel();
Object[] libroSeleccionado = new Object[model.getColumnCount()];

for (int i = 0; i < model.getColumnCount(); i++) {
    libroSeleccionado[i] = model.getValueAt(filaSeleccionada, i);
}

formDetallesLibro detallesForm = new formDetallesLibro();

if (libroSeleccionado.length == 8) {
    Object[] libroDetalle = new Object[13];
    
    // Rellenar todo con null por seguridad
    Arrays.fill(libroDetalle, null);

    libroDetalle[1] = libroSeleccionado[0]; // Titulo
    libroDetalle[2] = libroSeleccionado[1]; // ISBN
    libroDetalle[3] = libroSeleccionado[2]; // Genero
    libroDetalle[4] = libroSeleccionado[3]; // Cantidad
    libroDetalle[5] = libroSeleccionado[4]; // Autor
    libroDetalle[7] = libroSeleccionado[5]; // Año publicación
    libroDetalle[8] = libroSeleccionado[6]; // Editorial
    libroDetalle[9] = libroSeleccionado[7]; // Idioma

    // Opcionales
    libroDetalle[10] = null; // Rating
    libroDetalle[11] = null; // Fecha de ingreso
    libroDetalle[12] = null; // Usuario

    detallesForm.mostrarDetalles(libroDetalle);

} else if (libroSeleccionado.length == 13) {
    detallesForm.mostrarDetalles(libroSeleccionado);
} else {
    JOptionPane.showMessageDialog(this, "Error: La cantidad de datos del libro seleccionado es incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

detallesForm.setVisible(true);
detallesForm.setLocationRelativeTo(this);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerDetallesActionPerformed

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
            java.util.logging.Logger.getLogger(formLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formLibros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPagAnterior;
    private javax.swing.JButton btnPagSiguiente;
    private javax.swing.JButton btnVerDetalles;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxBuscarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPaginaActual;
    private javax.swing.JTable tblLibros;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
