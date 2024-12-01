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

        if (logMovimientos == null || logMovimientos.isEmpty()) { // Verificar si hay movimientos para deshacer
            System.out.println("No hay movimientos para deshacer.");
            return;
        }

        File dir = new File(directorio);
        File[] subdirectorios = dir.listFiles(File::isDirectory);
        if (subdirectorios == null || subdirectorios.length == 0) { // Verificar si hay subdirectorios para deshacer la clasificación
            System.out.println("No hay subdirectorios para deshacer la clasificación.");
            return;
        }

        for (File subdirectorio : subdirectorios) { // Recorrer los subdirectorios
            File[] archivos = subdirectorio.listFiles(File::isFile); // Obtener archivos del subdirectorio
            if (archivos != null) {
                for (File archivo : archivos) {
                    Path destinoOriginal = Path.of(directorio, archivo.getName()); // Crear objeto Path con la ruta original del archivo
                    try {
                        Files.move(archivo.toPath(), destinoOriginal, StandardCopyOption.REPLACE_EXISTING); // Mover archivo al directorio original
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            subdirectorio.delete(); // Eliminar subdirectorio
        }

        File logFile = new File(directorio + File.separator + "Movimientos_realizados.txt"); // Crear objeto File con el archivo de log
        if (logFile.exists()) { // Si existe el archivo de log, se elimina
            logFile.delete();
        }
    }
}