package javaapplication1;

// Fig. 23.7: Producer.java
// O m�todo run do Producer armazena os valores de 1 a 10 no buffer.
import java.util.Random;

public class Producer implements Runnable
{
   private static Random generator = new Random();
   private Buffer sharedLocation; // refer�ncia a objeto compartilhado

   // construtor
   public Producer( Buffer shared )
   {
       sharedLocation = shared;
   } // fim do construtor Producer

   // armazena os valores de 1 a 10 em sharedLocation
   public void run()                             
   {
      int sum = 0;
      int produzido = 0;

      for ( int count = 1; count <= 10; count++ )
      {
         try // dorme de 0 a 3 segundos, ent�o coloca valor em Buffer
         {            
            Thread.sleep( generator.nextInt( 3000 ) ); // thread sleep
            produzido = generator.nextInt(50);
            sharedLocation.set( produzido ); // configura valor no buffer
            sum += produzido; // incrementa soma de valores
            System.out.printf( "\t%2d\n", sum );
         } // fim do try
         // se a thread adormecida � interrompida, imprime rastreamento de pilha 
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // fim do catch
      } // fim do for

      System.out.printf( "\n%s %d.\n%s\n", 
         "Producer write values totaling", sum, "Terminating Production." );
   } // fim do m�todo run
} // fim da classe Producer


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