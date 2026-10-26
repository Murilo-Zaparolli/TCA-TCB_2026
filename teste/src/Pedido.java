import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Pedido {

    public static String pedido() {
        JFrame frame = new JFrame("pop-Up");

        return JOptionPane.showInputDialog(frame, "informe o nome do usuário:");
    }
}
