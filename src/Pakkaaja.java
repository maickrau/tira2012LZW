import java.util.*;

/**
 * pakkaa merkkijonon.
 */
public class Pakkaaja {
    Map<String, Integer> codeTable; //todo oma rakenne
    public Pakkaaja()
    {
        //alustetaan code tableen yhden merkin pituiset koodit
        codeTable = new HashMap<String, Integer>();
        for (int i = 0; i < 256; i++)
        {
            codeTable.put(""+(char)(i), i);
        }
    }
    public String pakkaa(String sisaan) throws Exception
    {
        byte[] bytet = new byte[sisaan.length()];
        for (int i = 0; i < bytet.length; i++)
        {
            bytet[i] = (byte)(int)sisaan.charAt(i);
        }
        return pakkaa(bytet);
    }
    public String pakkaa(byte[] sisaan) throws Exception
    {
        BittiMuuntajaKirjoittaja tulos = new BittiMuuntajaKirjoittaja();
        int sijaintiStringissa = 0;
        String osaString = "";
        while (sijaintiStringissa < sisaan.length)
        {
            char yksiMerkki = (char)(sisaan[sijaintiStringissa]);
            if (yksiMerkki >= 256)
            {
                yksiMerkki = (char)(yksiMerkki % 256);
            }
            if (codeTable.containsKey(osaString+yksiMerkki))
            {
                osaString = osaString+yksiMerkki;
            }
            else
            {
                tulos.lisaa(codeTable.get(osaString));
                codeTable.put(osaString+yksiMerkki, codeTable.size());
                osaString = ""+yksiMerkki;
            }
            sijaintiStringissa++;
        }
        tulos.lisaa(codeTable.get(osaString));
        return tulos.toString();
    }
}
