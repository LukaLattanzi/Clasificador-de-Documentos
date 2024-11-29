import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiClasificar implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame clasificarFrame = new JFrame("Clasificar - Clasificador de Documentos");
        clasificarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clasificarFrame.setLayout(new BorderLayout());
        clasificarFrame.setResizable(false);

        Font fuenteGeneral = new Font("Serif", Font.PLAIN, 14);

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelClasificar = new JLabel("Clasificar:");
        labelClasificar.setFont(fuenteGeneral);
        labelClasificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelClasificar);

        JLabel labelLineaUno = new JLabel("Está a punto de comenzar el proceso de clasificación de documentos.");
        labelLineaUno.setFont(fuenteGeneral);
        labelLineaUno.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelLineaUno);

        JLabel labelLineaDos = new JLabel(
                "Si continúa, los archivos en el directorio seleccionado serán organizados según el filtro elegido.");
        labelLineaDos.setFont(fuenteGeneral);
        labelLineaDos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelLineaDos);

        JLabel labelLineaTres = new JLabel("¿Desea continuar?");
        labelLineaTres.setFont(fuenteGeneral);
        labelLineaTres.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelLineaTres);

        clasificarFrame.add(panelTexto, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(fuenteGeneral);
        panelBoton.add(btnConfirmar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(fuenteGeneral);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clasificarFrame.dispose();
            }
        });
        panelBoton.add(btnCancelar);

        clasificarFrame.add(panelBoton, BorderLayout.SOUTH);

        clasificarFrame.pack();
        clasificarFrame.setLocationRelativeTo(null);
        clasificarFrame.setVisible(true);
    }
}