package pl.edu.agh.ics.to.models;

import java.util.Scanner;
import java.io.File;

public class FileUtils {

    Scanner x;


    public void inputzPliku(){
        try{
            x = new Scanner(new File("dzialyFile.txt)"));
        }
        catch(Exception e){
            System.out.println("Could not find file");
        }
    }


    public void czytajDzialy(){
        while(x.hasNext()) {
            String a = x.next();
            int b = Integer.parseInt(a);
            String c = x.next();
            int d = Integer.parseInt(a);
            Polaczenie polaczenie1 =  new Polaczenie( b,  d);
        }

    }


    public void closeFile(){
        x.close();
    }

}


