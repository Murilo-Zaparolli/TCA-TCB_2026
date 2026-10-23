package servidor;

import java.io.*;
import java.net.*;

public class Server {
    private int porta = 10101;

    void iniSever() {

        try (ServerSocket sSocket = new ServerSocket(this.porta)) {

            System.out.println("Servidor inicializado com sucesso.");

            for (int i = 0; i < 2; i++) {

                Socket cSocket = sSocket.accept();

                
                
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
