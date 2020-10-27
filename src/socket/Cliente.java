package socket;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import util.Terminal;

public class Cliente extends Thread {
    
    Socket socket;
    Terminal terminal;
    PrintStream saidaCliente = null;

    public Cliente(Socket socket, Terminal terminal) {
        this.socket = socket;
        this.terminal = terminal;
    }

    @Override
    public void run() {
                while (true) {
            InputStream ex ;
            try {
                saidaCliente = new PrintStream(socket.getOutputStream());
                ex = socket.getInputStream();
                if (ex.read() == -1) {
                    socket.close();
                    break;
                    
                } else {
                    Scanner lerDoSocket = new Scanner(ex);
                    while (lerDoSocket.hasNextLine()) {
                        terminal.print("| GET |: [ "+
                                socket.getInetAddress().getHostAddress()+
                                " ] << " + lerDoSocket.nextLine(), new Color(255, 85, 85) );
                    }
                    saidaCliente = null;
                }

            } catch (IOException ex1) {
                break;
            }
        }
    }
    
    
    
    
}
