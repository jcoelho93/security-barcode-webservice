/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Camilo
 */
public class AESEncryptor implements EncryptionInterface{

    private static final String algorithm = "AES";
    
    private SecretKey key;
    
    public byte[] encrypt(String data)
    {
        Cipher cipher = null;
        
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        } catch (NoSuchPaddingException ex) {
            return null;
        }
        
        try {
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
        } catch (InvalidKeyException ex) {
            return null;
        }
        
        byte[] cipherText;
        
        try {
            cipherText = cipher.doFinal(data.getBytes());
        } catch (IllegalBlockSizeException ex) {
            return null;
        } catch (BadPaddingException ex) {
            return null;
        }
        
        return cipherText;
        
    }
    
    public String decrypt(byte[] data)
    {
        
        Cipher cipher = null;
        
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        } catch (NoSuchPaddingException ex) {
            return null;
        }
        
        try {
            cipher.init(Cipher.DECRYPT_MODE, this.key);
        } catch (InvalidKeyException ex) {
            return null;
        }
        
        byte[] plainText;
        try {
            plainText = cipher.doFinal(data);
        } catch (IllegalBlockSizeException ex) {
            return null;
        } catch (BadPaddingException ex) {
            return null;
        }
        
        return new String(plainText);
        
    }
    
    public String generateKeys()
    {
        KeyGenerator keyGen = null;
        
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
        } catch (NoSuchAlgorithmException ex) {
            
        }
        
        SecretKey secretKey = keyGen.generateKey();
        
        this.key = secretKey;
        
        return AESEncryptor.keyToString(secretKey);
        
    }
    
    public String getAlgorithm()
    {
        return AESEncryptor.algorithm;
    }
    
    public SecretKey getKey()
    {
        return this.key;
    }
    
    public static String keyToString(SecretKey secretKey)
    {
        
        char[] hex;
        hex = Hex.encodeHex(secretKey.getEncoded());
        return String.valueOf(hex);
        
    }
    
}
