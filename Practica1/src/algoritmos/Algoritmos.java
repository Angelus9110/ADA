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
        PrintWriter pwN = null;
        FileWriter ficheroM = null;
        PrintWriter pwM = null;
            try{
                ficheroN = new FileWriter("ResultadoNormal.csv");
                ficheroM = new FileWriter("ResultadoMedias.csv");
                pwN = new PrintWriter(ficheroN);
                pwM = new PrintWriter(ficheroM);
                //for (int l = 0; l < 10; l++)
        pwN.println("Tamaño"+";"+"Tiempo binaria"+";"+"comparacionesBinaria"+";"+"Tiempo Secuencial"+";"+"comparacionesSecuencial");       
        for (int i = 1; i <= 5; i++){
            int size = ((int)Math.pow(10, i));
            int[] vector = new int[size];
            
            for (int k = 0; k < 30; k++){
                for (int j = 0; j < vector.length; j++){
                    vector[j] = (int)Math.floor(Math.random()*((int)Math.pow(10, i)));
                }
                /*for (int j : vector) {
                    System.out.print(j+", ");
                }*/
                vector = insercion(vector);
                int comparacionesBinaria = 0;
                int comparacionesSecuencial = 0;
                int numeroBuscar = (int)Math.floor(Math.random()*((int)Math.pow(10, i)));
                long inicioBinaria = System.nanoTime();
                comparacionesBinaria = busquedaBinaria(vector, numeroBuscar);
                long finBinaria =  System.nanoTime();
                //System.out.println("Binaria: "+ (finBinaria - inicioBinaria));
                //System.out.println("Comparaciones Binaria: "+ comparacionesBinaria);
                
                
                long inicioSecuencial = System.nanoTime();
                comparacionesSecuencial = busquedaSecuencial(vector, numeroBuscar);
                long finSecuencial = System.nanoTime();
                //System.out.println("Tiempo Secuencial: "+ (finSecuencial - inicioSecuencial));
                //System.out.println("Comparaciones Secuencial: "+ comparacionesSecuencial);
                
                pw.println(size+";"+(finBinaria - inicioBinaria)+";"+comparacionesBinaria+";"+(finSecuencial - inicioSecuencial)+";"+comparacionesSecuencial);
                
                
                
                
                //System.out.println((finBinaria - inicioBinaria)+";"+comparacionesBinaria+";"+(finSecuencial - inicioSecuencial)+";"+comparacionesSecuencial);
            }
        }
        } catch (Exception e) {
                    e.printStackTrace();
                }
            finally {
                    try {
                    // Nuevamente aprovechamos el finally para 
                    // asegurarnos que se cierra el fichero.
                        if (null != fichero)
                        fichero.close();
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
        for(int i = 0; i < vector.length; i++){//recorremos todo el arreglo
            comparaciones++;
            if(vector[i] == dato){//comparamos el elemento en el arreglo con el buscado
                posicion = i;//Si es verdadero guardamos la posicion
                break;//Para el ciclo
            }
        }
        return comparaciones;
    }
    
    public static int[] insercion(int vector[]){
        int p, j;
        int aux;
        for (p = 1; p < vector.length; p++){ // desde el segundo elemento hasta
              aux = vector[p]; // el final, guardamos el elemento y
              j = p - 1; // empezamos a comprobar con el anterior
              while ((j >= 0) && (aux < vector[j])){ // mientras queden posiciones y el
                                                // valor de aux sea menor que los
                             vector[j + 1] = vector[j];   // de la izquierda, se desplaza a
                             j--;               // la derecha
              }
              vector[j + 1] = aux; // colocamos aux en su sitio
        }
        return vector;
    }
    
}
