/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptionhashingalgorithms;

/**
 *
 * @author Camilo
 */
public class Encryptor {
    
    private EncryptionInterface algorithm;
    
    private String data;
    private byte[] encrypted;
    
    public Encryptor(String data)
    {
        this.data = data;
    }
    
    public void setAlgorithm(EncryptionInterface algorithm)
    {
        
        this.algorithm = algorithm;
        
    }
    
    public EncryptionInterface getAlgorithm()
    {
        return this.algorithm;
    }
    
    public byte[] encrypt()
    {
        this.encrypted = this.algorithm.encrypt(this.data);
        return this.encrypted;
    }
    
    public String decrypt()
    {
        this.data = this.algorithm.decrypt(this.encrypted);
        return this.data;
    }
    
}
