package javaapplication1;

// Fig. 23.9: UnsynchronizedBuffer.java

import java.util.logging.Level;
import java.util.logging.Logger;

// UnsynchronizedBuffer representa um �nico inteiro compartilhado.

public class UnsynchronizedBuffer implements Buffer
{
   private final int[] buffer = {-1, -1, -1, -1, -1}; // compartilhado pelas threads Produtor e Consumidor
   private int celulasOcupadas = 0;
   private int posicaoEscrita  = 0;
   private int posicaoLeitura  = 0;   
   private int producao  = 0; // 0 = Vazio / 1 = Cheio

   // coloca o valor no buffer
   public synchronized void set( int value )
   {
      while(producao == 1){
          try {
              //System.out.println("\nCHEIO, aguardando Consumidor...\t");              
              wait();
          } catch (InterruptedException ex) {
              Logger.getLogger(UnsynchronizedBuffer.class.getName()).log(Level.SEVERE, null, ex);
          }
      }      
      
      buffer [posicaoEscrita] = value;
      
      posicaoEscrita = (posicaoEscrita + 1) % buffer.length;
      
      celulasOcupadas++;
      
      System.out.printf( "Producer writes\t%2d", value );
      //System.out.println(" PRODUCAO == " + producao);
            
      //System.out.println("\nCelulas Ocupadas: " + celulasOcupadas);
      //System.out.println("Tamanho Buffer: " + buffer.length);
      if(celulasOcupadas == buffer.length){
          producao = 1;
          notifyAll();
      }      
            
   } // fim do método set

   // retorna valor do buffer
   public synchronized int get()
   {
      while(producao == 0){
          try {
              //System.out.println("VAZIO, aguardando Produtor...\t");              
              wait();
          } catch (InterruptedException ex) {
              Logger.getLogger(UnsynchronizedBuffer.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
            
      int readValue = buffer[posicaoLeitura];
      
      posicaoLeitura = (posicaoLeitura + 1) % buffer.length;
      
      celulasOcupadas--;
      
      System.out.printf( "Consumer reads\t%2d", readValue );
      //System.out.println(" PRODUCAO2 == " + producao);      
      
      //System.out.println("\nCelulas Ocupadas: " + celulasOcupadas);
      //System.out.println("Tamanho Buffer: " + buffer.length);
      
      if(celulasOcupadas == 0){
        producao = 0;
        notifyAll();  
      }
      
      return readValue;
      
   } // fim do método get
} // fim da classe UnsynchronizedBuffer


/**************************************************************************
 * (C) Copyright 1992-2005 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
