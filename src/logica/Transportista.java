/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import static java.lang.Thread.sleep;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Isaac A. Marin
 */
public class Transportista extends Observable implements Runnable{
    
    private Entorno ent;
    //private boolean recolectar = true;
    private String name;
    private Mercado m;
    
    public Transportista(String name, Entorno entorno, Mercado merca,Observer obsrvr){
        ent = entorno;
        addObserver(obsrvr);
        this.name = name;
        m = merca;
    }
    
    @Override
    public void run(){
        while(true){
            
            int va=ent.vaciarSilo();
            Campo c = new Campo();
            c.name="envio";
            setChanged();
            notifyObservers(c);
            m.depositarAlAlmacen(va);
            Campo sd = new Campo();
            sd.name="alma";
            sd.cantidad=m.almacen;
            setChanged();
            notifyObservers(sd);
            try {
                sleep(3000);
            } catch (InterruptedException e) {}
            Campo cx = new Campo();
            cx.name="regreso";
            setChanged();
            notifyObservers(cx);
        }
    }
    
}
