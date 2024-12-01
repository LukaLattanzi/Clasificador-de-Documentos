import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAyuda implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame ayudaFrame = new JFrame("Ayuda - Clasificador de Documentos");
        ayudaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ayudaFrame.setLayout(new BorderLayout());
        ayudaFrame.setResizable(false);

        Font fuenteGeneral = new Font("Serif", Font.PLAIN, 14);

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelInstrucciones = new JLabel("Instrucciones:");
        labelInstrucciones.setFont(fuenteGeneral);
        labelInstrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstrucciones);

        JLabel labelInstUno = new JLabel("1. Selecciona un directorio: Ingresa un directorio válido donde se organizarán los archivos.");
        labelInstUno.setFont(fuenteGeneral);
        labelInstUno.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstUno);

        JLabel labelInstDos = new JLabel("2. Elige un filtro de clasificación:");
        labelInstDos.setFont(fuenteGeneral);
        labelInstDos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstDos);

        JLabel labelInstDosA = new JLabel("- Tipo de archivo: Agrupa por extensión (.jpg, .pdf, etc.).");
        labelInstDosA.setFont(fuenteGeneral);
        labelInstDosA.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstDosA);

        JLabel labelInstDosB = new JLabel("- Tamaño: Clasifica en: < 1MB, 1-10MB, 11-100MB, 101-500MB, 501-1000MB, > 1000MB.");
        labelInstDosB.setFont(fuenteGeneral);
        labelInstDosB.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstDosB);

        JLabel labelInstDosC = new JLabel("- Fecha: Agrupa por fecha de modificación con el formato yyyy-MM");
        labelInstDosC.setFont(fuenteGeneral);
        labelInstDosC.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstDosC);

        JLabel labelInstTres = new JLabel("3. Clasificar: Presiona 'Clasificar' para organizar los archivos en carpetas.");
        labelInstTres.setFont(fuenteGeneral);
        labelInstTres.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstTres);

        JLabel labelInstCuatro = new JLabel("4. Deshacer: Si deseas deshacer la clasificación, presiona 'Deshacer'.");
        labelInstCuatro.setFont(fuenteGeneral);
        labelInstCuatro.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelInstCuatro);

        JLabel labelNota = new JLabel("Nota: El filtro de clasificación viene preseleccionado por tipo de archivo de manera predeterminada.");
        labelNota.setFont(fuenteGeneral);
        labelNota.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelNota);

        ayudaFrame.add(panelTexto, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(fuenteGeneral);
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ayudaFrame.dispose();
            }
        });
        panelBoton.add(btnCerrar);

        ayudaFrame.add(panelBoton, BorderLayout.SOUTH);

        ayudaFrame.pack();
        ayudaFrame.setLocationRelativeTo(null);
        ayudaFrame.setVisible(true);
    }
}