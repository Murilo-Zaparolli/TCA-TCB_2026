package começo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tela extends JFrame {

    ImageIcon bloco = new ImageIcon(getClass().getResource("/imagens/Card.png"));
    Dimension dBloco = new Dimension(bloco.getIconHeight(), bloco.getIconWidth());
    ImageIcon blocoCMouseEmCima = redimensionar(bloco, dBloco);

    JLabel objeto = new JLabel(bloco);

    boolean mousePressionado = false;
    boolean mouseEmCima = false;

    Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();

    int largura = (int) tamanhoTela.getWidth();
    int altura = (int) tamanhoTela.getHeight();
    
    int x = 0;
    int y = 0;

    public void tela() {

        new Mover().start();

        objeto.setBounds(x, y, bloco.getIconWidth(), bloco.getIconHeight());

        objeto.addMouseListener(evento_mover);
        this.addKeyListener(evento_reset);

        this.add(objeto);
        this.setSize(largura, altura);
        this.setLayout(null);
        this.setTitle("Nova aba");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    static ImageIcon redimensionar(ImageIcon bloco, Dimension dBloco) {

        System.out.println(dBloco.width + " " + dBloco.height);
        Image img = bloco.getImage().getScaledInstance(279, 482, DO_NOTHING_ON_CLOSE);
        return new ImageIcon(img);

    }

    private MouseListener evento_mover = new MouseListener() {
        @Override 
        public void mouseClicked(MouseEvent e) {
            objeto.setVisible(false);
            repaint();
        }
        @Override
        public void mousePressed(MouseEvent e) {
            // objeto.setIcon(bloco2);
            mousePressionado = true;
            objeto.setIcon(bloco);
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            // objeto.setIcon(bloco);
            mousePressionado = false;
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            objeto.setIcon(blocoCMouseEmCima);
            objeto.setSize(279, 482);
        }
        @Override
        public void mouseExited(MouseEvent e) {
            objeto.setIcon(bloco);
            objeto.setSize(268, 463);
        }
    };

    private KeyListener evento_reset = new KeyListener() {
        @Override 
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_K) {
                objeto.setVisible(true);
                repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_T) {
                remove(objeto);
            }

            if (e.getKeyCode() == KeyEvent.VK_R) {
                add(objeto);
            }
        }
        @Override 
        public void keyReleased(KeyEvent e){
        }
        @Override 
        public void keyTyped(KeyEvent e){
        }
    };

    public class Mover extends Thread {

        @Override
        public void run() {

            while (true) {
                try {sleep(10);} catch (Exception e) {}
                if (mousePressionado && objeto.isVisible()) {
                    Point ponto = getMousePosition();

                    if (ponto != null) {
                        objeto.setBounds(
                            ponto.x - (bloco.getIconWidth() / 2), 
                            ponto.y - (bloco.getIconHeight() / 2), 
                            bloco.getIconWidth(), 
                            bloco.getIconHeight()
                        );
                    }
                }
            }
        }
    }
    public class MouseEmCima extends Thread {

        @Override 
        public void run() {

            while (true) {

                try{sleep(10);}catch(Exception e){}

                if (mouseEmCima && objeto.isVisible()) {
                    
                }
                
            }

        }
    }
}





