package es.acceso_a_datos.controladores;

import es.acceso_a_datos.modelos.DatosCacheados;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorDatos {
    // Leer y guardar CSV

    public DatosCacheados datosCacheados = DatosCacheados.getInstance();

    /**
     * Carga los datos de un archivo CSV en memoria
     * @param rutaArchivo Ruta del archivo CSV a cargar
     * @param nombreCache Nombre con el que se guardará en caché
     * @throws IOException Si hay error al leer el archivo
     */
    public void cargarCsv(String rutaArchivo, String nombreCache) throws IOException {
        List<String[]> datos = leerCsv(rutaArchivo);
        datosCacheados.addDato(nombreCache, datos);
    }

    /**
     * Lee un archivo CSV y devuelve sus datos como una lista de arrays
     * @param rutaArchivo Ruta del archivo CSV a leer
     * @return Lista de arrays con los datos del CSV
     * @throws IOException Si hay error al leer el archivo
     */
    public List<String[]> leerCsv(String rutaArchivo) throws IOException {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                datos.add(valores);
            }
        }
        return datos;
    }

    /**
     * Guarda datos en un archivo CSV
     * @param datos Lista de arrays con los datos a guardar
     * @param rutaArchivo Ruta donde se guardará el archivo CSV
     * @throws IOException Si hay error al escribir el archivo
     */
    public void guardarCsv(List<String[]> datos, String rutaArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String[] fila : datos) {
                bw.write(String.join(",", fila));
                bw.newLine();
            }
        }
    }
}
