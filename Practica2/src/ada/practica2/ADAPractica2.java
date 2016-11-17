/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada.practica2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.ArrayDeque;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Diaz y Angel Posada
 */
public class ADAPractica2 {

    private static FileWriter fichero = null;
    private static PrintWriter pw = null;

    /**
     * Recibe los parametros por teclado, inicializa las variables y los arrays y ejecuta el metodo elegir
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String sumaCadena = JOptionPane.showInputDialog("Valor a buscar");
        int suma = -1;
        if(!sumaCadena.equals("")){
            suma = Integer.parseInt(sumaCadena);
        }
        String[] num = JOptionPane.showInputDialog("Valores en los que buscar (separados por espacio)").split(" ");
        int[] numeros = new int[num.length];
        if(!num[0].equals("")){
        for (int i = 0; i < num.length; i++) {
            numeros[i] = Integer.parseInt(num[i]);
        }
        }
        int pos = 0;
        int res = 0;
        Deque<Integer> validos = new ArrayDeque();

        try {
            fichero = new FileWriter("salida_p2_dandiaz_angposa.txt");
            pw = new PrintWriter(fichero);
        } catch (IOException e) {
        }

        elegir(numeros, suma, pos, res, validos);
        try {
            if (null != fichero) {
                fichero.close();
            }
        } catch (IOException e2) {
        }
    }

    /**
     * Realiza la busqueda del valor mediante recursividad y backtracking
     * @param numeros vector de números enteros positivos sin valores repetidos
     * @param suma valor entero a obtener sumando elementos del vector
     * @param pos valor entero que almacena la posición que estamos comprobando del vector
     * @param res valor entero que almacena la suma de los resultados parciales
     * @param validos vector de números enteros que almacena el subconjunto de números que son una posible solución
     * @throws IOException
     */
    public static void elegir(int numeros[], int suma, int pos, int res, Deque<Integer> validos) throws IOException {
        if (res == suma) {
            Imprimir(validos);
            return;
        }
        if (numeros.length >= (pos + 1) && res < suma) {

            validos.addLast(numeros[pos]);//System.out.println("Añade");Imprimir(validos);
            elegir(numeros, suma, pos + 1, res + numeros[pos], validos);

            validos.removeLast();//System.out.println("Elimina");Imprimir(validos);
            elegir(numeros, suma, pos + 1, res, validos);
        }
    }

    /**
     * Imprime las soluciones validas en un fichero
     * @param validos array que representa el subconjunto de elementos validos
     * @throws IOException
     */
    public static void Imprimir(Deque<Integer> validos) throws IOException {
        boolean primero = true;
        for (Integer valido : validos) {
            if(!primero){
                pw.print(", "+valido);
            }else{
                pw.print(valido);
                primero = false;
            }
        }
        pw.println();
    }
}