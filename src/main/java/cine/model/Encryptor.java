package cine.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* Codigo obtenido de:
https://gist.github.com/Da9el00/577c7bb58942e99df2771515c1e00c2d */ 
public class Encryptor {

    public String encryptar(String valor) {

        //MessageDigest works with MD2, MD5, SHA-1, SHA-224, SHA-256
        //SHA-384 and SHA-512
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(valor.getBytes());
            BigInteger bigInt = new BigInteger(1,messageDigest);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
  
    }

    public boolean iguales(String valor, String valorEncryptado) {
        return valorEncryptado.equals(this.encryptar(valor));
    } 
}