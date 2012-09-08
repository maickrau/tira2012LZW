import java.util.*;

/**
 * lukee pakatun stringin ja palauttaa merkkejä yksi kerrallaan. todo
 */
public class BittiMuuntajaLukija {
    ArrayList<Integer> jono;
    public BittiMuuntajaLukija()
    {
        jono = new ArrayList<Integer>();
    }
    public boolean onSeuraavaMerkki()
    {
        return (!jono.isEmpty());
    }
    public int seuraavaMerkki()
    {
        int tulos = jono.get(0);
        int a = jono.remove(0);
        return tulos;
    }
    public void lueMerkkiJono(String sisaan)
    {
        String[] numerot = sisaan.split(" ");
        for (int i = 0; i < numerot.length; i++)
        {
            int tamaLuku = 0;
            for (int a = 0; a < numerot[i].length(); a++)
            {
                tamaLuku *= 10;
                tamaLuku += numerot[i].charAt(a)-'0';
            }
            if (numerot[i].length() > 0)
            {
                jono.add(tamaLuku);
            }
        }
    }
}
