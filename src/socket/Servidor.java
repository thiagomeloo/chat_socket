package socket;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import util.Terminal;

public class Servidor extends Thread {

    Terminal terminal;
    int porta;
    ServerSocket servidor;
    PrintStream saidaServidor;
    Socket o_cliente;

    public Servidor(int porta, Terminal terminal) {
        this.terminal = terminal;
        this.porta = porta;
    }

    public void startServer() {
        try {
            servidor = new ServerSocket(porta);
            terminal.print("Servidor iniciado na porta: " + porta, Color.GREEN);
            while (true) {

                o_cliente = servidor.accept();
                terminal.print("Cliente: " + o_cliente.getInetAddress().getHostAddress() + " conectado", Color.GREEN);

                saidaServidor = new PrintStream(o_cliente.getOutputStream());

                ClienteInServer clienteinserver = new ClienteInServer(o_cliente, terminal);
                clienteinserver.start();

            }

        } catch (IOException ex) {

            System.out.println("Erro na criação do servidor");

        }
    }

    @Override
    public void run() {
        startServer();
    }

    public void send(String msg, Color color) {
        try {
            saidaServidor = new PrintStream(o_cliente.getOutputStream());
            saidaServidor.println(msg);
            terminal.print("| SEND |>> " + msg, color);
        } catch (IOException | NullPointerException ex) {
            terminal.print("| ERRO |>> ", new Color(255, 85, 85));
        }
    }
}
