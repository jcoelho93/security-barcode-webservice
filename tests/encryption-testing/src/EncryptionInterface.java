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
public interface EncryptionInterface {
    
    public byte[] encrypt(String data);

    public String decrypt(byte[] data);
    
    public void generateKey();
    
}
