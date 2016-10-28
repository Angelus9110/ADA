/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada.practica2;

import java.util.Deque;
import java.util.ArrayDeque;
/**
 *
 * @author angel
 */
public class ADAPractica2 {
    
    public static void main(String[] args) {
        // TODO code application logic here
        int suma = 15;
        int numeros[] = {8,6,2,4,3,5};
        int pos = 0;
        int res = 0;
        Deque<Integer> validos = new ArrayDeque();
        elegir(numeros, suma, pos, res, validos);
    }
       
    public static void elegir(int numeros[],int suma,int pos, int res, Deque<Integer> validos){
        if(res == suma){
            Imprimir(validos);
            return;
        }
        if (numeros.length >= (pos+1)){
            
            validos.addLast(numeros[pos]);Imprimir(validos);
            elegir(numeros, suma, pos + 1, res + numeros[pos], validos);

            validos.removeLast();
            elegir(numeros, suma, pos + 1, res, validos);
        }  
    }
    
    public static void Imprimir(Deque<Integer> validos){
        for (Integer valido : validos) {
                    System.out.print(valido+" ");
                }
            System.out.println();
    }
}
