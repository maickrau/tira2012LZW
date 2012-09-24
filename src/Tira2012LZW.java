import java.util.Scanner;
import java.io.*;

public class Tira2012LZW {
    public static void main(String[] args) {
        boolean annaInfoa = false;
        if (args.length == 3)
        {
            try
            {
                File fileSisaan = new File(args[1]);
                File fileUlos = new File(args[2]);
                FileInputStream fileRead = new FileInputStream(fileSisaan);
                String stringSisaan = "";
                int tavujaJaljella = fileRead.available();
                while (tavujaJaljella > 0)
                {
                    for (int i = 0; i < tavujaJaljella; i++)
                    {
                        stringSisaan = stringSisaan + (char)fileRead.read();
                    }
                    tavujaJaljella = fileRead.available();
                }
                String stringUlos = "";
                if (args[0].compareTo("p") == 0)
                {
                    Pakkaaja pakkaaja = new Pakkaaja();
                    stringUlos = pakkaaja.pakkaa(stringSisaan);
                }
                else if (args[0].compareTo("a") == 0)
                {
                    Avaaja avaaja = new Avaaja();
                    stringUlos = avaaja.avaa(stringSisaan);
                }
                else
                {
                    annaInfoa = true;
                }
                FileOutputStream fileWrite = new FileOutputStream(fileUlos);
                for (int i = 0; i < stringUlos.length(); i++)
                {
                    fileWrite.write(stringUlos.charAt(i));
                }
            }
            catch (Exception e)
            {
                System.out.println("Virhe:");
                System.out.println(e.toString());
            }

        }
        else
        {
            annaInfoa = true;
        }
        if (annaInfoa)
        {
            System.out.println("Käyttö:");
            System.out.println("Pakattaessa: java Tira2012LZW p tiedostoSisaan tiedostoUlos");
            System.out.println("Avattaessa: java Tira2012LZW a tiedostoSisaan tiedostoUlos");
        }
    }
}
