package ProyectoGrafos202201947;

import java.awt.Color;

/**
 * @author Pablo Rodriguez
 */
public class Vertice {
    
    int name;
    int posx, posy;
    Color color;

    public Vertice(int name, int posx, int posy, Color color) {
        this.name = name;
        this.posx = posx;
        this.posy = posy;
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
}
