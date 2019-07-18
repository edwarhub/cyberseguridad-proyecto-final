/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.rest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import test.rest.util.CipherUtil;
import test.rest.util.UrlContent;

/**
 *
 * @author edwar
 */
public class TestRest {

    String urlEndpoint;
    File privateKey;

    public TestRest(String urlEndpoint, File privateKey) {
        this.urlEndpoint = urlEndpoint;
        this.privateKey = privateKey;
    }

    public String executeQuery(String ghql, StringBuilder urlSend) throws Exception {

        String ghqlCrypt = CipherUtil.encryptToBase64(ghql, privateKey);

        String url = urlEndpoint + encodeValue(ghqlCrypt);
        String res = new UrlContent(url).getContent();
        urlSend.append(url);
        return res;
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
