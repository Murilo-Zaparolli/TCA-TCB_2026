package estudo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

public class est2 extends JFrame {
    private JTextField txtMensagem;
    private JTextArea areaHistorico;
    private PrintWriter saida;
    private BufferedReader entrada;

    public est2() {
        setTitle("Cliente Swing - Socket");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaHistorico = new JTextArea();
        areaHistorico.setEditable(false);
        add(new JScrollPane(areaHistorico), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());
        txtMensagem = new JTextField();
        JButton btnEnviar = new JButton("Enviar");

        painelInferior.add(txtMensagem, BorderLayout.CENTER);
        painelInferior.add(btnEnviar, BorderLayout.EAST);
        add(painelInferior, BorderLayout.SOUTH);

        btnEnviar.addActionListener(this::enviarMensagem);
        txtMensagem.addActionListener(this::enviarMensagem);

        conectarAoServidor();
    }

    private void conectarAoServidor() {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 12345);
                saida = new PrintWriter(socket.getOutputStream(), true);
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                areaHistorico.append("Conectado ao servidor!\n");

                String resposta;
                while ((resposta = entrada.readLine()) != null) {
                    final String msg = resposta;
                    // Atualiza a interface gráfica de forma segura
                    SwingUtilities.invokeLater(() -> areaHistorico.append("Servidor: " + msg + "\n"));
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> areaHistorico.append("Erro de conexão com o servidor.\n"));
            }
        }).start();
    }

    private void enviarMensagem(ActionEvent e) {
        String texto = txtMensagem.getText();
        if (!texto.isEmpty() && saida != null) {
            saida.println(texto);
            areaHistorico.append("Eu: " + texto + "\n");
            txtMensagem.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new est2().setVisible(true));
    }
}

