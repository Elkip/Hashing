/*
 * Generate a Random Access File with fixed size random char[] data
 *
 *  See  https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 *
 */
package hw0207_hashing;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 *
 * shaynes, for COSC 471/571 WINTER 2019
 *
 */
public class GenerateRandomData {

   static final String  ALPHABET = "0A";  // "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
   static final int     A_LENGTH = ALPHABET.length();
   static final int     D_LENGTH = 3;
   static int           rseed = 2019;
   static Random        rnd = new Random(rseed);
   static final int     RECS_IN_FILE = 20;
    
   public static String randomString (int len) {
      StringBuilder sb = new StringBuilder(len);
      for (int i = 0; i < len; i++)
         sb.append (ALPHABET.charAt(rnd.nextInt(A_LENGTH)));
      return sb.toString();
   }
   public static void main(String[] args) throws EOFException, IOException {
      RandomAccessFile fp;
      fp = new RandomAccessFile("data1.dat", "rw");
     
      // write data to file
       for (int i = 0; i < RECS_IN_FILE; i++) {
         String rs = randomString(D_LENGTH);       
         fp.writeChars(rs);
      }
     
      // checking file contents
      System.out.println("Data generated -- checking file contents");
      System.out.println("file length: " + fp.length());
      fp.seek(0);
      char[] cArr = new char[D_LENGTH];
      try {
         while (true) {
            for (int j = 0; j < cArr.length; j++)
               cArr[j] = fp.readChar();
        
            String str = new String(cArr);      
            System.out.println("just read: " + str);
         }
      }
      catch (EOFException ex1) {
         System.out.println("Reached EOF!");
         return;
      }  
     
   }
  
}