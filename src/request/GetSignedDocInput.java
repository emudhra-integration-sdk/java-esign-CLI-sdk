/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

/**
 *
 * @author 20476
 */
public class GetSignedDocInput extends BaseInput {

    private String responseXML;
    private String preSignedTempFile;

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
}
