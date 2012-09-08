import java.util.*;

/**
 * ottaa sisaan merkkejä, muuntaa ne stringiin joka kirjoitetaan tiedostoon. todo.
 */
public class BittiMuuntajaKirjoittaja {
    ArrayList<Integer> jono;
    public BittiMuuntajaKirjoittaja()
    {
        jono = new ArrayList<Integer>();
    }
    public void lisaa(int merkki)
    {
        jono.add(merkki);
    }
    public String toString()
    {
        String tulos = "";
        for (int i = 0; i < jono.size(); i++)
        {
            tulos = tulos + " " + jono.get(i);
        }
        return tulos;
    }
}
