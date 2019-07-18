/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.general;

import com.tncity.util.CipherGeneratorKeys;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "restTestControl")
@RequestScoped
public class RestTestControl {

    FacesUtil facesUtil = FacesUtil.currentInstance();

    public void generarLlaves() {

        CipherGeneratorKeys gk;
        try {
            gk = new CipherGeneratorKeys(1024);
            gk.createKeys();
            gk.writeToFile("/opt/ciberseguridad/tncity/publicKey.key", gk.getPublicKey().getEncoded());
            gk.writeToFile("/opt/ciberseguridad/tncity/privateKey.key", gk.getPrivateKey().getEncoded());
            facesUtil.msgOk("","Llaves generadas!");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
            facesUtil.msgError("", e.toString());
        } catch (IOException e) {
            facesUtil.msgError("", e.toString());
            System.err.println(e.getMessage());
        }

    }

}
