import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Clasificador {

    private List<File> archivosMovidos = new ArrayList<>();

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
        });
        clasificadorThread.start();
    }

    private void clasificarPorTipo(File archivo, String directorio) throws IOException {
        String extension = obtenerExtension(archivo.getName());
        File destino = new File(directorio + File.separator + extension);
        if (!destino.exists()) {
            destino.mkdirs();
        }
        Files.move(archivo.toPath(), Path.of(destino.getPath(), archivo.getName()),
                StandardCopyOption.REPLACE_EXISTING);
        archivosMovidos.add(new File(destino.getPath(), archivo.getName()));
    }

    private void clasificarPorTamaño(File archivo, String directorio) throws IOException {
        long tamaño = archivo.length();
        String tamañoCategoria;
        if (tamaño < 1024) {
            tamañoCategoria = "Pequeño";
        } else if (tamaño < 1048576) {
            tamañoCategoria = "Mediano";
        } else {
            tamañoCategoria = "Grande";
        }
        File destino = new File(directorio + File.separator + tamañoCategoria);
        if (!destino.exists()) {
            destino.mkdirs();
        }
        Files.move(archivo.toPath(), Path.of(destino.getPath(), archivo.getName()),
                StandardCopyOption.REPLACE_EXISTING);
        archivosMovidos.add(new File(destino.getPath(), archivo.getName()));
    }

    private void clasificarPorFecha(File archivo, String directorio) throws IOException {
        long ultimaModificacion = archivo.lastModified();
        Date fecha = new Date(ultimaModificacion);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String fechaStr = sdf.format(fecha);
        File destino = new File(directorio + File.separator + fechaStr);
        if (!destino.exists()) {
            destino.mkdirs();
        }
        Files.move(archivo.toPath(), Path.of(destino.getPath(), archivo.getName()),
                StandardCopyOption.REPLACE_EXISTING);
        archivosMovidos.add(new File(destino.getPath(), archivo.getName()));
    }

    private String obtenerExtension(String nombreArchivo) {
        int i = nombreArchivo.lastIndexOf('.');
        if (i > 0) {
            return nombreArchivo.substring(i + 1).toLowerCase();
        }
        return "sin_extension";
    }

    public List<File> getArchivosMovidos() {
        return archivosMovidos;
    }
}