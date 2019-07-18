/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.rest.util;

import org.apache.commons.codec.binary.Base64;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 *
 * @author edwar
 */
public class CipherUtil {

   private static PrivateKey getPrivate(File filePrivateKey) throws Exception {
        byte[] keyBytes = Files.readAllBytes(filePrivateKey.toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private static PublicKey getPublic(File filePublicKey) throws Exception {
        byte[] keyBytes = Files.readAllBytes(filePublicKey.toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static String encryptToBase64(String content,File filePrivateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPrivate(filePrivateKey));
        return Base64.encodeBase64String(cipher.doFinal(content.getBytes("UTF-8")));
    }

    public static String decrypt(String contentBase64,File filePublicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, getPublic(filePublicKey));
        byte[] cont = Base64.decodeBase64(contentBase64);
        byte[] res = cipher.doFinal(cont);
        return new String(res);
    }

    public static void main(String[] args) throws Exception {
        File privateK=new File("./privateKey.key");
        File publicK=new File("./publicKey.key");
        
        String cryptB64 = CipherUtil.encryptToBase64("HELLO1!!!",privateK);
        String decrypt = CipherUtil.decrypt(cryptB64,publicK);

        System.out.println("Crypt:" + cryptB64);
        System.out.println("Decrypt:" + decrypt);

    }

}
