/**
 * lukee pakatut tavut ja palauttaa merkkejä yksi kerrallaan.
 */
public class BittiMuuntajaLukija {
    /**
     * Merkkijono josta luetaan merkkejä
     */
    byte[] jono;
    /**
     * Monennetta tavua luetaan
     */
    int tavuSijainti;
    /**
     * Monennetta bittiä nykyisen tavun sisällä luetaan
     */
    int bittiSijainti;
    /**
     * Monta bittiä yhdessä merkissä on
     */
    int bittejaMerkissa;
    /**
     * Isoin merkki joka nykyisellä bittimäärällä voi olla
     */
    int isoinMerkki;
    /**
     * Bittimaski tavun viimeisistä biteistä, n:ssä indeksissä ensimmäiset n ovat 0, loput 1
     */
    byte[] loppuMaski;
    /**
     * Bittimaski tavun ensimmäisistä biteistä, n:ssä indeksissä ensimmäiset n ovat 1, loput 0
     */
    byte[] alkuMaski;
    public BittiMuuntajaLukija()
    {
        this(9);
    }
    public BittiMuuntajaLukija(int bittejaMerkissa)
    {
        jono = null;
        this.bittejaMerkissa = bittejaMerkissa;
        isoinMerkki = (int)Math.pow(2, bittejaMerkissa)-1;
        tavuSijainti = 0;
        bittiSijainti = 0;
        loppuMaski = new byte[8];
        alkuMaski = new byte[8];
        for (int i = 0; i < 8; i++)
        {
            loppuMaski[i] = 0;
            alkuMaski[i] = 0;
            for (int a = 0; a <= 7-i; a++)
            {
                loppuMaski[i] |= 1 << a;
            }
            for (int a = 0; a <= i; a++)
            {
                alkuMaski[i] |= 1 << (7-a);
            }
        }
    }
    /**
     * Kysyy onko jonossa vielä merkkejä joita ei olla luettu.
     * @return 
     */
    public boolean onSeuraavaMerkki()
    {
        if (jono == null)
        {
            return false;
        }
        if (tavuSijainti*8+bittiSijainti+bittejaMerkissa > jono.length*8)
        {
            return false;
        }
        return true;
    }
    /**
     * Palauttaa seuraavan merkin jonosta.
     * @return merkki
     */
    public int seuraavaMerkki()
    {
        if (jono == null)
        {
            return -1;
        }
        int tulos;
        int lisays = (jono[tavuSijainti])&loppuMaski[bittiSijainti];
        if (lisays < 0)
        {
            lisays = 256+lisays;
        }
        tulos = lisays;
        tavuSijainti++;
        int bittejaJaljella = bittejaMerkissa-(8-bittiSijainti);
        while (bittejaJaljella >= 8)
        {
            tulos <<= 8;
            lisays = jono[tavuSijainti];
            if (lisays < 0)
            {
                lisays += 256;
            }
            tulos += lisays;
            bittejaJaljella -= 8;
            tavuSijainti++;
        }
        if (bittejaJaljella > 0)
        {
            tulos <<= bittejaJaljella;
            lisays = (int)((jono[tavuSijainti])&alkuMaski[bittejaJaljella-1]);
            if (lisays < 0)
            {
                lisays = 256+lisays;
            }
            lisays >>= (8-bittejaJaljella);
            tulos += lisays;
        }
        bittiSijainti = bittejaJaljella;
        if (tulos == isoinMerkki)
        {
            kasvataMerkinKokoa();
            return seuraavaMerkki();
        }
        return tulos;
    }
    /**
     * Ottaa vastaan merkkijonon josta merkit luetaan.
     * @param sisaan merkkijono
     */
    public void lueMerkkiJono(String sisaan)
    {
        jono = new byte[sisaan.length()];
        for (int i = 0; i < sisaan.length(); i++)
        {
            jono[i] = (byte)(int)sisaan.charAt(i);
        }
        tavuSijainti = 0;
        bittiSijainti = 0;
    }
    /**
     * Kasvattaa merkin pituutta yhdellä bitillä
     */
    private void kasvataMerkinKokoa()
    {
        bittejaMerkissa++;
        isoinMerkki = (int)Math.pow(2, bittejaMerkissa)-1;
    }
    /**
     * Ottaa vastaan merkkijonon josta merkit luetaan.
     * @param sisaan merkkijono
     */
    public void lueMerkkiJono(byte[] sisaan)
    {
        jono = sisaan;
        tavuSijainti = 0;
        bittiSijainti = 0;
    }
}
