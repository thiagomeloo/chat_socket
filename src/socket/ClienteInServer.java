
package socket;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
import util.Terminal;


public class ClienteInServer extends Thread {
      private Socket entrada; 
     private Terminal terminal;
    
    public ClienteInServer(Socket entrada, Terminal terminal) {
        this.entrada = entrada;
        this.terminal=terminal;  
    }

    @Override
    public void run() {
        while (true) {            
            try {
              InputStream ex = entrada.getInputStream();
              Scanner lerDoSocket = new Scanner(ex);
              while(lerDoSocket.hasNextLine()){
                  System.out.println("vai bixiga 2 "+lerDoSocket.nextLine());
                  terminal.print("| GET |: [ " + entrada.getInetAddress().getHostAddress() + " ] << " + lerDoSocket.nextLine(), Color.MAGENTA);
              }
            } catch (IOException ex1) {
                
            }
            
            
        }
    } 
}
