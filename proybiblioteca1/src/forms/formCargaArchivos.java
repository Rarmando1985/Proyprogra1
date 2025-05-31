/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import static forms.login.nombreUsuario;
import static forms.login.perfil_color;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import proybiblioteca.Conexion;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.List;
import java.util.List;


/**
 *
 * @author ronychoche
 */
public class formCargaArchivos extends javax.swing.JFrame {

    /**
     * Creates new form formCargaArchivos
     */
    public formCargaArchivos() {
        initComponents();
        colorfondo();
                cbxArchivo.addItem("JSON");
        cbxArchivo.addItem("CSV");
        cbxArchivo.addItem("XML");
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
        
        public void guadarLibros(){

            try {
    String ruta = txtArchivo.getText(); 
    String extension = ruta.substring(ruta.lastIndexOf('.') + 1).toLowerCase();
    String usuario= nombreUsuario.toUpperCase();
    
    LocalDate fechaActual = LocalDate.now();

 
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String fechaFormateada = fechaActual.format(formatter);

    Conexion cc = new Conexion();
    Connection cn = cc.conectar();
    String sql = "INSERT INTO libros (titulo, isbn, genero, cantidad, autor, anio_publicacion, editorial, idioma,Fecha_ingreso,usuario_ingreso) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pst = cn.prepareStatement(sql);

    switch (extension) {
        case "json":
            String contenidoJson = new String(Files.readAllBytes(Paths.get(ruta)), "UTF-8");
            JSONArray librosArray = new JSONArray(contenidoJson);

            for (int i = 0; i < librosArray.length(); i++) {
                JSONObject obj = librosArray.getJSONObject(i);

                pst.setString(1, obj.getString("titulo"));
                pst.setString(2, obj.getString("isbn"));
                pst.setString(3, obj.getString("genero"));
                pst.setInt(4, obj.getInt("cantidad"));
                pst.setString(5, obj.getString("autor"));
                pst.setInt(6, obj.getInt("anio_publicacion"));
                pst.setString(7, obj.getString("editorial"));
                pst.setString(8, obj.getString("idioma"));
                pst.setString(9, usuario);
                pst.setString(10,fechaFormateada);
                pst.executeUpdate();
            }
            break;

        case "csv":
            List<String> lineas = Files.readAllLines(Paths.get(ruta));
            for (int i = 1; i < lineas.size(); i++) { 
                String[] datos = lineas.get(i).split(",");

                pst.setString(1, datos[0]);
                pst.setString(2, datos[1]);
                pst.setString(3, datos[2]);
                pst.setInt(4, Integer.parseInt(datos[3]));
                pst.setString(5, datos[4]);
                pst.setInt(6, Integer.parseInt(datos[5]));
                pst.setString(7, datos[6]);
                pst.setString(8, datos[7]);
                pst.setString(9, usuario);
                pst.setString(10,fechaFormateada);
                pst.executeUpdate();
            }
            break;

        case "xml":
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(ruta));
            NodeList libros = doc.getElementsByTagName("libro");

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);

                pst.setString(1, libro.getElementsByTagName("titulo").item(0).getTextContent());
                pst.setString(2, libro.getElementsByTagName("isbn").item(0).getTextContent());
                pst.setString(3, libro.getElementsByTagName("genero").item(0).getTextContent());
                pst.setInt(4, Integer.parseInt(libro.getElementsByTagName("cantidad").item(0).getTextContent()));
                pst.setString(5, libro.getElementsByTagName("autor").item(0).getTextContent());
                pst.setInt(6, Integer.parseInt(libro.getElementsByTagName("anio_publicacion").item(0).getTextContent()));
                pst.setString(7, libro.getElementsByTagName("editorial").item(0).getTextContent());
                pst.setString(8, libro.getElementsByTagName("idioma").item(0).getTextContent());
                pst.setString(9, usuario);
                pst.setString(10,fechaFormateada);
                pst.executeUpdate();
            }
            break;

        default:
            JOptionPane.showMessageDialog(null, "Tipo de archivo no soportado.");
            return;
    }

    pst.close();
    cn.close();
    JOptionPane.showMessageDialog(null, "Libros insertados correctamente.");
    txtArchivo.setText(""); 

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error general: " + e.getMessage());
}

            
            

        }
        
        public void guardarAutores() {
    try {
        String ruta = txtArchivo.getText(); 
        String extension = ruta.substring(ruta.lastIndexOf('.') + 1).toLowerCase();
        String usuario = nombreUsuario.toUpperCase();

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatter);

        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "INSERT INTO autores (nombre, biografia, pais, fecha_ingreso, usuario_ingreso) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = cn.prepareStatement(sql);

        switch (extension) {
            case "json":
                String contenidoJson = new String(Files.readAllBytes(Paths.get(ruta)), "UTF-8");
                JSONArray autoresArray = new JSONArray(contenidoJson);

                for (int i = 0; i < autoresArray.length(); i++) {
                    JSONObject obj = autoresArray.getJSONObject(i);

                    pst.setString(1, obj.getString("nombre"));
                    pst.setString(2, obj.getString("biografia"));
                    pst.setString(3, obj.getString("pais"));
                    pst.setString(4, fechaFormateada);
                    pst.setString(5, usuario);
                    pst.executeUpdate();
                }
                break;

            case "csv":
                List<String> lineas = Files.readAllLines(Paths.get(ruta));
                for (int i = 1; i < lineas.size(); i++) { 
                    String[] datos = lineas.get(i).split(",");

                    pst.setString(1, datos[0]);
                    pst.setString(2, datos[1]);
                    pst.setString(3, datos[2]);
                    pst.setString(4, fechaFormateada);
                    pst.setString(5, usuario);
                    pst.executeUpdate();
                }
                break;

            case "xml":
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new File(ruta));
                NodeList autores = doc.getElementsByTagName("autor");

                for (int i = 0; i < autores.getLength(); i++) {
                    Element autor = (Element) autores.item(i);

                    pst.setString(1, autor.getElementsByTagName("nombre").item(0).getTextContent());
                    pst.setString(2, autor.getElementsByTagName("biografia").item(0).getTextContent());
                    pst.setString(3, autor.getElementsByTagName("pais").item(0).getTextContent());
                    pst.setString(4, fechaFormateada);
                    pst.setString(5, usuario);
                    pst.executeUpdate();
                }
                break;

            default:
                JOptionPane.showMessageDialog(null, "Tipo de archivo no soportado.");
                return;
        }

        pst.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "Autores insertados correctamente.");
        txtArchivo.setText(""); 

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error general: " + e.getMessage());
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

        txtArchivo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cbxArchivo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        esquema = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo Archivo");

        jButton2.setText("Cargar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Datos a cargar");

        esquema.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libros", "Autores" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(esquema, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(esquema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
   String tipoSeleccionado = cbxArchivo.getSelectedItem().toString().toLowerCase();

    
    String descripcionFiltro = "";
    String extension = "";

    switch (tipoSeleccionado) {
        case "json":
            descripcionFiltro = "Archivos JSON (*.json)";
            extension = "json";
            break;
        case "csv":
            descripcionFiltro = "Archivos CSV (*.csv)";
            extension = "csv";
            break;
        case "xml":
            descripcionFiltro = "Archivos XML (*.xml)";
            extension = "xml";
            break;
        default:
            JOptionPane.showMessageDialog(null, "Tipo de archivo no válido");
            return;
    }

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(descripcionFiltro, extension);
    fileChooser.setFileFilter(filtro);

    int resultado = fileChooser.showOpenDialog(null);

    if (resultado == JFileChooser.APPROVE_OPTION) {
        File archivoSeleccionado = fileChooser.getSelectedFile();
        String ruta = archivoSeleccionado.getAbsolutePath();

      
        if (ruta.toLowerCase().endsWith("." + extension)) {
            JOptionPane.showMessageDialog(null, "Archivo cargado: " + ruta);
            txtArchivo.setText(archivoSeleccionado.getAbsolutePath());

        } else {
            JOptionPane.showMessageDialog(null, "El archivo seleccionado no coincide con el tipo elegido: " + extension,
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   String tipoSeleccionado = esquema.getSelectedItem().toString().toLowerCase();
           
   if(tipoSeleccionado.equals("libros")){
       guadarLibros();
   }
    if(tipoSeleccionado.equals("autores")){
         guardarAutores();
   }
   
 
        
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
            java.util.logging.Logger.getLogger(formCargaArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formCargaArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formCargaArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formCargaArchivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formCargaArchivos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxArchivo;
    private javax.swing.JComboBox<String> esquema;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtArchivo;
    // End of variables declaration//GEN-END:variables
}
