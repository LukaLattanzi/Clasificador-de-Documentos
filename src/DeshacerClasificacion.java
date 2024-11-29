import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class DeshacerClasificacion {

    private List<String> logMovimientos;

    public DeshacerClasificacion(List<String> logMovimientos) {
        this.logMovimientos = logMovimientos;
    }

    public void deshacer(String directorio) {
        if (logMovimientos == null || logMovimientos.isEmpty()) {
            System.out.println("No hay movimientos para deshacer.");
            return;
        }

        File dir = new File(directorio);
        File[] subdirectorios = dir.listFiles(File::isDirectory);
        if (subdirectorios == null || subdirectorios.length == 0) {
            System.out.println("No hay subdirectorios para deshacer la clasificación.");
            return;
        }

        for (File subdirectorio : subdirectorios) {
            File[] archivos = subdirectorio.listFiles(File::isFile);
            if (archivos != null) {
                for (File archivo : archivos) {
                    Path destinoOriginal = Path.of(directorio, archivo.getName());
                    try {
                        Files.move(archivo.toPath(), destinoOriginal, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            subdirectorio.delete();
        }

        File logFile = new File(directorio + File.separator + "Movimientos_realizados.txt");
        if (logFile.exists()) {
            logFile.delete();
        }

        System.out.println("Clasificación deshecha.");
    }
}