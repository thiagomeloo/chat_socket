
package socket;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import util.Terminal;


public class ClienteInServer extends Thread {
      private Socket entrada; 
     private Terminal terminal;
    PrintStream saidaCliente = null;
    
    public ClienteInServer(Socket entrada, Terminal terminal) {
        this.entrada = entrada;
        this.terminal=terminal;  
    }

    @Override
    public void run() {
        while (true) {
            InputStream ex ;
            try {
                
                ex = entrada.getInputStream();
                Scanner lerDoSocket = new Scanner(ex);
                while (lerDoSocket.hasNextLine()) {
                    terminal.print("| GET |: [ " + entrada.getInetAddress().getHostAddress() + " ] << " + lerDoSocket.nextLine(), Color.ORANGE);
                }

            } catch (IOException ex1) {
                break;
            }
        }
    }
}
