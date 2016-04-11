/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Camilo
 */
public class RSAEncryptor implements EncryptionInterface {

    private static final String ALGORITHM = "RSA";
    
    private Key privateKey;
    private Key publicKey;
    
    public byte[] encrypt(String data)
    {
        
        byte[] cipherText = null;
        
        try {
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
            
            cipherText = cipher.doFinal(data.getBytes());
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cipherText;
    }
    
    public String decrypt(byte[] data, Key private_key)
    {
        
        byte[] plainText = null;
        
        try {
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, private_key);
            
            plainText = cipher.doFinal(data);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new String(plainText);
        
    }
    
    public String generateKeys()
    {
        try {
            
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            
            KeyPair keyPair = keyGen.genKeyPair();
            
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSAEncryptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return RSAEncryptor.keyToString(this.privateKey);
        
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(Key privateKey) {
        this.privateKey = privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(Key publicKey) {
        this.publicKey = publicKey;
    }
    
    public String getAlgorithm()
    {
        return RSAEncryptor.ALGORITHM;
    }
    
    /**
     * 
     * Turns SecretKey to String for storage in DB
     * 
     * @param secretKey
     * @return 
     */
    private static String keyToString(Key key)
    {
        
        char[] hex;
        hex = Hex.encodeHex(key.getEncoded());
        return String.valueOf(hex);
        
    }
    
}
