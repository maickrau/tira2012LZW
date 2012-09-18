import java.util.*;

/**
 * avaa pakatun merkkijonon.
 */
public class Avaaja {
    Map<Integer, String> codeTable; //todo oma rakenne
    public Avaaja()
    {
        //alustetaan code tableen yhden merkin pituiset koodit
        codeTable = new HashMap<Integer, String>();
        for (int i = 0; i < 256; i++)
        {
            codeTable.put(i, ""+(char)i);
        }
    }
    public String avaa(String merkit)
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
            koodi = uusiKoodi;
        }
        return tulos;
    }
}
