/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaac A. Marin
 */
public class Entorno {
    
    int [] campos = {1,2,3,4};
    int camp = 4;
    int silo=0;
    
    public synchronized Response sembrar(){
        Response s = new Response();
        while(silo==500){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        int a = camp;
        if(camp>0){
            camp--;
            s.pos=a;
            s.x=true;
            //System.out.println("sem"+camp);
            return s;
        }
        s.x=false;
        notifyAll();
        return s;
    }
    
    public synchronized int dara(){
        return camp+1;
    }
    
    public synchronized Response recolectar(){
        Response r = new Response();
        while(silo==700){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        
        if(camp<4){
            camp++;
            silo+=40;
            
        }
        r.cantidad=silo;
        r.pos=camp;
        System.out.println(camp);
        r.x=(camp==4);
        notifyAll();
        return r;
    }
    
    public synchronized int vaciarSilo(){
        while(silo<700){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        int valor = silo;
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }
        silo =0;
        try {
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
    
}
