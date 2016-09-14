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
                System.out.println("Inicio Binaria: "+System.nanoTime());
                busquedaBinaria(vector, (int)Math.floor(Math.random()*((int)Math.pow(10, i))));
                System.out.println("Fin Binaria: "+System.nanoTime());
                System.out.println("Inicio Secuencial: "+System.nanoTime());
                busquedaSecuencial(vector, (int)Math.floor(Math.random()*((int)Math.pow(10, i))));
                System.out.println("Fin Secuencial: "+System.nanoTime());
            }
        }
        
        
    }
            
    public static int busquedaBinaria(int vector[], int dato){ 
        int n = vector.length; 
        int centro,inf=0,sup=n-1; 
        while(inf<=sup){ 
            centro=(sup+inf)/2; 
            if(vector[centro]==dato) 
                return centro; 
            else if(dato < vector [centro] ){ 
                sup=centro-1; 
            } else { inf=centro+1; } 
        } 
        return -1; 
    }
    
    public static int busquedaSecuencial(int []arreglo,int dato){
        int posicion = -1;
        for(int i = 0; i < arreglo.length; i++){//recorremos todo el arreglo
            if(arreglo[i] == dato){//comparamos el elemento en el arreglo con el buscado
                posicion = i;//Si es verdadero guardamos la posicion
                break;//Para el ciclo
            }
        }
        return posicion;
    }
    
    public static int[] insercion(int A[]){
        int p, j;
        int aux;
        for (p = 1; p < A.length; p++){ // desde el segundo elemento hasta
              aux = A[p]; // el final, guardamos el elemento y
              j = p - 1; // empezamos a comprobar con el anterior
              while ((j >= 0) && (aux < A[j])){ // mientras queden posiciones y el
                                                // valor de aux sea menor que los
                             A[j + 1] = A[j];   // de la izquierda, se desplaza a
                             j--;               // la derecha
              }
              A[j + 1] = aux; // colocamos aux en su sitio
        }
        return A;
    }
    
}
