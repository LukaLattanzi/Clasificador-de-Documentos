import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Clasificador {

    private List<String> logMovimientos = new ArrayList<>();

    public void clasificar(String directorio, String filtro) {

        File dir = new File(directorio);
        File[] archivos = dir.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("No hay archivos para clasificar en el directorio.");
            return;
        }

        Thread clasificadorThread = new Thread(() -> {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    try {
                        switch (filtro) {
                            case "tipo":
                                clasificarPorTipo(archivo, directorio);
                                break;
                            case "tamaño":
                                clasificarPorTamaño(archivo, directorio);
                                break;
                            case "fecha":
                                clasificarPorFecha(archivo, directorio);
                                break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            guardarLog(directorio);
        });

        clasificadorThread.start();
    }

    private void clasificarPorTipo(File archivo, String directorio) throws IOException {
        String extension = obtenerExtension(archivo.getName()); // Obtener extensión del archivo
        File destino = new File(directorio + File.separator + extension);
        if (!destino.exists()) { // Si no existe el directorio, se crea
            destino.mkdirs();
        }
        Files.move(archivo.toPath(), Path.of(destino.getPath(), archivo.getName()), StandardCopyOption.REPLACE_EXISTING); // Mover archivo
        logMovimientos.add(archivo.getName() + " -> " + destino.getPath()); // Agregar movimiento al log
    }

    private void clasificarPorTamaño(File archivo, String directorio) throws IOException {
        long tamaño = archivo.length(); // Obtener tamaño del archivo
        String tamañoCategoria;
        if (tamaño < 1024 * 1024) { // < 1MB
            tamañoCategoria = "Menos de 1MB";
        } else if (tamaño < 10 * 1024 * 1024) { // 1-10MB
            tamañoCategoria = "1-10MB";
        } else if (tamaño < 100 * 1024 * 1024) { // 11-100MB
            tamañoCategoria = "11-100MB";
        } else if (tamaño < 500 * 1024 * 1024) { // 101-500MB
            tamañoCategoria = "101-500MB";
        } else if (tamaño < 1000 * 1024 * 1024) { // 501-1000MB
            tamañoCategoria = "501-1000MB";
        } else { // > 1000MB
            tamañoCategoria = "Más de 1000MB";
        }
        File destino = new File(directorio + File.separator + tamañoCategoria); 
        if (!destino.exists()) { // Si no existe el directorio, se crea
            destino.mkdirs();
        }
        Files.move(archivo.toPath(), Path.of(destino.getPath(), archivo.getName()), StandardCopyOption.REPLACE_EXISTING); // Mover archivo
        logMovimientos.add(archivo.getName() + " -> " + destino.getPath()); // Agregar movimiento al log
    }

    private void clasificarPorFecha(File archivo, String directorio) throws IOException {
        long ultimaModificacion = archivo.lastModified(); // Obtener fecha de modificación del archivo
        Date fecha = new Date(ultimaModificacion); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); // Formato de fecha
        String fechaStr = sdf.format(fecha); // Convertir fecha a String
        File destino = new File(directorio + File.separator + fechaStr);
        if (!destino.exists()) { // Si no existe el directorio, se crea
            destino.mkdirs();
        }
        Files.move(archivo.toPath(), Path.of(destino.getPath(), archivo.getName()), StandardCopyOption.REPLACE_EXISTING); // Mover archivo
        logMovimientos.add(archivo.getName() + " -> " + destino.getPath()); // Agregar movimiento al log
    }

    private String obtenerExtension(String nombreArchivo) {
        int i = nombreArchivo.lastIndexOf('.'); // Obtener índice del último punto
        if (i > 0) { // Si se encontró un punto
            return nombreArchivo.substring(i + 1).toLowerCase(); // Devolver extensión en minúsculas
        }
        return "sin_extension";
    }

    private void guardarLog(String directorio) {
        File logFile = new File(directorio + File.separator + "Movimientos_realizados.txt"); // Crear archivo de log
        try (FileWriter writer = new FileWriter(logFile)) { // Escribir log en archivo
            for (String log : logMovimientos) { 
                writer.write(log + System.lineSeparator()); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}