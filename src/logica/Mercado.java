/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaac A. Marin
 */
public class Mercado {
    int almacen = 0;
    
    public synchronized void depositarAlAlmacen(int valor){
        int tal = almacen+valor;
        while(almacen == 1000 || tal>1000 ){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mercado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        almacen+=valor;
        notifyAll();
    }
    
    public synchronized void abastecer(){
        while(almacen<49){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mercado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mercado.class.getName()).log(Level.SEVERE, null, ex);
        }
        almacen-=50;
        notifyAll();
    }
}
