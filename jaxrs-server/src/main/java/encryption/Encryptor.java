/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Camilo
 */
public class Encryptor {
    
    
    private EncryptionInterface encryptor;
    
    private String data;
    private byte[] encrypted;
    
    public Encryptor()
    {
        this.encryptor = null;
    }
    
    public Encryptor(EncryptionInterface encryptor)
    {
        
        this.encryptor = encryptor;
        
    }
    
    public void setEncryptorClass(EncryptionInterface encryptor)
    {
        
        this.encryptor = encryptor;
        
    }
    
    public EncryptionInterface getEncryptor()
    {
        
        return this.encryptor;
        
    }
    
    /**
     * 
     * Encrypt data
     * 
     * @param data
     * @return bytes
     */
    public byte[] encrypt(String data)
    {
        this.data = data;
        this.encrypted = this.encryptor.encrypt(data);
        return this.encrypted;
    }
    
    /**
     * 
     * Decrypt data
     * 
     * @param data
     * @return String
     */
    public String decrypt(byte[] data)
    {
        
        this.encrypted = data;
        this.data = this.encryptor.decrypt(data);
        return this.data;
        
    }
    
    /**
     * 
     * Hashing function for SHA-256 algorithm
     * 
     * @param data
     * @return String
     * @throws NoSuchAlgorithmException 
     */
    public String hash(String data) throws NoSuchAlgorithmException
    {
        
        MessageDigest md = MessageDigest.getInstance("SHA256");
        byte[] hash = md.digest(data.getBytes());
        
        return Hex.encodeHexString(hash);
        
    }
    
}
