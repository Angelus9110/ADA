package ada.practica3;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Angel Posada
 * @author Daniel Diaz
 */
public class ADAPractica3 {

    /*
    Definimos variables estaticas que seran utilizadas en toda la clase para la
    lectura y escritura de ficheros
     */
    private static File archivo = null;
    private static FileWriter fichero = null;

    private static FileReader fr = null;
    private static BufferedReader br = null;

    private static FileReader fr2 = null;
    private static BufferedReader br2 = null;

    private static PrintWriter pw = null;

    /**
     * Ejecuta el método buscar comunidades pasandole el nombre del fichero por
     * teclado
     *
     * @param args
     */
    public static void main(String[] args) {
        buscarComunidades(JOptionPane.showInputDialog("Introduce el nombre del fichero"));
    }

    /**
     * Implementa el algoritmo de Kosaraju, el cual realiza la busqueda de
     * primero en profundidad a la lista de adyacencia normal y a su inversa de
     * tal forma que obtiene las componentes fuertemente conexas
     *
     * @param graph lista de adyacencia que se recorre
     * @return lista de listas de componentes fuertemente conexas
     */
    public static List<List<Integer>> componentesFuertementeConexas(List<Integer>[] graph) {
        int n = graph.length;
        boolean[] used = new boolean[n];
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                busquedaPrimeroEnProfundidad(graph, used, order, i);
            }
        }

        List<Integer>[] reverseGraph = new List[n];
        for (int i = 0; i < n; i++) {
            reverseGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                reverseGraph[j].add(i);
            }
        }

        List<List<Integer>> components = new ArrayList<>();
        Arrays.fill(used, false);
        Collections.reverse(order);

        for (int u : order) {
            if (!used[u]) {
                List<Integer> component = new ArrayList<>();
                busquedaPrimeroEnProfundidad(reverseGraph, used, component, u);
                components.add(component);
            }
        }

        return components;
    }

    /**
     * Implementacion del algoritmo de busqueda de primero en profundidad
     * mediante recursividad
     *
     * @param graph lista de adyacencia que será recorrida
     * @param used lista de nodos visitados
     * @param res lista de nodos por visitar
     * @param u indice del nodo
     */
    public static void busquedaPrimeroEnProfundidad(List<Integer>[] graph, boolean[] used, List<Integer> res, int u) {
        used[u] = true;
        for (int v : graph[u]) {
            if (!used[v]) {
                busquedaPrimeroEnProfundidad(graph, used, res, v);
            }
        }
        res.add(u);
    }

    /**
     * Inicializa las variables y los arrays, almacena los resultados leidos del
     * fichero en una matriz de adyacencia y posteriormente en una lista de
     * adyacencia, y se llama al metodo componentesFuertementeConexas con esta
     * lista
     *
     * @param nombreFichero nombre del fichero
     */
    public static void buscarComunidades(String nombreFichero) {
        try {
            archivo = new File(nombreFichero);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            /*
            Almacenamos cada letra distinta de la comunidad en un array de caracteres que nos servira para determinar
            el tamaño de la matriz de adyacencia
             */
            ArrayList<String> nodeMatrix = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] palabras = line.split(" ");
                if (!nodeMatrix.contains(palabras[0])) {
                    nodeMatrix.add(palabras[0]);
                }
            }

            // Creamos la matriz de adyacencia con el tamaño adecuado
            int adjacenceMatrix[][] = new int[nodeMatrix.size()][nodeMatrix.size()];

            /*
            Rellenamos la diagonal principal de 0 porque son el mismo vertice, en el resto
            de posiciones almacenamos 100 para simular el valor infinito
             */
            for (int i = 0; i < adjacenceMatrix.length; i++) {
                for (int j = 0; j < adjacenceMatrix.length; j++) {
                    if (i == j) {
                        adjacenceMatrix[i][j] = 0;
                    } else {
                        adjacenceMatrix[i][j] = 100;
                    }
                }
            }

            fr2 = new FileReader(archivo);
            br2 = new BufferedReader(fr2);

            /*
            Leemos cada linea del fichero y almacenamos un 1 en la posicion adecuada para indicar
            que un vertice esta conectado con otro y en que direccion
             */
            while ((line = br2.readLine()) != null) {
                String[] palabras = line.split(" ");
                adjacenceMatrix[nodeMatrix.indexOf(palabras[0])][nodeMatrix.indexOf(palabras[2])] = 1;
            }

            /*
            Generamos la lista de adyacencia a partir de la matriz de adyacencia que habiamos
            generado en intentos anteriores para aprovechar que ya la teniamos hecha
             */
            List<Integer>[] adjacenceList = new List[adjacenceMatrix.length];
            List<Integer> adjacent;

            for (int i = 0; i < adjacenceMatrix.length; i++) {
                adjacent = new ArrayList<>();
                for (int j = 0; j < adjacenceMatrix.length; j++) {
                    if (adjacenceMatrix[i][j] == 1) {
                        adjacent.add(j);
                    }
                }
                adjacenceList[i] = adjacent;
            }

            /*
            Imprimimos las comunidades encontradas
             */
            fichero = new FileWriter("salida_p3_dandiaz_angposa.txt");
            pw = new PrintWriter(fichero);

            for (List<Integer> list : componentesFuertementeConexas(adjacenceList)) {
                if (list.size() >= 3) {
                    pw.println("COMUNIDAD:");
                    boolean primero = true;
                    for (Integer integer : list) {
                        if (!primero) {
                            pw.print(", " + nodeMatrix.get(integer));
                        } else {
                            pw.print(nodeMatrix.get(integer));
                            primero = false;
                        }
                    }
                    pw.println();
                }
            }
        } catch (HeadlessException | IOException e) {
            e.addSuppressed(e);
        } finally {
            try {
                if (null != fr | null != fr2 | null != fichero) {
                    fr.close();
                    fr2.close();
                    fichero.close();
                }
            } catch (IOException e2) {
                e2.addSuppressed(e2);
            }
        }
    }
}
