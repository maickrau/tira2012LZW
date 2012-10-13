/**
 * Hajautustaulukko, avaimena String, arvona int.
 */
public class StringIntHajautusTaulukko {
    /**
     * Vakio hajautusfunktiossa
     */
    final int m = 1559;
    /**
     * Numeroarvo joka annetaan stringille
     */
    int[] intArvot;
    String[] stringArvot;
    /**
     * Monta arvoa taulukossa on tällä hetkellä
     */
    int arvoja;
    /**
     * Miten suuri (arvojen määrä)/(taulukon koko) on suurimmillaan ennen kuin taulukon kokoa kasvatetaan
     */
    double arvojaEnnenKasvatusta;
    public StringIntHajautusTaulukko(int koko, double arvojaEnnenKasvatusta)
    {
        if (arvojaEnnenKasvatusta > 0.95)
        {
            arvojaEnnenKasvatusta = 0.95;
        }
        if (arvojaEnnenKasvatusta < 0.05)
        {
            arvojaEnnenKasvatusta = 0.05;
        }
        if (koko < 64)
        {
            koko = 64;
        }
        intArvot = new int[koko];
        stringArvot = new String[koko];
        arvoja = 0;
        this.arvojaEnnenKasvatusta = arvojaEnnenKasvatusta;
    }
    public StringIntHajautusTaulukko(int koko)
    {
        this(koko, 0.75);
    }
    public StringIntHajautusTaulukko(double arvojaEnnenKasvatusta)
    {
        this(512, arvojaEnnenKasvatusta);
    }
    public StringIntHajautusTaulukko()
    {
        this(512, 0.75);
    }
    /**
     * Laskee stringille indeksin. Eri yrityksen tulee tuottaa eri indeksi.
     * @param taa string jolle lasketaan
     * @param yritys monennetta kertaa yritetään
     * @return 
     */
    private int stringinHash(String taa, int yritys)
    {
        long a = 7;
        for (int i = 0; i < taa.length(); i++)
        {
            a *= m;
            a += taa.charAt(i);
        }
        a += yritys;
        int tulos = (int)(a % intArvot.length);
        if (tulos < 0)
        {
            tulos += intArvot.length;
        }
        return tulos;
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
     * Sisältääkö etsityn stringin.
     * @param taa string jota etsitään
     * @return 
     */
    public boolean sisaltaa(String taa)
    {
        int yritys = 0;
        int kohde;
        do
        {
            kohde = stringinHash(taa, yritys);
            yritys++;
            if (stringArvot[kohde] == null)
            {
                //ei löydy
                return false;
            }
        } while (taa.compareTo(stringArvot[kohde]) != 0);
        return true;
    }
    /**
     * Palauttaa etsityn stringin arvon.
     * @param taa string jota etsitään
     * @return stringin indeksi, tai -1 jos ei löydy.
     */
    public int etsi(String taa)
    {
        int yritys = 0;
        int kohde;
        do
        {
            kohde = stringinHash(taa, yritys);
            yritys++;
            if (stringArvot[kohde] == null)
            {
                //ei löydy
                return -1;
            }
        } while (taa.compareTo(stringArvot[kohde]) != 0);
        return intArvot[kohde];
    }
    /**
     * Lisää stringin tietyllä arvolla
     * @param taa string jota lisätään
     * @param numero arvo joka annetaan stringille
     */
    public void lisaa(String taa, int numero)
    {
        arvoja++;
        int yritys = 0;
        int kohde;
        do
        {
            kohde = stringinHash(taa, yritys);
            yritys++;
        } while (stringArvot[kohde] != null);
        stringArvot[kohde] = taa;
        intArvot[kohde] = numero;
        if ((double)arvoja/intArvot.length > arvojaEnnenKasvatusta)
        {
            kasvataTaulukkoa();
        }
    }
    /**
     * Kasvattaa taulukon koon kaksinkertaiseksi ja laskee arvoille uudet indeksit.
     */
    private void kasvataTaulukkoa()
    {
        String[] vanhaS = stringArvot;
        int[] vanhaI = intArvot;
        intArvot = new int[vanhaS.length*2];
        stringArvot = new String[vanhaS.length*2];
        arvoja = 0;
        for (int i = 0; i < vanhaS.length; i++)
        {
            if (vanhaS[i] != null)
            {
                lisaa(vanhaS[i], vanhaI[i]);
            }
        }
    }
}
