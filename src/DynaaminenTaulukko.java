/**
 * Dynaaminen String-taulukko.
 */
public class DynaaminenTaulukko {
    String[] arvot;
    /**
     * Monta arvoa taulukossa on tällä hetkellä, ei ole sama kuin arvot.length
     */
    int arvoja;
    public DynaaminenTaulukko()
    {
        this(512);
    }
    public DynaaminenTaulukko(int koko)
    {
        if (koko < 64)
        {
            koko = 64;
        }
        arvot = new String[koko];
        arvoja = 0;
    }
    /**
     * monta arvoa taulukossa on
     * @return
     */
    public int koko()
    {
        return arvoja;
    }
    /**
     * Hakee stringin tietystä indeksistä.
     * @param taa indeksi josta haetaan
     * @return string joka on indeksissä, tai null jos sitä ei ole
     */
    public String hae(int taa)
    {
        if (arvoja < taa)
        {
            //ei löydy
            return null;
        }
        return arvot[taa];
    }
    /**
     * Lisää stringin taulukkoon.
     * @param taa string joka lisätään
     */
    public void lisaa(String taa)
    {
        arvot[arvoja] = taa;
        arvoja++;
        if (arvoja == arvot.length)
        {
            kasvata();
        }
    }
    /**
     * Kasvattaa taulukon koon kaksinkertaiseksi.
     */
    private void kasvata()
    {
        String[] vanha = arvot;
        arvot = new String[vanha.length*2];
        for (int i = 0; i < arvoja; i++)
        {
            arvot[i] = vanha[i];
        }
    }
}
