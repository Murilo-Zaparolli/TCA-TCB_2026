package estudo;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class est1 {

    private static List<PrintWriter> clientes = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado na porta " + porta);

            while (true) {
                // Aguarda a conexão de um cliente
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + clienteSocket.getInetAddress());

                // Cria uma thread para lidar com este cliente individualmente
                new Thread(new TrataCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TrataCliente implements Runnable {
    private Socket socket;

    public TrataCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            saida.println("Conexão estabelecida com sucesso!");

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                System.out.println("Mensagem recebida: " + mensagem);
                // Responde de volta para o cliente (eco ou lógica de negócio)
                saida.println("Servidor recebeu: " + mensagem);
            }
        } catch (IOException e) {
            System.out.println("Conexão encerrada pelo cliente.");
        }
    }
}

