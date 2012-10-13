/**
 * avaa pakatun merkkijonon.
 */
public class Avaaja {
    DynaaminenTaulukko codeTable;
    public Avaaja()
    {
        //alustetaan code tableen yhden merkin pituiset koodit
        codeTable = new DynaaminenTaulukko();
        for (int i = 0; i < 256; i++)
        {
            codeTable.lisaa(""+(char)i);
        }
    }
    /**
     * Avaa pakatun merkkijonon.
     * @param merkit pakattu merkkijono
     * @return avattu merkkijono
     */
    public String avaa(String merkit)
    {
        byte[] bytet = new byte[merkit.length()];
        for (int i = 0; i < bytet.length; i++)
        {
            bytet[i] = (byte)(int)merkit.charAt(i);
        }
        return avaa(bytet);
    }
    /**
     * Avaa pakatun merkkijonon.
     * @param merkit pakattu merkkijono
     * @return avattu merkkijono
     */
    public String avaa(byte[] merkit)
    {
        String tulos = "";
        BittiMuuntajaLukija lukija = new BittiMuuntajaLukija();
        lukija.lueMerkkiJono(merkit);
        int koodi = lukija.seuraavaMerkki();
        tulos = tulos + codeTable.hae(koodi);
        char yksiMerkki = (char)koodi;
        while (lukija.onSeuraavaMerkki())
        {
            int uusiKoodi = lukija.seuraavaMerkki();
            String osaString;
            if (codeTable.koko() <= uusiKoodi)
            {
                osaString = codeTable.hae(koodi)+yksiMerkki;
            }
            else
            {
                osaString = codeTable.hae(uusiKoodi);
            }
            tulos = tulos + osaString;
            yksiMerkki = osaString.charAt(0);
            codeTable.lisaa(codeTable.hae(koodi)+yksiMerkki);
            koodi = uusiKoodi;
        }
        return tulos;
    }
}
