/**
 * Dynaaminen String-taulukko.
 */
public class DynaaminenTaulukko {
    String[] arvot;
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
    public int koko()
    {
        return arvoja;
    }
    public String hae(int taa)
    {
        if (arvoja < taa)
        {
            //ei löydy
            return null;
        }
        return arvot[taa];
    }
    public void lisaa(String taa)
    {
        arvot[arvoja] = taa;
        arvoja++;
        if (arvoja == arvot.length)
        {
            kasvata();
        }
    }
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
