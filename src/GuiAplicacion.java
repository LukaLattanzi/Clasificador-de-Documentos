import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;

public class GuiAplicacion {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Clasificador de Documentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Font fuenteGeneral = new Font("Serif", Font.PLAIN, 14);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelDirectorio = new JLabel("Directorio:");
        labelDirectorio.setFont(fuenteGeneral);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(labelDirectorio, gbc);

        JTextField textDirectorio = new JTextField();
        textDirectorio.setFont(fuenteGeneral);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panelPrincipal.add(textDirectorio, gbc);

        JButton btnAyuda = new JButton("?");
        btnAyuda.setFont(fuenteGeneral);
        btnAyuda.addActionListener(new GuiAyuda());
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(btnAyuda, gbc);

        JLabel labelFiltro = new JLabel("Filtro Clasificador:");
        labelFiltro.setFont(fuenteGeneral);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        panelPrincipal.add(labelFiltro, gbc);

        JRadioButton rbTipo = new JRadioButton("Tipo de Archivo");
        rbTipo.setFont(fuenteGeneral);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        rbTipo.setSelected(true);
        panelPrincipal.add(rbTipo, gbc);

        JRadioButton rbTamaño = new JRadioButton("Tamaño");
        rbTamaño.setFont(fuenteGeneral);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelPrincipal.add(rbTamaño, gbc);

        JRadioButton rbFecha = new JRadioButton("Fecha de Modificación");
        rbFecha.setFont(fuenteGeneral);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelPrincipal.add(rbFecha, gbc);

        ButtonGroup grupoFiltros = new ButtonGroup();
        grupoFiltros.add(rbTipo);
        grupoFiltros.add(rbTamaño);
        grupoFiltros.add(rbFecha);

        JButton btnClasificar = new JButton("Clasificar");
        btnClasificar.setFont(fuenteGeneral);
        btnClasificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directorio = textDirectorio.getText(); // Obtener directorio ingresado
                if (directorio.isEmpty()) { // Si el directorio está vacío
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    File dir = new File(directorio); // Crear objeto File con el directorio ingresado
                    if (!dir.exists() || !dir.isDirectory()) { // Si el directorio no existe o no es un directorio
                        JOptionPane.showMessageDialog(frame, "El directorio ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // Si el directorio es válido
                        String filtro = rbTipo.isSelected() ? "tipo" : rbTamaño.isSelected() ? "tamaño" : "fecha"; // Obtener filtro seleccionado
                        new GuiClasificar(directorio, filtro).actionPerformed(e); // Llamar a la clase GuiClasificar
                    }
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelPrincipal.add(btnClasificar, gbc);

        JButton btnDeshacer = new JButton("Deshacer");
        btnDeshacer.setFont(fuenteGeneral);
        btnDeshacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directorio = textDirectorio.getText(); // Obtener directorio ingresado
                if (directorio.isEmpty()) { // Si el directorio está vacío
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    File dir = new File(directorio); // Crear objeto File con el directorio ingresado
                    if (!dir.exists() || !dir.isDirectory()) { // Si el directorio no existe o no es un directorio
                        JOptionPane.showMessageDialog(frame, "El directorio ingresado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else { // Si el directorio es válido
                        File logFile = new File(directorio + File.separator + "Movimientos_realizados.txt"); // Crear objeto File con el archivo de log
                        if (!logFile.exists()) { // Si el archivo de log no existe
                            JOptionPane.showMessageDialog(frame, "No se encontró el archivo de log. No se puede deshacer la clasificación.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else { // Si el archivo de log existe
                            new GuiDeshacer(directorio).actionPerformed(e); // Llamar a la clase GuiDeshacer
                        }
                    }
                }
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelPrincipal.add(btnDeshacer, gbc);

        JLabel labelCreditos = new JLabel("<html><a href=''>Github</a></html>");
        labelCreditos.setFont(fuenteGeneral);
        labelCreditos.setHorizontalAlignment(SwingConstants.CENTER);
        labelCreditos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelCreditos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try { // Abrir enlace en el navegador
                    Desktop.getDesktop().browse(new URI("https://github.com/LukaLattanzi"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        panelPrincipal.add(labelCreditos, gbc);

        frame.add(panelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}