package sockets;

import java.io.InputStream;
import java.util.Scanner;

class RecebedorDeMensagemDoServidor implements Runnable{

	private InputStream servidor;
        
        Cliente cli = new Cliente();
        
                   
	public RecebedorDeMensagemDoServidor(InputStream servidor) {
		this.servidor = servidor;
	}
        
	public void run() {
		try(Scanner s = new Scanner(this.servidor)){
			while (s.hasNextLine()) {
                                    cli.pergunta = s.nextLine(); // Faz o Recebimento
                                    cli.resposta_cliente = s.nextLine(); // Faz o Recebimento
                                    System.out.println("Pergunta " + cli.pergunta);
                                    System.out.println("Resposta Cliente " + cli.resposta_cliente);
                                    System.out.println("Resposta " + cli.resposta);
                                    System.out.println(cli.resposta_cliente.equals(cli.resposta)); 
                                    }
                                }
			}
}