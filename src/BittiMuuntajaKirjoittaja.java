import java.util.*;

/**
 * ottaa sisaan merkkejä, muuntaa ne stringiin joka kirjoitetaan tiedostoon.
 * handlaa vain vakiopituisia merkkejä. todo vaihteleva bittien määrä per merkki
 */
public class BittiMuuntajaKirjoittaja {
    byte tavunOsa;
    byte[] maski;
    int bittejaJaljellaTavussa;
    int bittejaMerkissa;
    int isoinMerkki;
    String jono;
    public BittiMuuntajaKirjoittaja()
    {
        this(15);
    }
    public BittiMuuntajaKirjoittaja(int bittejaMerkissa)
    {
        jono = "";
        tavunOsa = 0;
        bittejaJaljellaTavussa = 8;
        this.bittejaMerkissa = bittejaMerkissa;
        isoinMerkki = (int)Math.pow(2, bittejaMerkissa);
        maski = new byte[8];
        for (int i = 0; i < 8; i++)
        {
            maski[i] = 0;
            for (int a = 0; a <= i; a++)
            {
                maski[i] |= 1 << (a);
            }
        }
    }
    public void lisaa(int merkki)
    {
        if (merkki > isoinMerkki)
        {
            return;
//            throw new Exception("Liian iso merkki bittikirjoittajaan");
        }
        tavunOsa |= (byte)(merkki >> (bittejaMerkissa-bittejaJaljellaTavussa)) & maski[bittejaJaljellaTavussa-1];
        jono = jono + (char)tavunOsa;
        int ylijaamaBitteja = bittejaMerkissa-bittejaJaljellaTavussa;
        while (ylijaamaBitteja >= 8)
        {
            tavunOsa = (byte)(merkki >> (ylijaamaBitteja-8));
            ylijaamaBitteja -= 8;
            jono = jono + (char)tavunOsa;
        }
        tavunOsa = (byte)(merkki << (8-ylijaamaBitteja));
        bittejaJaljellaTavussa = 8-ylijaamaBitteja;
    }
    public String bititStringina()
    {
        String tulos = "";
        int seuraavaVali = bittejaMerkissa;
        for (int i = 0; i < jono.length(); i++)
        {
            for (int a = 0; a < 8; a++)
            {
                if (((byte)jono.charAt(i) & ((byte)1 << (7-a))) != 0)
                {
                    tulos = tulos + "1";
                }
                else
                {
                    tulos = tulos + "0";
                }
                seuraavaVali--;
                if (seuraavaVali == 0)
                {
                    tulos = tulos + " ";
                    seuraavaVali = bittejaMerkissa;
                }
            }
        }
        return tulos;
    }
    public String toString()
    {
        if (bittejaJaljellaTavussa == 8)
        {
            return jono;
        }
        return jono + (char)tavunOsa;
    }
}
