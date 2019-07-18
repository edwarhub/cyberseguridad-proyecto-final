/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.servlet;



import com.tncity.properties.Propiedad;
import com.tncity.util.EncryptionUtil;
import com.tncity.util.MimeTypeUtil;
import com.tncity.cache.GeneralCache;
import com.tncity.informex.InformexFacade;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletGeneralDownload extends HttpServlet {

    public final static String TYPE_FILE = "FILE";
    public final static String TYPE_INFO = "INFO";

    /**
     * Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * Destroys the servlet.
     */
    public void destroy() {
    }

    private void sendMsgHtml(HttpServletResponse response, String msg) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try {
            String type = TYPE_FILE;
            if (request.getParameter("type") != null && !request.getParameter("type").toLowerCase().trim().equals("null")) {
                type = request.getParameter("type");
            }

            if (type.equals(TYPE_FILE)) {
                downloadFile(request, response);
            }
            if (type.equals(TYPE_INFO)) {
                downloadInfo(request, response);
            }

        } catch (Exception e) {
            System.out.println("ServletDownload: Error ->" + e.toString());
            sendMsgHtml(response, "<b style='color:red'>Hubo un error descargando el archivo</b>: Por favor avisar al administrador del sistema");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    private void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Recuperando Archivo
        String path = EncryptionUtil.decryptAES(request.getParameter("f"), Propiedad.getCurrentInstance().getEncryptAesKey());
        File f = new File(path);
        if (!f.exists()) {
            //System.out.println("ServletDownload: Archivo no encontrado ->" + f.getAbsolutePath());
            System.out.println("ServletGeneralDownload-> Archivo no encontrado:" + f.getAbsolutePath());
            sendMsgHtml(response, "<h2 style='color:gray'>El archivo solicitado no fue encontrado :( !</h2>");
            return;
        }

        //Nombre de Archivo
        String reName = request.getParameter("n");
        String nameF = f.getName();
        if (reName != null && !reName.trim().isEmpty()) {
            nameF = reName;
        }

        //Stream
        String mimeType = new MimeTypeUtil(f).getMimeType();
        FileInputStream inStream = new FileInputStream(f);
        OutputStream out = response.getOutputStream();

        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "attachment; filename=" + nameF);
        response.setContentLength(inStream.available());
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        out.flush();
        out.close();
        inStream.close();
    }

    private void downloadInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String key = request.getParameter("key");
        String filename = request.getParameter("filename");
        String tmpkey = request.getParameter("tmpkey");

        ByteArrayOutputStream out = InformexFacade.getOuputStream(key);
        if (out == null) {
            sendMsgHtml(response, "<h2 style='color:red'>El informe solicitado no fue encontrado :( !</h2>");
            return;
        }

        if (!GeneralCache.isTmpkeyRegister(tmpkey)) {
            sendMsgHtml(response, "<h2 style='color:red'>Token (tmpkey) no válido  :( !</h2>");
            return;
        }
        GeneralCache.delTmpKey(tmpkey);

        String mimeType = new MimeTypeUtil(new File(filename)).getMimeType();
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentLength(out.size());
        response.getOutputStream().write(out.toByteArray());

        response.getOutputStream().flush();
        response.getOutputStream().close();
        out.close();
    }
}
