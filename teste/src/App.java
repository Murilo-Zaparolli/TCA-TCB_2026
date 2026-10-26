import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import estudo.est1;

public class App {

    private static List<PrintWriter> clientes = new CopyOnWriteArrayList<>();

    public static void addCliente(PrintWriter saida) {
        clientes.add(saida);
    }

    public static void printChat(String msg) {
        for (PrintWriter cliente : clientes) {
            cliente.println(msg);
        }
    }

    public static void rmCliente(PrintWriter saida) {
        clientes.remove(saida);
    }

    public static void main(String[] args) throws Exception {

        int porta = 10101;

        try (ServerSocket sSocket = new ServerSocket(porta)) {

            while (true) {

                Socket cSocket = sSocket.accept();
                InetAddress id = sSocket.getInetAddress();
                System.out.println("Nova conexão: " + id);

                new Thread(new Clientela(cSocket, id)).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}


class Clientela implements Runnable {

    private Socket socket;
    private PrintWriter saida;
    private InetAddress id;

    public Clientela(Socket socket, InetAddress id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {

        Scanner SC = new Scanner(System.in);

        try(
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {

            this.saida = out;

            App.addCliente(saida);

            saida.println("Conexão estabelecida com sucesso!!");
            App.printChat("Novo usuário na parada!");

            String msg;

            while (true) {

                msg = entrada.readLine();

                if (msg.equals("SAIR")) {
                    break;
                }

                App.printChat(msg);
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (saida != null) {
                App.rmCliente(saida);
                App.printChat("O usuário de id " + id + ", se desconectou!!");
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SC.close();

    }

}
