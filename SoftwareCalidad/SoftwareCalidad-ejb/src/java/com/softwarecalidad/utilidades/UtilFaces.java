package com.softwarecalidad.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import org.primefaces.context.RequestContext;

/**
 *
 * @author Sebastian Vega
 */
public class UtilFaces {

    private static UtilFaces facesUtil;

    private UtilFaces() {
    }

    public static UtilFaces getFacesUtil() {
        if (facesUtil == null) {
            facesUtil = new UtilFaces();
        }
        return facesUtil;
    }

    public HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getSession(true);
    }

    public void setAttributeSession(String key, Object value) {
        HttpSession session = getSession();
        session.setAttribute(key, value);
    }

    public void redirect(String string) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect(string);
    }

    public void addMessage(FacesMessage.Severity SEVERITY, String string, String toString) {
        FacesMessage message = new FacesMessage(SEVERITY, string, toString);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getContextPath() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getContextPath();
    }

    public HttpServletRequest getHttpServletRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

/*    public void execute(String string) {
        RequestContext.getCurrentInstance().execute(string);
    }

    public void openDialog(String string, Map<String, Object> options) {
        RequestContext.getCurrentInstance().openDialog(string, options, null);
    }

    public void closeDialog(Object object) {
        RequestContext.getCurrentInstance().closeDialog(object);
    }

    public void update(String string) {
        RequestContext.getCurrentInstance().update(string);
    }*/

    public void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
    }

    public void downloadFile(String fileName, byte[] dataFile) throws IOException {

        File outputFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(dataFile);
        fos.close();

        // Obtains ServletContext
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        // Gets MIME type of the file
        String mimeType = context.getMimeType(fileName);
        if (mimeType == null) { // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        ec.setResponseContentType(mimeType);
        ec.setResponseContentLength((int) outputFile.length());
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        OutputStream output = ec.getResponseOutputStream();
        FileInputStream inStream = new FileInputStream(outputFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        inStream.close();
        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
        outputFile.delete();
    }

    public byte[] parseInputStreamToArrayByte(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        return byteArrayOutputStream.toByteArray();
    }

}
