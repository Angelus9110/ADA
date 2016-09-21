/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;
import java.math.*;

/**
 *
 * @author angel
 */
public class Algoritmos {
    
    public static void main(String args[]){
        for (int i = 1; i <= 5; i++){
            int[] vector = new int[((int)Math.pow(10, i))];
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
                double inicioBinaria = (System.nanoTime())*Math.pow(10, -3);
                comparacionesBinaria = busquedaBinaria(vector, numeroBuscar);
                double finBinaria =  (System.nanoTime())*Math.pow(10, -3);
                //System.out.println("Binaria: "+ (finBinaria - inicioBinaria));
                //System.out.println("Comparaciones Binaria: "+ comparacionesBinaria);
                
                
                double inicioSecuencial = (System.nanoTime())*Math.pow(10, -3);
                comparacionesSecuencial = busquedaSecuencial(vector, numeroBuscar);
                double finSecuencial = (System.nanoTime())*Math.pow(10, -3);
                //System.out.println("Tiempo Secuencial: "+ (finSecuencial - inicioSecuencial));
                //System.out.println("Comparaciones Secuencial: "+ comparacionesSecuencial);
                
                System.out.println((finBinaria - inicioBinaria)+";"+comparacionesBinaria+";"+(finSecuencial - inicioSecuencial)+";"+comparacionesSecuencial);
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
