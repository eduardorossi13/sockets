package javaapplication1;


// Fig. 23.10: SharedBufferTest.java
// Aplicativo mostra duas threads que manipulam um buffer n�o-sincronizado.
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest
{
   public static void main( String[] args )
   {
      // cria novo pool de threads com duas threads
      ExecutorService application = Executors.newFixedThreadPool( 2 );

      // cria UnsynchronizedBuffer para armazenar ints
      Buffer sharedLocation = new UnsynchronizedBuffer();

      System.out.println( "Action\t\tValue\tProduced\tConsumed" );
      System.out.println( "------\t\t-----\t--------\t--------\n" );

      // tenta iniciar as threads produtora e consumidora fornecendo acesso a cada uma
      // a sharedLocation
      try 
      {
         application.execute( new Producer( sharedLocation ) );
         application.execute( new Consumer( sharedLocation ) );
      } // fim do try
      catch ( Exception exception )
      {
         exception.printStackTrace();
      } // fim do catch

      application.shutdown(); // termina aplicativo quando as threads terminam
   } // fim do main
} // fim da classe SharedBufferTest


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