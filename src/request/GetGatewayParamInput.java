/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import com.emudhra.esign.eSign;
import com.emudhra.esign.eSign.AuthMode;
import com.emudhra.esign.eSign.eSignAPIVersion;
import java.util.ArrayList;

/**
 *
 * @author 20476
 */
public class GetGatewayParamInput extends BaseInput {

    private ArrayList<SignInput> inputs;
    private String tempFolderPath;
    private String signerID;
    private String redirectURL;
    private String responseURL;
    private String transactionID;
    private String eSignType;
    private String authMode;

    /**
     * @return the inputs
     */
    public ArrayList<SignInput> getInputs() {
        return inputs;
    }

    /**
     * @param inputs the inputs to set
     */
    public void setInputs(ArrayList<SignInput> inputs) {
        this.inputs = inputs;
    }

    /**
     * @return the signerID
     */
    public String getSignerID() {
        return signerID;
    }

    /**
     * @param signerID the signerID to set
     */
    public void setSignerID(String signerID) {
        this.signerID = signerID;
    }

    /**
     * @return the redirectURL
     */
    public String getRedirectURL() {
        return redirectURL;
    }

    /**
     * @param redirectURL the redirectURL to set
     */
    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    /**
     * @return the responseURL
     */
    public String getResponseURL() {
        return responseURL;
    }

    /**
     * @param responseURL the responseURL to set
     */
    public void setResponseURL(String responseURL) {
        this.responseURL = responseURL;
    }

    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return the tempFolderPath
     */
    public String getTempFolderPath() {
        return tempFolderPath;
    }

    /**
     * @param tempFolderPath the tempFolderPath to set
     */
    public void setTempFolderPath(String tempFolderPath) {
        this.tempFolderPath = tempFolderPath;
    }

    public eSignAPIVersion geteSignType() {
        eSignAPIVersion apiVersion = eSignType.equalsIgnoreCase("V2")? eSignAPIVersion.V2 : eSignAPIVersion.V3;
        return apiVersion;
    }

    public void seteSignType(String eSignType) {
        this.eSignType = eSignType;
    }

    public AuthMode getAuthMode() {
        AuthMode mode =eSign.AuthMode.OTP;
        if(authMode.equalsIgnoreCase("OTP")){
             mode =AuthMode.OTP;
        } else  if(authMode.equalsIgnoreCase("IRIS")){
             mode =AuthMode.IRIS;
        } else  if(authMode.equalsIgnoreCase("FingerPrint")){
             mode =AuthMode.FingerPrint;
        } else if(authMode.equalsIgnoreCase("FaceRecognition")){
             mode =AuthMode.FaceRecognition;
        }
        return mode;
    }

    public void setAuthMode(String authMode) {
        this.authMode = authMode;
    }
    
    
}
