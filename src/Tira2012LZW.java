import java.util.*;

public class Tira2012LZW {
    public static void main(String[] args) {
        //testausta
        String pakattavaksi = "aaaaaaabcdefdefaa";
        System.out.println(pakattavaksi);
        Pakkaaja pakkaaja = new Pakkaaja();
        String pakattu = pakkaaja.pakkaa(pakattavaksi);
        System.out.println(pakattu);
        Avaaja avaaja = new Avaaja();
        String avattu = avaaja.avaa(pakattu);
        System.out.println(avattu);
    }
}
