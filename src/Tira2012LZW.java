import java.util.Scanner;
import java.io.*;

public class Tira2012LZW {
    public static byte[] lueTiedosto(String nimi) throws Exception
    {
        File fileSisaan = new File(nimi);
        FileInputStream fileRead = new FileInputStream(fileSisaan);
        int tavujaJaljella = fileRead.available();
        byte[] tulos = new byte[tavujaJaljella];
        int alku = 0;
        while (tavujaJaljella > 0)
        {
//            fileRead.read(tulos);
            for (int i = alku; i < alku+tavujaJaljella; i++)
            {
                tulos[i] = (byte)fileRead.read();
            }
            alku += tavujaJaljella;
            tavujaJaljella = fileRead.available();
            if (tavujaJaljella > 0)
            {
                byte[] vanhaTulos = tulos;
                tulos = new byte[vanhaTulos.length+tavujaJaljella];
                for (int i = 0; i < vanhaTulos.length; i++)
                {
                    tulos[i] = vanhaTulos[i];
                }
            }
        }
        return tulos;
    }
    public static void kirjoitaTiedosto(String nimi, String kirjoitettava) throws Exception
    {
        File fileUlos = new File(nimi);
        FileOutputStream fileWrite = new FileOutputStream(fileUlos);
        for (int i = 0; i < kirjoitettava.length(); i++)
        {
            fileWrite.write(kirjoitettava.charAt(i));
        }
    }
    public static void main(String[] args) {
        boolean annaInfoa = false;
        long aikaAlussa = System.currentTimeMillis();
        long aikaLuettua = aikaAlussa;
        long aikaEnnenKirjoitusta = aikaAlussa;
        if (args.length == 3)
        {
            try
            {
                byte[] stringSisaan = lueTiedosto(args[1]);
                aikaLuettua = System.currentTimeMillis();
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
                aikaEnnenKirjoitusta = System.currentTimeMillis();
                kirjoitaTiedosto(args[2], stringUlos);
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
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("tiedoston luku: " + (aikaLuettua - aikaAlussa));
        System.out.println("pakkaus/avaus: " + (aikaEnnenKirjoitusta - aikaLuettua));
        System.out.println("tiedoston kirjoitus: " + (aikaLopussa - aikaEnnenKirjoitusta));
    }
}
