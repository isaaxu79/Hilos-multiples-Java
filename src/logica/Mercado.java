/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaac A. Marin
 */
public class Mercado {
    int almacen = 0;
    
    Semaphore ent, merc;
    
    public Mercado(Semaphore ent, Semaphore merc){
        this.ent = ent;
        this.merc = merc;
    }
    
    public void depositarAlAlmacen(int valor){
        try {
            merc.acquire();
        } catch (Exception e) {}
        int tal = almacen+valor;
        
        almacen+=valor;
        merc.release();
    }
    
    public boolean abastecer(){
        boolean  x = false;
        try {
            merc.acquire();
        } catch (Exception e) {}
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mercado.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(almacen !=  0 && almacen > 50){
            almacen-=50;
            x=true;
        }
        merc.release();
        return x;
    }
}
