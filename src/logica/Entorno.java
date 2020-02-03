/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaac A. Marin
 */
public class Entorno {

    int[] campos = {1, 2, 3, 4};
    int camp = 4;
    int silo = 0;
    public Semaphore mutex;

    public Entorno(Semaphore mutex) {
        this.mutex = mutex;
    }

    public Response sembrar() {
        Response s = null;
        System.out.println("hola prees");
        try {
            mutex.release();
            mutex.acquire();
        } catch (Exception e) {
        }
        if (silo >= 500) {
             s = new Response();
            s.x = true;
        } else {
            s = new Response();
            int a = camp;
            if (camp > 0) {
                camp--;
                s.pos = a;
                s.x = true;
                //System.out.println("sem"+camp);
                return s;
            }
            s.x = false;
            //notifyAll();
            
        }
        mutex.release();
        return s;
    }

    public int dara() {
        try {
            mutex.acquire();
        } catch (Exception e) {
        }
        camp++;
        mutex.release();
        return camp;
    }

    public Response recolectar() {
        try {
            mutex.acquire();
        } catch (Exception e) {
        }
        Response r = new Response();

        if (camp < 4) {
            camp++;
            silo += 40;
        }
        r.cantidad = silo;
        r.pos = camp;
        System.out.println(camp);
        r.x = (camp == 4);
        mutex.release();
        return r;
    }

    public int vaciarSilo() {
        int valor = 0;
        try {
            mutex.acquire();
        } catch (Exception e) {
        }
        if (silo < 500) {

        } else {
            valor = silo;
            try {
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
            }
            silo = 0;
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        mutex.release();
        return valor;
    }

}
