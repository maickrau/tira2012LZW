import java.util.*;

/**
 * avaa pakatun merkkijonon.
 */
public class Avaaja {
    Map<Integer, String> codeTable; //todo oma rakenne
//    ArrayList<String> codeTable;
    public Avaaja()
    {
        //alustetaan code tableen yhden merkin pituiset koodit
        codeTable = new TreeMap<Integer, String>();
//        codeTable = new ArrayList<String>();
        for (int i = 0; i < 256; i++)
        {
            codeTable.put(i, ""+(char)i);
//            codeTable.add(""+(char)i);
        }
    }
    public String avaa(String merkit)
    {
        byte[] bytet = new byte[merkit.length()];
        for (int i = 0; i < bytet.length; i++)
        {
            bytet[i] = (byte)(int)merkit.charAt(i);
        }
        return avaa(bytet);
    }
    public String avaa(byte[] merkit)
    {
        String tulos = "";
        BittiMuuntajaLukija lukija = new BittiMuuntajaLukija();
        lukija.lueMerkkiJono(merkit);
        int koodi = lukija.seuraavaMerkki();
        tulos = tulos + codeTable.get(koodi);
        char yksiMerkki = (char)koodi;
        while (lukija.onSeuraavaMerkki())
        {
            int uusiKoodi = lukija.seuraavaMerkki();
            String osaString;
            if (!codeTable.containsKey(uusiKoodi))
//            if (codeTable.size() <= uusiKoodi)
            {
                osaString = codeTable.get(koodi)+yksiMerkki;
            }
            else
            {
                osaString = codeTable.get(uusiKoodi);
            }
            tulos = tulos + osaString;
            yksiMerkki = osaString.charAt(0);
            codeTable.put(codeTable.size(), codeTable.get(koodi)+yksiMerkki);
//            codeTable.add((codeTable.get(koodi)+yksiMerkki));
            koodi = uusiKoodi;
        }
        return tulos;
    }
}
