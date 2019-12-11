/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaac A. Marin
 */
public class Vendedor extends Observable implements Runnable {

    private String name;
    private int mercancia=0;
    private Mercado merca;

    public Vendedor(String name, Mercado merca, Observer obsrvr) {
        addObserver(obsrvr);
        this.name = name;
        this.merca = merca;
    }

    @Override
    public void run() {
        while (true) {
            if(mercancia==0){
                merca.abastecer();
                Campo c = new Campo();
                c.name = "extra";
                mercancia = 50;
                c.cantidad = mercancia;
                c.id = Integer.parseInt(name);
                c.pos = merca.almacen;
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                setChanged();
                notifyObservers(c);
            }else{
                long val= (long)((Math.random()*2500)+3500);
                try {
                    Thread.sleep(val);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                mercancia=0;
                Campo x = new Campo();
                x.cantidad=0;
                x.name="vacia";
                x.id=Integer.parseInt(name);
                setChanged();
                notifyObservers(x);
                try {
                    Thread.sleep(2200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

}
