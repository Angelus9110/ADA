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
import java.lang.Character;
import java.util.Comparator;

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
    private static ArrayList<Character> matrixSize = new ArrayList<Character>();
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
                if (!matrixSize.contains(line.charAt(4))){
                    matrixSize.add(line.charAt(4));
                }
            }
            int adjacenceMatrix[][] = new int[matrixSize.size()][matrixSize.size()];
            for (int i = 0; i<adjacenceMatrix.length; i++){
                for (int j = 0; j<adjacenceMatrix.length; j++){
                    if (i == 0){
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
                if (!matrixSize.contains(line.charAt(4))){
                    matrixSize.add(line.charAt(4));
                }
            System.out.println(matrixSize.indexOf(line.charAt(4)));
            System.out.println(matrixSize.indexOf(line.charAt(18)));
            }
            
            
            
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
}
