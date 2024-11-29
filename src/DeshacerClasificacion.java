import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class DeshacerClasificacion {

    private List<File> archivosMovidos;

    public DeshacerClasificacion(List<File> archivosMovidos) {
        this.archivosMovidos = archivosMovidos;
    }

    public void deshacer(String directorio) {
        if (archivosMovidos == null || archivosMovidos.isEmpty()) {
            System.out.println("No hay archivos para deshacer la clasificación.");
            return;
        }

        for (File archivo : archivosMovidos) {
            try {
                Path destinoOriginal = Path.of(directorio, archivo.getName());
                Files.move(archivo.toPath(), destinoOriginal, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Clasificación deshecha.");
    }
}