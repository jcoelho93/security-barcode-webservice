/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptionhashingalgorithms;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Camilo
 */
public class AESEncryptor implements EncryptionInterface {
    
    private static final String ALGORITHM = "AES";
    
    private SecretKeySpec encryption_key;
    
    public AESEncryptor(String encryption_key)
    {
        
        this.encryption_key = new SecretKeySpec(encryption_key.getBytes(),ALGORITHM);
        
    }
    
    public byte[] encrypt(String data)
    {
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.encryption_key);
            return cipher.doFinal(data.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    public String decrypt(byte[] data)
    {
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.encryption_key);
            return new String(cipher.doFinal(data));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
        
    }
    
    public void generateKey()
    {
        
    }
    
}
