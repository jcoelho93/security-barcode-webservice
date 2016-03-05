/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptionhashingalgorithms;

import java.util.Arrays;

/**
 *
 * @author Camilo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String data = "Nome: José Camilo Fernandes Coelho\n"
                + "Conta n: 0001000200030004\n"
                + "Montante: 132.00€";
        
        Encryptor encryptor = new Encryptor(data);
        
        encryptor.setAlgorithm(new RSAEncryptor());
        
        long startTime = 0;
        long encryptTime = 0;
        long decryptTime = 0;

        startTime = System.currentTimeMillis();
        encryptor.encrypt();
        encryptTime = encryptTime + System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        encryptor.decrypt();
        decryptTime = decryptTime + System.currentTimeMillis() - startTime;
        
        
        System.out.println("Algoritmo " + encryptor.getAlgorithm().getClass().getSimpleName() + ":");
        System.out.println("Encriptação: " + (double)(encryptTime)*0.001 + "s");
        System.out.println("Decriptação:" + (double)(decryptTime)*0.001 + "s");
        
    }
    
}
