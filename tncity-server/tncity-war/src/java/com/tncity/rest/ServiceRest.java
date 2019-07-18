/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.rest;

import com.tncity.config.ParamFacade;
import com.tncity.config.pojoaux.RestConfig;
import com.tncity.graphql.TncityGraphqlFacade;
import com.tncity.util.BeanUtil;
import com.tncity.util.CipherUtil;
import java.io.File;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author edwar
 */
@Path("generic")
public class ServiceRest {

    static HashMap<String, String> mapToken = new HashMap<>();

    TncityGraphqlFacade graphqlFacade = new TncityGraphqlFacade();
    ParamFacade paramFacade = BeanUtil.lookupFacadeBean(ParamFacade.class);

    @Context
    private UriInfo context;

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello!";
    }

    @GET
    @Path("graphql/{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public String graphql(@PathParam("query") String query) {

        String ghql = query;
        if (paramFacade.getParamFromCache(RestConfig.class).isSecurityIsActive()) {
            File publicKey = new File("/opt/ciberseguridad/tncity/publicKey.key");
            try {

                String decryptVal = CipherUtil.decrypt(query, publicKey);
                String arr[] = decryptVal.split("@param@");
                if (arr.length != 3) {
                    return "Falla de autenticación (TIMESTAMP)!";
                }
                ghql = arr[0];
                String timestamp = arr[1];
                String idclient = arr[2];

                if (mapToken.get(idclient) != null && mapToken.get(idclient).equals(timestamp)) {
                    return "Error token!";
                }
                mapToken.put(idclient, timestamp);

            } catch (Exception e) {
                e.printStackTrace();
                return "Falla de autenticación !";
            }
        }

        StringBuilder err = new StringBuilder();
        String res = graphqlFacade.executeQueryResultJSON(ghql, err);
        if (!err.toString().isEmpty()) {
            System.out.println("Alerta: Error en RealtimeGraphqlFacade ->" + err.toString());
            return "{\"error\":\"Graphql :: " + err.toString() + "\"}";
        }
        return res;
    }

}
