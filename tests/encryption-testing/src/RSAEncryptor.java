/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptionhashingalgorithms;

import java.io.File;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import javax.crypto.Cipher;

/**
 *
 * @author Camilo
 */
public class RSAEncryptor implements EncryptionInterface {
    
    private final static String ALGORITHM = "RSA";
    
    private Key public_key;
    private Key private_key;
    
    public RSAEncryptor()
    {
        this.generateKey();
    }
    
    public byte[] encrypt(String data)
    {
        
        byte[] cipher_text = null;
        
        try{
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.public_key);
            cipher_text = cipher.doFinal(data.getBytes());
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return cipher_text;
        
    }
    
    /**
     *
     * @param data
     * @return
     */
    @Override
    public String decrypt(byte[] data)
    {
        
        byte[] decrypted_text = null;
        
        try{
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            
            cipher.init(Cipher.DECRYPT_MODE, this.private_key);
            decrypted_text = cipher.doFinal(data);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return new String(decrypted_text);
        
    }
    
    public void generateKey()
    {
        try{
            
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            
            KeyPair kp = keyGen.genKeyPair();
            this.public_key = kp.getPublic();
            this.private_key = kp.getPrivate();
    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getAlgorithm()
    {
        return RSAEncryptor.ALGORITHM;
    }
    
}
