/*
 * Mitchell Henschel
 * hw0207
 * COSC 471 Haynes
 * Febuary 14th, 2019
 */
package hw0207_hashing;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hw0207_Hashing {

    public static void main(String[] args) throws EOFException, IOException {
        RandomAccessFile output;
        output = new RandomAccessFile("output.dat","rw");

        RandomAccessFile input;
        input = new RandomAccessFile("data0.dat","r");
        
        int hash_row = (int) input.length() / 6;
        System.out.println("Number of hashrows: " + hash_row);
        
        String[] table = new String[hash_row];
        
        int key;
        char[] entry = new char[3];

        while (true) {
            try{
                entry[0] = input.readChar();
                entry[1] = input.readChar();
                entry[2] = input.readChar();

                String value = String.valueOf(entry);
                System.out.println("Value read: " + value);

                key = value.hashCode() % hash_row;
               
                boolean collison=true;
                while(collison) {
                    
                    //if key already exists
                    if (table[key] == value) {
                        System.out.println("Duplicate found, skipping...");
                        collison=false;
                    }
                    
                    //key doesnt exist
                    if (table[key] == null) {
                        table[key]=value;
                        System.out.println("Hashcode: " + key);
                        collison=false;
                    }

                    key = (key + 1) % hash_row;
                }
            }
            catch (EOFException e) {
                System.out.println("Reached End of File");
                break;
            }
        }    
        output.close();
        input.close();
    }
}
