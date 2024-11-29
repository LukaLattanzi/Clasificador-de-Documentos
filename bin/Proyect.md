# Clasificador de Documentos

## Descripción

El Clasificador de Documentos es una aplicación que analiza un directorio y organiza los archivos en subcarpetas según su tipo (imágenes, documentos, música, etc.). Esta herramienta es útil para mantener tus archivos organizados y facilitar su acceso.

## Funcionalidades Clave

- **Selección de Directorio**: Permite al usuario seleccionar un directorio desde la interfaz gráfica.
- **Organización en Tiempo Real**: Muestra el progreso de la organización en tiempo real utilizando threads.
- **Registro de Cambios**: Guarda un log en un archivo de texto con los cambios realizados.

## Funcionalidades Extras

- **Deshacer Organización**: Opción para deshacer la organización y restaurar los archivos a su ubicación original.
- **Filtrado Avanzado**: Filtrar archivos por fecha de creación o tamaño.
- **Notificaciones**: Envía notificaciones al usuario cuando la organización se completa o si ocurre algún error.
- **Interfaz Multilingüe**: Soporte para múltiples idiomas en la interfaz gráfica.















## Requisitos del Sistema

- Java 8 o superior
- Sistema operativo Windows, macOS o Linux

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu-usuario/clasificador-documentos.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd clasificador-documentos
    ```
3. Compila el proyecto:
    ```sh
    javac -d bin src/*.java
    ```
4. Ejecuta la aplicación:
    ```sh
    java -cp bin GuiApp
    ```

## Uso

1. Abre la aplicación.
2. Selecciona el directorio que deseas organizar.
3. Configura las opciones de organización según tus preferencias.
4. Haz clic en "Iniciar" para comenzar la organización.
5. Revisa el log de cambios para ver los detalles de la organización.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para discutir cualquier cambio que desees realizar.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.