/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package response;

import com.emudhra.esign.ReturnDocument;
import com.emudhra.esign.eSign;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @author 20476
 */
public final class RetDoc {

    private String signedDocument;
    private String documentHash;
    private String docInfo;
    private String docURL;
    private int docId;
    private String errorMessage;
    private String errorCode;
    private int status;
    private boolean isHash;

    /**
     * @return the signedDocument
     */
    public String getSignedDocument() {
        return signedDocument;
    }

    /**
     * @param signedDocument the signedDocument to set
     */
    public void setSignedDocument(String signedDocument) {
        this.signedDocument = signedDocument;
    }

    /**
     * @return the documentHash
     */
    public String getDocumentHash() {
        return documentHash;
    }

    /**
     * @param documentHash the documentHash to set
     */
    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    /**
     * @return the docInfo
     */
    public String getDocInfo() {
        return docInfo;
    }

    /**
     * @param docInfo the docInfo to set
     */
    public void setDocInfo(String docInfo) {
        this.docInfo = docInfo;
    }

    /**
     * @return the docURL
     */
    public String getDocURL() {
        return docURL;
    }

    /**
     * @param docURL the docURL to set
     */
    public void setDocURL(String docURL) {
        this.docURL = docURL;
    }

    /**
     * @return the docId
     */
    public int getDocId() {
        return docId;
    }

    /**
     * @param docId the docId to set
     */
    public void setDocId(int docId) {
        this.docId = docId;
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
     * @return the isHash
     */
    public boolean isIsHash() {
        return isHash;
    }

    /**
     * @param isHash the isHash to set
     */
    public void setIsHash(boolean isHash) {
        this.isHash = isHash;
    }

    public RetDoc(ReturnDocument retd) throws UnsupportedEncodingException {
        this.setDocId(retd.getDocId());
        this.setDocInfo(retd.getDocInfo()==null?"":retd.getDocInfo());
        this.setDocURL(retd.getDocURL()==null?"":retd.getDocURL());
        this.setDocumentHash(retd.getDocumentHash()==null?"":retd.getDocumentHash());
        this.setErrorCode(retd.getErrorCode()==null?"":retd.getErrorCode());
        this.setErrorMessage(retd.getErrorMessage()==null?"":retd.getErrorMessage());
        this.setStatus(retd.getStatus());
        this.setSignedDocument(retd.getSignedDocument()==null?"":(URLEncoder.encode(retd.getSignedDocument(),"UTF-8")));
        this.setIsHash(retd.getInputType() == eSign.InputType.HASH);
    }

}
