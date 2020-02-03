/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 *
 * @author Isaac A. Marin
 */
public class Granjero extends Observable implements Runnable{
    
    private Entorno ent;
    private boolean recolectar = true;
    private String name;
    
    public Granjero(String name, Entorno entorno, Observer obsrvr){
        ent = entorno;
        addObserver(obsrvr);
        this.name = name;
    }

    @Override
    public void run() {         
        while(true){
            if(recolectar){
                System.out.println("aqiopaso");
                Response s = ent.sembrar();
                recolectar=s.x;
                System.out.println(s.x);
                if(s.x){
                    float tiempo = (float)(Math.random()*1500)+2000;
                    System.out.println("sembrador "+name + " Estoy sembrando maiz");
                    Campo c = new Campo();
                    c.name="e";
                    c.pos= s.getPos();
                    c.id=Integer.parseInt(name);
                    setChanged();
                    notifyObservers(c);
                    try {
                    Thread.sleep((long) tiempo);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Granjero.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }else{
                System.out.println("aqiopasosañ");
                Response s = ent.recolectar();
                recolectar=s.x;
                if(!s.isX() || ent.camp==4){
                    System.out.println("recolector "+name+" Recolecte maiz");
                    Campo c = new Campo();
                    c.name="s";
                    c.cantidad=s.getCantidad();
                    c.pos= s.getPos();
                    c.id=Integer.parseInt(name);
                    setChanged();
                    notifyObservers(c);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Granjero.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }
    
}
