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
 * @author angel
 */
public class ADAPractica2 {

    private static FileWriter fichero = null;
    private static PrintWriter pw = null;

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int suma = Integer.parseInt(JOptionPane.showInputDialog("Valor a buscar"));
        String num[] = JOptionPane.showInputDialog("Valores en los que buscar (separados por espacio)").split(" ");
        int numeros[] = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            numeros[i] = Integer.parseInt(num[i]);
        }
        int pos = 0;
        int res = 0;
        Deque<Integer> validos = new ArrayDeque();

        try {
            fichero = new FileWriter("salida_p2_dandiaz_angposa.txt");
            pw = new PrintWriter(fichero);
        } catch (Exception e) {
            e.printStackTrace();
        }

        elegir(numeros, suma, pos, res, validos);
        try {
            if (null != fichero) {
                fichero.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     *
     * @param numeros
     * @param suma
     * @param pos
     * @param res
     * @param validos
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
     *
     * @param validos
     * @throws IOException
     */
    public static void Imprimir(Deque<Integer> validos) throws IOException {
        for (Integer valido : validos) {
            System.out.print(valido + ", ");
        }
        System.out.println();

        for (Integer valido : validos) {
            pw.print(valido + ", ");
        }
        pw.println();
    }
}
