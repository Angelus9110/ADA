/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.*;

/**
 *
 * @author angel
 */
public class Algoritmos {
    
    public static void main(String args[]) throws IOException{
        
        FileWriter ficheroN = null;
        FileWriter ficheroM = null;
        PrintWriter pwN = null;
        PrintWriter pwM = null;
        int repeticiones = 30;
                
        try{
            ficheroN = new FileWriter("ResultadoNormal.csv");
            ficheroM = new FileWriter("ResultadoMedias.csv");
            pwN = new PrintWriter(ficheroN);
            pwM = new PrintWriter(ficheroM);
            
            pwN.println("Tamaño"+";"+"Tiempo binaria"+";"+"comparacionesBinaria"+";"+"Tiempo Secuencial"+";"+"comparacionesSecuencial");
            pwM.println("Tamaño"+";"+"Tiempo binaria"+";"+"comparacionesBinaria"+";"+"Tiempo Secuencial"+";"+"comparacionesSecuencial");
            
            for (int i = 1; i <= 5; i++){
                int size = ((int)Math.pow(10, i));
                int[] vector = new int[size];
                int sumaComparacionesB = 0;
                int sumaComparacionesS = 0;

                double sumaTiempoB = 0; 
                double sumaTiempoS = 0;
                for (int k = 0; k < repeticiones; k++){
                    for (int j = 0; j < vector.length; j++){
                        vector[j] = (int)Math.floor(Math.random()*((int)Math.pow(10, i)));
                    }

                    vector = insercion(vector);
                    int comparacionesBinaria = 0, comparacionesSecuencial = 0;
                    double tiempoB = 0;
                    double tiempoS = 0;
                    
                    int numeroBuscar = (int)Math.floor(Math.random()*((int)Math.pow(10, i)));
                    
                    double inicioBinaria = System.nanoTime();
                    comparacionesBinaria = busquedaBinaria(vector, numeroBuscar);
                    double finBinaria =  System.nanoTime();
                    tiempoB = finBinaria - inicioBinaria;
                    sumaTiempoB = sumaTiempoB + tiempoB;
                    sumaComparacionesB = sumaComparacionesB + comparacionesBinaria;

                    double inicioSecuencial = System.nanoTime();
                    comparacionesSecuencial = busquedaSecuencial(vector, numeroBuscar);
                    double finSecuencial = System.nanoTime();
                    tiempoS = finSecuencial - inicioSecuencial;
                    sumaTiempoS = sumaTiempoS + tiempoS;
                    sumaComparacionesS = sumaComparacionesS + comparacionesSecuencial;

                    pwN.println(size+";"+tiempoB+";"+comparacionesBinaria+";"+tiempoS+";"+comparacionesSecuencial);             
                }
                pwM.println(size+";"+(sumaTiempoB/repeticiones)+";"+(sumaComparacionesB/repeticiones)+";"+(sumaTiempoS/repeticiones)+";"+(sumaComparacionesS/repeticiones));
            }
        } catch (Exception e) {
                    e.printStackTrace();
                }
            finally {
                    try {
                        if (null != ficheroN)
                        ficheroN.close();
                        if (null != ficheroM)
                        ficheroM.close();
                    } catch (Exception e2) {
                       e2.printStackTrace();
                    }
            }
        
    }
            
    public static int busquedaBinaria(int vector[], int dato){ 
        int n = vector.length;
        int comparaciones = 0;
        int centro,inf=0,sup=n-1;
        while(inf<=sup){ 
            centro=(sup+inf)/2; 
            if(vector[centro]==dato){
                comparaciones++;
                return comparaciones;
            }
            else if(dato < vector [centro] ){
                comparaciones++;
                sup=centro-1; 
            } else { 
                inf=centro+1; 
                comparaciones++;
            }  
        } 
        return comparaciones; 
    }
    
    public static int busquedaSecuencial(int vector[],int dato){
        int posicion = -1;
        int comparaciones = 0;
        for(int i = 0; i < vector.length; i++){
            comparaciones++;
            if(vector[i] == dato){
                posicion = i;
                break;
            }
        }
        return comparaciones;
    }
    
    public static int[] insercion(int vector[]){
        int p, j;
        int aux;
        for (p = 1; p < vector.length; p++){
              aux = vector[p];
              j = p - 1;
              while ((j >= 0) && (aux < vector[j])){
                                                
                             vector[j + 1] = vector[j];
                             j--;
              }
              vector[j + 1] = aux;
        }
        return vector;
    }
    
}