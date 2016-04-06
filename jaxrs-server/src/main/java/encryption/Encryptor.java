/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

/**
 *
 * @author Camilo
 */
public class Encryptor {
    
    
    private EncryptionInterface encryptor;
    
    private String data;
    private byte[] encrypted;
    
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
    
    public byte[] encrypt(String data)
    {
        this.data = data;
        this.encrypted = this.encryptor.encrypt(data);
        return this.encrypted;
    }
    
    public String decrypt(byte[] data)
    {
        
        this.encrypted = data;
        this.data = this.encryptor.decrypt(data);
        return this.data;
        
    }
    
    public String hash(String data)
    {
        
        return data;
        
    }
    
}
