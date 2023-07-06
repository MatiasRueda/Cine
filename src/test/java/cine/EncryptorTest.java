package cine;

import static org.junit.Assert.*;
import cine.model.Encryptor;
import org.junit.Test;

public class EncryptorTest {
    private Encryptor encryptor = new Encryptor();
    private final String palabraUNO = "hola";
    private final String palabraDOS = "h1241414ola";
    private final String palabraTRES = "Matias";
    
    @Test
    public void encryptarUNOTest() {
        assertNotEquals(palabraUNO, this.encryptor.encryptar(palabraUNO));
    }

    @Test
    public void encryptarDOSTest() {
        assertNotEquals(palabraDOS, this.encryptor.encryptar(palabraDOS));
    }

    @Test
    public void encryptarTRESTest() {
        assertNotEquals(palabraTRES, this.encryptor.encryptar(palabraTRES));
    }

    @Test
    public void igualesUNOTest() {
        String palabraUNOEncryptada = this.encryptor.encryptar(palabraUNO);
        assertTrue(this.encryptor.iguales(palabraUNO, palabraUNOEncryptada));
    }

    @Test
    public void igualesDOSTest() {
        String palabraDOSEncryptada = this.encryptor.encryptar(palabraDOS);
        assertTrue(this.encryptor.iguales(palabraDOS, palabraDOSEncryptada));
    }

    @Test
    public void igualesTRESTest() {
        String palabraTRESEncryptada = this.encryptor.encryptar(palabraTRES);
        assertTrue(this.encryptor.iguales(palabraTRES, palabraTRESEncryptada));
    }
}
