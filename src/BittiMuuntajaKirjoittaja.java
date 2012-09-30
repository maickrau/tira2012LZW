/**
 * ottaa sisaan merkkejä, muuntaa ne stringiin joka kirjoitetaan tiedostoon.
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
