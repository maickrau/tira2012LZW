/**
 * lukee pakatut tavut ja palauttaa merkkejä yksi kerrallaan.
 * handlaa vain vakionpituisia merkkejä, todo vaihteleva pituus
 */
//todo vaihteleva bittimäärä per merkki
public class BittiMuuntajaLukija {
    byte[] jono;
    int tavuSijainti;
    int bittiSijainti;
    int bittejaMerkissa;
    byte[] loppuMaski;
    byte[] alkuMaski;
    public BittiMuuntajaLukija()
    {
        this(16);
    }
    public BittiMuuntajaLukija(int bittejaMerkissa)
    {
        jono = null;
        this.bittejaMerkissa = bittejaMerkissa;
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
        return tulos;
    }
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
    public void lueMerkkiJono(byte[] sisaan)
    {
        jono = sisaan;
        tavuSijainti = 0;
        bittiSijainti = 0;
    }
}
