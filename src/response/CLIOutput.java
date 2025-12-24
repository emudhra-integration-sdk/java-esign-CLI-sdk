/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package response;

import com.emudhra.esign.ReturnDocument;
import com.emudhra.esign.eSignServiceReturn;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.emcastle.util.encoders.Base64;

/**
 *
 * @author 20476
 */
public final class CLIOutput {

    private String transactionID;
    private String preSignedTempFile;
    private String requestXML;
    private String responseXML;
    private int status;
    private String responseCode;
    private String gatewayParameter;
    private String errorCode;
    private String errorMessage;
    private ArrayList<RetDoc> returnValues;

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
     * @return the preSignedTempFile
     */
    public String getPreSignedTempFile() {
        return preSignedTempFile;
    }

    /**
     * @param preSignedTempFile the preSignedTempFile to set
     */
    public void setPreSignedTempFile(String preSignedTempFile) {
        this.preSignedTempFile = preSignedTempFile;
    }

    /**
     * @return the requestXML
     */
    public String getRequestXML() {
        return requestXML;
    }

    /**
     * @param requestXML the requestXML to set
     */
    public void setRequestXML(String requestXML) {
        this.requestXML = requestXML;
    }

    /**
     * @return the responseXML
     */
    public String getResponseXML() {
        return responseXML;
    }

    /**
     * @param responseXML the responseXML to set
     */
    public void setResponseXML(String responseXML) {
        this.responseXML = responseXML;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the gatewayParameter
     */
    public String getGatewayParameter() {
        return gatewayParameter;
    }

    /**
     * @param gatewayParameter the gatewayParameter to set
     */
    public void setGatewayParameter(String gatewayParameter) {
        this.gatewayParameter = gatewayParameter;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the returnValues
     */
    public ArrayList<RetDoc> getReturnValues() {
        return returnValues;
    }

    /**
     * @param returnValues the returnValues to set
     */
    public void setReturnValues(ArrayList<RetDoc> returnValues) {
        this.returnValues = returnValues;
    }

    public CLIOutput(eSignServiceReturn ret, String method) throws UnsupportedEncodingException {

        if (method.equals("GETGATEWAYPARAM")) {
            this.setErrorCode(ret.getErrorCode() == null ? "" : ret.getErrorCode());
            this.setErrorMessage(ret.getErrorMessage() == null ? "" : ret.getErrorMessage());
            this.setStatus(ret.getStatus());
            this.setGatewayParameter(ret.getGatewayParameter() == null ? "" : ret.getGatewayParameter());
            this.setTransactionID(ret.getTransactionID() == null ? "" : ret.getTransactionID());
            this.setPreSignedTempFile(ret.getPreSignedTempFile() == null ? "" : ret.getPreSignedTempFile());
            this.setResponseCode(ret.getResponseCode() == null ? "" : ret.getResponseCode());
            if (ret.getRequestXML() != null) {
                this.setRequestXML(Base64.toBase64String(ret.getRequestXML().getBytes(StandardCharsets.UTF_8)));
            } else {
                this.setRequestXML("");
            }
            if (ret.getResponseXML() != null) {
                this.setResponseXML(Base64.toBase64String(ret.getResponseXML().getBytes(StandardCharsets.UTF_8)));
            } else {
                this.setResponseXML("");
            }
        }
        if (method.equals("GETTRANSACTIONSTATUS")) {
            this.setErrorCode(ret.getErrorCode() == null ? "" : ret.getErrorCode());
            this.setErrorMessage(ret.getErrorMessage() == null ? "" : ret.getErrorMessage());
            this.setStatus(ret.getStatus());
            if (ret.getRequestXML() != null) {
                this.setRequestXML(Base64.toBase64String(ret.getRequestXML().getBytes(StandardCharsets.UTF_8)));
            } else {
                this.setRequestXML("");
            }
            if (ret.getResponseXML() != null) {
                this.setResponseXML(Base64.toBase64String(ret.getResponseXML().getBytes(StandardCharsets.UTF_8)));
            } else {
                this.setResponseXML("");
            }
        }
        if (method.equals("GETSIGNEDPDF")) {
            this.setErrorCode(ret.getErrorCode() == null ? "" : ret.getErrorCode());
            this.setErrorMessage(ret.getErrorMessage() == null ? "" : ret.getErrorMessage());
            this.setStatus(ret.getStatus());
            this.setGatewayParameter(null);
            this.setTransactionID(ret.getTransactionID() == null ? "" : ret.getTransactionID());
            this.setPreSignedTempFile(ret.getPreSignedTempFile() == null ? "" : ret.getPreSignedTempFile());
            this.setResponseCode(ret.getResponseCode() == null ? "" : ret.getResponseCode());
            
            if (ret.getResponseXML() != null) {
                this.setResponseXML(Base64.toBase64String(ret.getResponseXML().getBytes(StandardCharsets.UTF_8)));
            } else {
                this.setResponseXML("");
            }
            ArrayList<RetDoc> retVals = new ArrayList<>();
            if (ret.getStatus() == 1) {
                if (ret.getReturnValues() != null) {
                    for (ReturnDocument returnValue : ret.getReturnValues()) {
                        retVals.add(new RetDoc(returnValue));
                    }
                }
                this.setReturnValues(retVals);
            }
        }
        if (method.equals("PERFORMBANKKYC")) {
            this.setErrorCode(ret.getErrorCode() == null ? "" : ret.getErrorCode());
            this.setErrorMessage(ret.getErrorMessage() == null ? "" : ret.getErrorMessage());
            this.setStatus(ret.getStatus());
            this.setGatewayParameter(null);
            this.setTransactionID(ret.getTransactionID() == null ? "" : ret.getTransactionID());
            this.setResponseCode(ret.getResponseCode() == null ? "" : ret.getResponseCode());
            if (ret.getRequestXML() != null) {
                this.setRequestXML(Base64.toBase64String(ret.getRequestXML().getBytes(StandardCharsets.UTF_8)));
            } else {
                this.setRequestXML("");
            }
            if (ret.getResponseXML() != null) {
                this.setResponseXML(Base64.toBase64String(ret.getResponseXML().getBytes(StandardCharsets.UTF_8)));
            } else {
                this.setResponseXML("");
            }
        }
    }
}
