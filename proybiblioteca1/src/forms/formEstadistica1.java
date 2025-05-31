/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;
import static forms.login.perfil_color;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import proybiblioteca.Conexion;
import java.sql.SQLException;


/**
 *
 * @author ronychoche
 */
public class formEstadistica1 extends javax.swing.JFrame {

    /**
     * Creates new form formEstadistica1
     */
    public formEstadistica1() {

        initComponents();
        crearGrafica();

        colorfondo();
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
        

            public void colorfondo(){
        try {
            
    getContentPane().setBackground(Color.decode(perfil_color));
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "El valor de perfil_color no es un código de color válido.");
}

    }
    
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();

private void crearGrafica() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    try {
        String sql = "SELECT titulo, rating_promedio " +
                     "FROM libros " +
                     "WHERE rating_promedio > 0 " +
                     "ORDER BY rating_promedio DESC " +
                     "LIMIT 5";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String titulo = rs.getString("titulo");
            double rating = rs.getDouble("rating_promedio");
            dataset.addValue(rating, "Rating", titulo); // "Rating" es la serie
        }

        rs.close();
        st.close();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al consultar datos: " + ex.getMessage());
    }

    // Crear el gráfico
    JFreeChart chart = ChartFactory.createBarChart(
            "Top 5 Libros con Mejor Rating",
            "Título del Libro",
            "Rating Promedio",
            dataset
    );

    // Rotar etiquetas del eje X
    CategoryPlot plot = chart.getCategoryPlot();
    CategoryAxis xAxis = plot.getDomainAxis();
    xAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0)); // 45 grados

    // Personalizar colores individuales
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    Color[] colores = new Color[] { 
        new Color(79, 129, 189),    // Azul
        new Color(192, 80, 77),     // Rojo
        new Color(155, 187, 89),    // Verde
        new Color(128, 100, 162),   // Morado
        new Color(75, 172, 198)     // Cian
    };
    
    // Asignar color distinto a cada barra (en el renderizador)
    int barras = dataset.getColumnCount(); // columnas = barras
    for (int i = 0; i < barras; i++) {
        renderer.setSeriesPaint(0, colores[i % colores.length]);
    }

    // Panel del gráfico
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(800, 600));

    this.setLayout(new BorderLayout());
    this.add(chartPanel, BorderLayout.CENTER);
    this.pack();
    this.setLocationRelativeTo(null); // Centrar ventana
}


     @SuppressWarnings("unchecked")
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  
    public static void main(String args[]) {
 java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formEstadistica1().setVisible(true);
            }
        });
               
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
