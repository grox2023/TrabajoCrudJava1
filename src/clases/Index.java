
package clases;
import javax.swing.JFrame;
import clases.Conexion;
import formularios.IndexPanel;

    
    public class Index extends JFrame {
    
    public Index() {
        // Configuración de la ventana principal
        setTitle("EconoRegist");
        setSize(800, 600); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    public static void main(String[] args) {
        // Crear e inicializar la ventana principal
        java.awt.EventQueue.invokeLater(() -> {
            IndexPanel ventanaPrincipal = new IndexPanel();
            ventanaPrincipal.setVisible(true);
        });
    }
}
