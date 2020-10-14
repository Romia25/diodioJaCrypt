/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptojava.classes;

import cryptojava.interfaces.ICryptoConfig;
import cryptojava.interfaces.IKeyGeneration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author hp
 */
public class KeyGeneration implements IKeyGeneration{

    @Override
    public SecretKey keyGen() {//doit etre private
        
        try {
            KeyGenerator kg = KeyGenerator.getInstance(ICryptoConfig.algo);
            kg.init(ICryptoConfig.keySize);
            return kg.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public boolean saveKey(String filePath, SecretKey key) {//doit etre private
        
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(key);
            oos.close();
            fos.close();
            return true;
                    } catch (IOException ex) {
            Logger.getLogger(KeyGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    

    @Override
    public boolean keyGeneration(String filePath) {
        
        SecretKey k = keyGen();
        return saveKey(filePath, k);
    }

    @Override
    public SecretKey recupKey(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            SecretKey key = (SecretKey) ois.readObject();
            return key;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KeyGeneration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(KeyGeneration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    
    }
    
}
