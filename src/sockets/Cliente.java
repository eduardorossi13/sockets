package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente {

	private String host;
	private int porta;
        public String pergunta;
        public static String resposta;  
        public static String resposta_cliente; 
      


	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

    Cliente() {
        
    }

	public void executa() throws UnknownHostException, IOException {
		try(Socket cliente = new Socket(this.host, this.porta); 
				Scanner teclado = new Scanner(System.in); 
				PrintStream saida = new PrintStream(cliente.getOutputStream())) {
			System.out.println("Conectado com Sucesso!");
	
			RecebedorDeMensagemDoServidor r = new RecebedorDeMensagemDoServidor(cliente.getInputStream());
			new Thread(r).start();
                        
                        //JOptionPane.showMessageDialog(null, "Bem Vindo");
                        //int op = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma opção: \n"
                        //        + "1 = Host \n"
                        //        + "2 = Cliente \n"));
                        
                        System.out.println("Bem Vindo !");
                        System.out.println("Escolha uma Opção:"
                                + "\n 1 = HOST"
                                + "\n 2 = CLIENTE \n\n");
                        
                        System.out.print("Opção: ");
                        int op = teclado.nextInt();
                        
			while (teclado.hasNextLine()) {                            
                            if(op == 1){
                                 //pergunta = JOptionPane.showInputDialog("Pergunta: ");
                                 System.out.print("Pergunta: ");
                                 pergunta = teclado.next();
                                 //int resposta = Integer.parseInt(JOptionPane.showInputDialog("Resposta: "));
                                 System.out.print("Resposta: ");
                                 resposta = teclado.next();
				 saida.println(pergunta); //Faz o Envio                                 
                                 System.out.println("Enviado \n");
                                 
                                 
                            } else if(op == 2){
                                 //int res = Integer.parseInt(JOptionPane.showInputDialog(null, "Resposta: "));
                                 System.out.println("Resposta: ");
                                 resposta = teclado.next();                                                                  
                                 saida.println(resposta); //Faz o Envio
                            }
                        }
		 
	}
        }
}
