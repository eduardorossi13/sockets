package sockets;

import java.io.InputStream;
import java.util.Scanner;

class RecebedorDeMensagemDoServidor implements Runnable{

	private InputStream servidor;
        private String pergunta;
        private String host;
        
        
        Cliente cli = new Cliente();
        
                   
	public RecebedorDeMensagemDoServidor(InputStream servidor) {
		this.servidor = servidor;
	}
        
	public void run() {
		try(Scanner s = new Scanner(this.servidor)){
			while (s.hasNextLine()) {
                                    cli.resposta_cliente = s.nextLine(); // Faz o Recebimento
                                    String[] dados = cli.resposta_cliente.split(" ");
                                    System.out.println(dados[1]);
                                    //System.out.println("Pergunta " + cli.pergunta);
                                    //System.out.println("Resposta Cliente " + dados[1].trim());
                                    //System.out.println("Resposta " + cli.resposta);
                                    //System.out.println(dados[1].trim().equals(cli.resposta));
                                    
                                    if(dados[1].trim().equals(cli.resposta)){
                                        System.out.println("ACERTOU MISERAVI");
                                        System.out.println("HOST: " + dados[0]);
                                        cli.resposta = "null";
                                        }
                                    }
                                }
			}
}