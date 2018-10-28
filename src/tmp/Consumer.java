package javaapplication1;

// Fig. 23.8: Consumer.java
// oO m�todo run de Consumer itera dez vezes lendo um valor do buffer.
import java.util.Random;

public class Consumer implements Runnable
{ 
   private static Random generator = new Random();
   private Buffer sharedLocation; // refer�ncia a objeto compartilhado

   // construtor
   public Consumer( Buffer shared )
   {
      sharedLocation = shared;
   } // fim do construtor Consumer 

   // l� o valor do sharedLocation quatro vezes e soma os valores
   public void run()                                           
   {
      int sum = 0;

      for ( int count = 1; count <= 10; count++ ) 
      {
         // dorme de 0 a 3 segundos, l� o valor do buffer e adiciona a soma
         try 
         {
            Thread.sleep( generator.nextInt( 3000 ) );
            sum += sharedLocation.get();
            System.out.printf( "\t\t\t%2d\n", sum );
         } // fim do try
         // se a thread adormecida � interrompida, imprime rastreamento de pilha 
         catch ( InterruptedException exception ) 
         {
            exception.printStackTrace();
         } // fim do catch
      } // fim do for

      System.out.printf( "\n%s %d.\n%s\n", 
         "Consumer read values totaling", sum, "Terminating Consumer." );
   } // fim do m�todo run
} // fim da classe Consumer 


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