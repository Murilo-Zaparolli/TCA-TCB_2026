package começo;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;

public class Tela extends JFrame {

    ImageIcon bloco = new ImageIcon(getClass().getResource("/imagens/images.jpeg"));
    ImageIcon bloco2 = new ImageIcon(getClass().getResource("/imagens/blocks.jpeg"));

    JLabel objeto = new JLabel(bloco);

    boolean mousePressionado = false;

    Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();

    int largura = (int) tamanhoTela.getWidth();
    int altura = (int) tamanhoTela.getHeight();
    
    int x = (int)(Math.random()*(largura - 250));
    int y = (int)(Math.random()*(altura - 150));

    public void tela() {

        new Mover().start();

        objeto.setBounds(x, y, bloco.getIconWidth(), bloco.getIconHeight());

        objeto.addMouseListener(evento_mover);

        this.add(objeto);
        this.setSize(largura, altura);
        this.setLayout(null);
        this.setTitle("Nova aba");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private MouseListener evento_mover = new MouseListener() {
        @Override 
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            // objeto.setIcon(bloco2);
            mousePressionado = true;
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            // objeto.setIcon(bloco);
            mousePressionado = false;
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    };

    public class Mover extends Thread {

        @Override
        public void run() {

            while (true) {
                try {sleep(10);} catch (Exception e) {}
                if (mousePressionado) {
                    Point ponto = getMousePosition();
                    objeto.setBounds(
                        ponto.x - (bloco.getIconWidth() / 2), 
                        ponto.y - (bloco.getIconHeight() / 2), 
                        bloco.getIconWidth(), 
                        bloco.getIconHeight());
                }
            }

        }
    
    }
}



