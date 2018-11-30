package pl.edu.agh.ics.to.models;

import java.io.FileNotFoundException;
import java .util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args) {
    }

        Scanner x;


        public void inputFile(){
            try{
                x = new Scanner(new File("dzialyFile.txt)"));
            }
          catch(Exception e){
                System.out.println("Could not find file");
          }
        }


        public void readFile(){
           while(x.hasNext()) {
               String a = x.next();
               int b = Integer.parseInt(a);
               String c = x.next();
               TypDzialu typdzialu1 =  new TypDzialu( b,  a);
           }

        }


        public void closeFile(){
         x.close();
        }


}