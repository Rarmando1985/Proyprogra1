/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;

import static forms.login.foto_perfil;
import static forms.login.nombreUsuario;
import static forms.login.userRol;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import java.awt.Image;
import static java.awt.SystemColor.window;
import proybiblioteca.Conexion;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author ronychoche
 */


public class formMenuPrincipal extends javax.swing.JFrame {
 private Timer timer;
private int segundos = 0;
private javax.swing.JDialog dialogoCuentaRegresiva;
private javax.swing.JLabel lblCuentaRegresiva;

    /**
   * Creates new form formMenuPrincipal
     */  
    public formMenuPrincipal() {
        initComponents();
       // cargarFoto();
        String usuario="BIENENIDO: "+nombreUsuario.toUpperCase();
         lblUsuario.setText(usuario);
        String roluser="SU ROL ES: "+userRol.toUpperCase();
        lblRol.setText(roluser);
        byte[] fotop=foto_perfil;
        
             if (fotop != null) {
            ImageIcon imagenOriginal = new ImageIcon(fotop);
               
    int ancho = 150;
    int alto = 200;

    
    Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
    
 //           Image imgEscalada = imagen.getImage().getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
            jLabel2.setIcon(new ImageIcon(imagenRedimensionada));
        } else {
            jLabel2.setText("Sin imagen");
        }
     iniciarContador();
    }
    
private void iniciarContador() {
    timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            segundos++;

            int horas = segundos / 3600;
            int minutos = (segundos % 3600) / 60;
            int segundosRestantes = segundos % 60;

            String tiempoFormateado = String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
            lblTimer.setText("Tiempo Inactivo: " + tiempoFormateado);

            if (segundos == 1655) {
                mostrarCuentaRegresiva();
            }

            if (segundos >= 1660) {
                timer.stop(); // Detener el contador
                if (dialogoCuentaRegresiva != null && dialogoCuentaRegresiva.isVisible()) {
                    dialogoCuentaRegresiva.dispose(); // Cerrar el diálogo si aún está visible
                }
                for (java.awt.Window window : java.awt.Window.getWindows()) {
                    window.dispose(); 
                }
            }
        }
    });
    timer.start();
}

private void mostrarCuentaRegresiva() {
    dialogoCuentaRegresiva = new javax.swing.JDialog(this, "Advertencia de Inactividad", false);
    lblCuentaRegresiva = new javax.swing.JLabel("Su sesión se cerrará en 5 segundos", javax.swing.SwingConstants.CENTER);
    lblCuentaRegresiva.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
    dialogoCuentaRegresiva.add(lblCuentaRegresiva);
    dialogoCuentaRegresiva.setSize(300, 100);
    dialogoCuentaRegresiva.setLocationRelativeTo(this);
    dialogoCuentaRegresiva.setVisible(true);

    new Thread(() -> {
        for (int i = 5; i > 0; i--) {
            final int segundosRestantes = i;
            lblCuentaRegresiva.setText("Su sesión se cerrará en " + segundosRestantes + " segundos");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        dialogoCuentaRegresiva.dispose(); // Oculta el diálogo al terminar
    }).start();
}

    public void cargarFoto(){
        try {
            String usuario=nombreUsuario.toUpperCase();
    Conexion cc = new Conexion();
    Connection cn = cc.conectar();

    String sql = "SELECT foto_perfil FROM usuarios WHERE usuario = ?";
    PreparedStatement ps = cn.prepareStatement(sql);
    ps.setString(1, usuario); // puedes cambiar esto por un dato dinámico
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        Blob blob = rs.getBlob("foto_perfil");

        if (blob != null) {
            byte[] datosImagen = blob.getBytes(1, (int) blob.length());

            ImageIcon icono = new ImageIcon(datosImagen);

            // Escalamos la imagen para que se vea bien en el JLabel
            Image imagenEscalada = icono.getImage().getScaledInstance(
                    lblFotoPerfil.getWidth(),
                    lblFotoPerfil.getHeight(),
                    Image.SCALE_SMOOTH
            );

            lblFotoPerfil.setIcon(new ImageIcon(imagenEscalada));
        } else {
            JOptionPane.showMessageDialog(null, "Este usuario no tiene imagen.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
    }
rs.close();
ps.close();
cn.close();
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error al cargar imagen.");
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        lblRol = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        lblFotoPerfil = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);

        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel1MouseWheelMoved(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRol.setBackground(new java.awt.Color(255, 255, 255));
        lblRol.setFont(new java.awt.Font("DejaVu Sans", 3, 24)); // NOI18N
        lblRol.setText("jLabel1");
        lblRol.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(lblRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 40, 430, -1));

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setFont(new java.awt.Font("DejaVu Sans", 3, 24)); // NOI18N
        lblUsuario.setText("jLabel1");
        jPanel1.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 410, -1));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 80, 290, 240));

        lblTimer.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        lblTimer.setText("jLabel1");
        jPanel1.add(lblTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, -1, -1));

        lblFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo-biblioteca-vina-del-mar.jpg"))); // NOI18N
        jPanel1.add(lblFotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 740));

        jMenu1.setText("Administracion");

        jMenuItem4.setText("Nuevo Usuario");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Cambio Password");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Admin Usuarios");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Perfil");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenu5.setText("Perfil color");

        jMenuItem15.setText("Obscuro");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem15);

        jMenuItem16.setText("BernsteinLookAndFeel  ");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);

        jMenuItem17.setText("AcrylLookAndFeel  ");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);

        jMenuItem18.setText("Claro");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem18);

        jMenu1.add(jMenu5);

        jMenuItem7.setText("Cargar Arcivos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Bibliotecario");

        jMenuItem8.setText("Libros");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Autores");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Membros");

        jMenuItem12.setText("Prestamos");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText("Devoluciones");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem11.setText("Top 5 mejores libros");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Ayuda");

        jMenuItem19.setText("Acerca de Nosotros");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem19);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 2353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    for (java.awt.Window window : java.awt.Window.getWindows()) {
    window.dispose();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
       
    if (userRol.equals("Administrador")){
       
        
        formNevoUsuario nuevousuario = new formNevoUsuario();
        nuevousuario.setVisible(true);     
        }else{
            JOptionPane.showMessageDialog(null, "Opcion disponible solo apra administrador su rol es : " + userRol);
        }
       
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
      
      formCambioPassword nuevousuario = new formCambioPassword();
                        nuevousuario.setVisible(true);   
      
         
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if (userRol.equals("Administrador")){
      formAdminUsers adminUser = new formAdminUsers();
                        adminUser.setVisible(true);   
        }else{
            JOptionPane.showMessageDialog(null, "Opcion disponible solo apra administrador su rol es : " + userRol);
        }
       
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        formPerfil perfil = new formPerfil();
        perfil.setVisible(true); 
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
           if (userRol.equals("Administrador")){
      formCargaArchivos cargaArchivos = new formCargaArchivos();
                        cargaArchivos.setVisible(true);   
        }else{
            JOptionPane.showMessageDialog(null, "Opcion disponible solo apra administrador su rol es : " + userRol);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        formPrestamos prestamos =new formPrestamos();
        prestamos.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        formDevoluciones dev= new formDevoluciones();
        dev.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
          String lookAndFeelClassName = "com.jtattoo.plaf.hifi.HiFiLookAndFeel"; 
            String BernsteinLookAndFeel = "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";
       
         
        try {
              UIManager.setLookAndFeel(lookAndFeelClassName);
        } catch (UnsupportedLookAndFeelException ex) {
              Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
            Logger.getLogger(formMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(formMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(formMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
            String BernsteinLookAndFeel = "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";
       
         
        try {
              UIManager.setLookAndFeel(new AcrylLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
              Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
                
        String AcrylLookAndFeel = "com.jtattoo.plaf.bernstein.AcrylLookAndFeel";
       
         
        try {
              UIManager.setLookAndFeel(AcrylLookAndFeel);
        } catch (UnsupportedLookAndFeelException ex) {
              Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
            Logger.getLogger(formMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(formMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(formMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
                
     String AcrylLookAndFeel = "com.jtattoo.plaf.bernstein.MintLookAndFeel";
                
                   try {
              UIManager.setLookAndFeel(new MintLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
              Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
             }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        formEstadistica1 estadist= new formEstadistica1();
        estadist.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        formAcercadeNosotros nosotros = new formAcercadeNosotros();
        nosotros.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
 
         segundos = 0;
       if (dialogoCuentaRegresiva != null && dialogoCuentaRegresiva.isVisible()) {
             dialogoCuentaRegresiva.dispose(); 
        }
       
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel1MouseWheelMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseWheelMoved

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        
        
                 if (userRol.equals("Administrador")|| userRol.equals("Bibliotecario")){
        formAutores autor = new formAutores();
        autor.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Opcion disponible solo apra Bdministrador O Bibliotecario su rol es : " + userRol);
        }
   
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:

                 if (userRol.equals("Administrador")|| userRol.equals("Bibliotecario")){
       formLibros formularioLibros = new formLibros();
        formularioLibros.setVisible(true);
        formularioLibros.setLocationRelativeTo(null);
        }else{
            JOptionPane.showMessageDialog(null, "Opcion disponible solo para Administrador O Bibliotecario su rol es : " + userRol);
        }
                 
       

    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formMenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblFotoPerfil;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
