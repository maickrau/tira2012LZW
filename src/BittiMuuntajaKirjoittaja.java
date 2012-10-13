/**
 * ottaa sisaan merkkejä, muuntaa ne stringiin joka kirjoitetaan tiedostoon.
 */
public class BittiMuuntajaKirjoittaja {
    /**
     * Nykyinen tavu johon lisätään bittejä
     */
    byte tavunOsa;
    /**
     * Bittimaski tavun viimeisistä biteistä, n:llä indeksillä viimeiset (n+1) bittiä ovat 1 ja muut 0
     */
    byte[] maski;
    /**
     * Monta bittiä johon ei ole kirjoitettu on jäljellä nykyisessä tavussa
     */
    int bittejaJaljellaTavussa;
    /**
     * Monta bittiä yhdessä merkissä on
     */
    int bittejaMerkissa;
    /**
     * Isoin merkki joka nykyisellä bittimäärällä voi olla
     */
    int isoinMerkki;
    /**
     * Pakattut merkit
     */
    String jono;
    public BittiMuuntajaKirjoittaja()
    {
        this(9);
    }
    public BittiMuuntajaKirjoittaja(int bittejaMerkissa)
    {
        jono = "";
        tavunOsa = 0;
        bittejaJaljellaTavussa = 8;
        this.bittejaMerkissa = bittejaMerkissa;
        isoinMerkki = (int)Math.pow(2, bittejaMerkissa)-1;
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
    /**
     * Yrittää lisätä merkin kirjoitettavaan jonoon. Jos merkki on liian iso, lisätään "kasvata koodin pituutta" -merkki ennen sitä.
     * @param merkki lisättävä merkki
     */
    public void lisaa(int merkki)
    {
        if (merkki >= isoinMerkki)
        {
            lisaaOikeasti(isoinMerkki);
            kasvataMerkinKokoa();
            lisaa(merkki);
        }
        else
        {
            lisaaOikeasti(merkki);
        }
    }
    /**
     * Lisää merkin kirjoitettavaan jonoon.
     * @param merkki lisättävä merkki
     */
    private void lisaaOikeasti(int merkki)
    {
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
    /**
     * Kasvattaa merkin kokoa yhdellä bitillä.
     */
    private void kasvataMerkinKokoa()
    {
        bittejaMerkissa++;
        isoinMerkki = (int)Math.pow(2, bittejaMerkissa)-1;
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
