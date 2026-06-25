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
public class BankKYCRequest extends BaseInput{

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
     * @return the IFSCCode
     */
    public String getIFSCCode() {
        return IFSCCode;
    }

    /**
     * @param IFSCCode the IFSCCode to set
     */
    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the bankKYCURL
     */
    public String getBankKYCURL() {
        return bankKYCURL;
    }

    /**
     * @param bankKYCURL the bankKYCURL to set
     */
    public void setBankKYCURL(String bankKYCURL) {
        this.bankKYCURL = bankKYCURL;
    }
    private String transactionID;
    private String IFSCCode;
    private String accountNumber;
    private String bankName;
    private UserInfo userInfo;
    private String bankKYCURL;
}
