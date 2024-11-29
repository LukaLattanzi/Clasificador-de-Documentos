import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.File;

public class GuiDeshacer implements ActionListener {

    private String directorio;
    private List<File> archivosMovidos;

    public GuiDeshacer(String directorio, List<File> archivosMovidos) {
        this.directorio = directorio;
        this.archivosMovidos = archivosMovidos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame deshacerFrame = new JFrame("Deshacer - Clasificador de Documentos");
        deshacerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deshacerFrame.setLayout(new BorderLayout());
        deshacerFrame.setResizable(false);

        Font fuenteGeneral = new Font("Serif", Font.PLAIN, 14);

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelDeshacer = new JLabel("Deshacer:");
        labelDeshacer.setFont(fuenteGeneral);
        labelDeshacer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelDeshacer);

        JLabel labelLineaUno = new JLabel("Está a punto de deshacer el proceso de clasificación de documentos.");
        labelLineaUno.setFont(fuenteGeneral);
        labelLineaUno.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelLineaUno);

        JLabel labelLineaDos = new JLabel(
                "Si continúa, los archivos en el directorio seleccionado serán restaurados a su ubicación original.");
        labelLineaDos.setFont(fuenteGeneral);
        labelLineaDos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelLineaDos);

        JLabel labelLineaTres = new JLabel("¿Desea continuar?");
        labelLineaTres.setFont(fuenteGeneral);
        labelLineaTres.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTexto.add(labelLineaTres);

        deshacerFrame.add(panelTexto, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(fuenteGeneral);
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeshacerClasificacion deshacerClasificacion = new DeshacerClasificacion(archivosMovidos);
                deshacerClasificacion.deshacer(directorio);
                deshacerFrame.dispose();
            }
        });
        panelBoton.add(btnConfirmar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(fuenteGeneral);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshacerFrame.dispose();
            }
        });
        panelBoton.add(btnCancelar);

        deshacerFrame.add(panelBoton, BorderLayout.SOUTH);

        deshacerFrame.pack();
        deshacerFrame.setLocationRelativeTo(null);
        deshacerFrame.setVisible(true);
    }
}