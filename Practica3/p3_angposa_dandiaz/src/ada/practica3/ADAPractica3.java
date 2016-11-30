/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada.practica3;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class ADAPractica3 {

    private static File archivo = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    
    private static FileReader fr2 = null;
    private static BufferedReader br2 = null;
    private static ArrayList<Character> nodeMatrix = new ArrayList<Character>();
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            //String ficheroEntrada = JOptionPane.showInputDialog("Nombre fichero entrada");
            //archivo = new File(ficheroEntrada);
            archivo = new File("entrada.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
                      
            String line;
            while ((line = br.readLine()) != null) {
                if (!nodeMatrix.contains(line.charAt(4))){
                    nodeMatrix.add(line.charAt(4));
                }
            }
            
            int adjacenceMatrix[][] = new int[nodeMatrix.size()][nodeMatrix.size()];
            
            for (int i = 0; i<adjacenceMatrix.length; i++){
                for (int j = 0; j<adjacenceMatrix.length; j++){
                    if (i == j){
                        adjacenceMatrix[i][j] = 0;
                    }
                    else{       
                        adjacenceMatrix[i][j] = -1;
                    }
                }
            }
            
            fr2 = new FileReader(archivo);
            br2 = new BufferedReader(fr2);
            
            while ((line = br2.readLine()) != null) {
                adjacenceMatrix[nodeMatrix.indexOf(line.charAt(4))][nodeMatrix.indexOf(line.charAt(18))] = 1;
            }
            ArrayList<Integer> recorridos = new ArrayList<Integer>();
            recorridos = recorridoAnchura(adjacenceMatrix);
            
            for (Integer recorrido : recorridos) {   
                
                if (recorrido != -1){
                    System.out.println(nodeMatrix.get(recorrido));
                }
            }
            
            /*or (int i = 0; i<adjacenceMatrix.length; i++){
                for (int j = 0; j<adjacenceMatrix.length; j++){
                    
                    System.out.print("|"+adjacenceMatrix[i][j]);
                    
                }
                System.out.print("|");
                System.out.println("");
            }*/
            
        } catch (HeadlessException | IOException e) {
            e.addSuppressed(e);
        } finally {
            try {
                if (null != fr | null != fr2) {
                    fr.close();
                    fr2.close();
                }
            } catch (Exception e2) {
                e2.addSuppressed(e2);
            }
        }
    }
    public static ArrayList<Integer> recorridoAnchura(int adjacenceMatrix[][]) {
        int nodoI = 0;
    boolean[] visitiadoAnchura = new boolean[10];
//Lista donde guardo los nodos recorridos

        ArrayList<Integer> recorridos = new ArrayList<Integer>();

//El nodo inicial ya está visitado

        visitiadoAnchura[nodoI] = true;

//Cola de visitas de los nodos adyacentes

        ArrayList<Integer> cola = new ArrayList<Integer>();

//Se lista el nodo como ya recorrido

        recorridos.add(nodoI);

//Se agrega el nodo a la cola de visitas

        cola.add(nodoI);

//Hasta que visite todos los nodos

        while (!cola.isEmpty()) {

            int j = cola.remove(0); //Se saca el primero nodo de la cola

//Se busca en la matriz que representa el grafo los nodos adyacentes

            for (int i = 0; i < adjacenceMatrix.length; i++) {

//Si es un nodo adyacente y no está visitado entonces

                if (adjacenceMatrix[j][i] == 1 && !visitiadoAnchura[i] && adjacenceMatrix[j][i] != -1) {

                    cola.add(i);//Se agrega a la cola de visitas

                    recorridos.add(i);//Se marca como recorrido

                    visitiadoAnchura[i] = true;//Se marca como visitado

                }

            }

        }

        return recorridos;//Devuelvo el recorrido del grafo en anchura

    }
}
