package socket;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import util.Terminal;

public class Cliente extends Thread {

    String ip;
    int porta;
    Socket socket;
    Terminal terminal;
    PrintStream saidaCliente = null;

    public Cliente(String ip, int porta, Terminal terminal) {
        this.ip = ip;
        this.porta = porta;
        this.terminal = terminal;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ip, porta);
            terminal.print("Conectado ao servidor " + ip + " com sucesso!", new Color(80, 250, 123));
            clientStart();

        } catch (Exception ex) {
            terminal.print("| ERRO |>> erro ao conectar ao servidor", new Color(255, 85, 85));
        }
    }

    private void clientStart() {

        while (true) {
            InputStream ex;
            try {
                saidaCliente = new PrintStream(socket.getOutputStream());
                ex = socket.getInputStream();
                Scanner lerDoSocketServidor = new Scanner(ex);
                while (lerDoSocketServidor.hasNextLine()) {
                    terminal.print("| GET |<< " + lerDoSocketServidor.nextLine(), new Color(139, 233, 253));
                }

            } catch (IOException | NullPointerException e) {
                break;
            }
        }

    }

    
    public void send(String msg, Color color) {
        try {
            saidaCliente = new PrintStream(socket.getOutputStream());
            if (saidaCliente != null) {
                saidaCliente.println(msg);
                terminal.print("| SEND |>> " + msg, color);
            }
        } catch (IOException | NullPointerException ex) {
            terminal.print("| ERRO |>> Não foi possivel enviar a mensagem ", new Color(255, 85, 85));
        }
    }

}
