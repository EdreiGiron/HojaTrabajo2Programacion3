/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author edrei
 */
import java.awt.Graphics;
import javax.swing.JPanel;

public class GraficarArbol extends JPanel {

    private LogicaArbolBinarioExpresiones arbol;
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 30;

    public void setArbol(LogicaArbolBinarioExpresiones arbol) {
        this.arbol = arbol;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pintar(g, getWidth() / 2, 20, arbol.raizArbol);
    }

    public void pintar(Graphics g, int x, int y, NodoArbol subArbol) {
        if (subArbol != null) {
            int EXTRA = arbol.nodosCompletos(subArbol) * ANCHO / 2;
            g.drawOval(x, y, DIAMETRO, DIAMETRO);
            g.drawString(subArbol.dato.toString(), x + 12, y + 18);
            if (subArbol.izquierdo != null) {
                g.drawLine(x, y + RADIO, x + RADIO - ANCHO - EXTRA, y + ANCHO);
            }
            if (subArbol.derecho != null) {
                g.drawLine(x + DIAMETRO, y + RADIO, x + RADIO + ANCHO + EXTRA, y + ANCHO);
            }
            pintar(g, x - ANCHO - EXTRA, y + ANCHO, subArbol.izquierdo);
            pintar(g, x + ANCHO + EXTRA, y + ANCHO, subArbol.derecho);
        }
    }
}
