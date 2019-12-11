/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;  
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logica.*;



/**
 *
 * @author Isaac A. Marin
 */
public class FXMLDocumentController implements Initializable, Observer {

    @FXML
    private Label label;
    @FXML
    private Rectangle campo1;
    @FXML
    private Rectangle campo2;
    @FXML
    private Rectangle campo3;
    @FXML
    private Rectangle campo4;
    @FXML
    private Rectangle cielo;
    @FXML
    private Rectangle pasto;
    @FXML private ImageView vendedor;
    @FXML private Label almacen;
    @FXML private Label tienda1;
    @FXML private Label tienda2;
    @FXML private Label tienda3;
    @FXML private Label tienda4;
    @FXML private Label tienda5;
    private int contador=0;
    
    TranslateTransition translate = new TranslateTransition();
    

    Thread[] dar = new Thread[2];
    Entorno entorno = new Entorno();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fields();
        //init();

    }

    public void fields() {
        Image img = new Image("/img/t.jpg");
        campo1.setFill(new ImagePattern(img));
        campo2.setFill(new ImagePattern(img));
        campo3.setFill(new ImagePattern(img));
        campo4.setFill(new ImagePattern(img));
        Image ix = new Image("/img/p.jpg");
        pasto.setFill(new ImagePattern(ix));
        Image i = new Image("/img/c.jpg");
        cielo.setFill(new ImagePattern(i));
    }

    @Override
    public void update(Observable o, Object o1) {
        Platform.runLater(() -> {
            Campo c = (Campo) o1;
            switch (c.getName()) {
                case "e":
                    insert(c.getPos());
                    break;
                case "envio":
                    Image im = new Image("/img/c.png");
                    vendedor.setImage(im);
                    translate.setByX(410);
                    translate.setNode(vendedor);
                    translate.play();
                    break;
                case "regreso":
                    Image img = new Image("/img/cv.png");
                    vendedor.setImage(img);
                    translate.setByX(-410);
                    translate.setNode(vendedor);
                    translate.play();
                    break;
                case "alma":
                    almacen.setText(String.valueOf(c.getCantidad()));
                    break;
                case "extra":
                    tiendas(c.getId()+1, c.getCantidad());
                    almacen.setText(String.valueOf(c.getPos()));
                    break;
                case "vacia":
                    System.out.println(c.getCantidad()+ "  " +c.getId());
                    tiendas(c.getId()+1, c.getCantidad());
                    break;
                default:
                    delet(c.getPos());
                    label.setText(String.valueOf(c.getCantidad()));
                    break;
            }
        });
    }

    public void init() {
        for (int i = 0; i < dar.length; i++) {
            dar[i] = new Thread(new Granjero(String.valueOf(i), entorno, this));
            dar[i].start();
        }
        Mercado merca = new Mercado();
        Transportista tra = new Transportista("1",entorno,merca,this);
        Thread t = new Thread(tra);
        t.start();
        Thread[] ts = new Thread[5];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(new Vendedor(String.valueOf(i), merca, this));
            ts[i].start();
        }
    }

    private void insert(int pos) {
        Image img = new Image("/img/g.jpg");
        switch (pos) {
            case 1:
                campo1.setFill(new ImagePattern(img));
                break;
            case 2:
                campo2.setFill(new ImagePattern(img));
                break;
            case 3:
                campo3.setFill(new ImagePattern(img));
                break;
            case 4:
                campo4.setFill(new ImagePattern(img));
                break;
            default:
                System.err.println("eerror bro lo siento");
        }
    }

    private void delet(int pos) {
        Image img = new Image("/img/t.jpg");
        switch (pos) {
            case 1:
                campo4.setFill(new ImagePattern(img));
                break;
            case 2:
                campo3.setFill(new ImagePattern(img));
                break;
            case 3:
                campo2.setFill(new ImagePattern(img));
                break;
            case 4:
                campo1.setFill(new ImagePattern(img));
                break;
            default:
                System.err.println("eerror bro lo siento");
        }
    }

    private void tiendas(int id, int cantidad) {
        switch(id){
            case 1:
                tienda1.setText(String.valueOf(cantidad));
                break;
            case 2:
                tienda2.setText(String.valueOf(cantidad));
                break;
            case 3:
                tienda3.setText(String.valueOf(cantidad));
                break;
            case 4:
                tienda4.setText(String.valueOf(cantidad));
                break;
            case 5:
                tienda5.setText(String.valueOf(cantidad));
                break;
            default:
                System.out.println("error bro");
                break;
        }
    }

}
