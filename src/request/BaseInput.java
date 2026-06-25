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
public class BaseInput {

    /**
     * @return the aspId
     */
    public String getAspId() {
        return aspId;
    }

    /**
     * @param aspId the aspId to set
     */
    public void setAspId(String aspId) {
        this.aspId = aspId;
    }

    /**
     * @return the V3 (PAN) eSign gateway URL
     */
    public String geteSignURL() {
        return eSignURL;
    }

    /**
     * @param eSignURL the V3 (PAN) eSign gateway URL to set
     */
    public void seteSignURL(String eSignURL) {
        this.eSignURL = eSignURL;
    }

    /**
     * @return the V2 (Aadhaar) eSign gateway URL
     */
    public String geteSignURLV2() {
        return eSignURLV2;
    }

    /**
     * @param eSignURLV2 the V2 (Aadhaar) eSign gateway URL to set
     */
    public void seteSignURLV2(String eSignURLV2) {
        this.eSignURLV2 = eSignURLV2;
    }

    /**
     * @return the pfxPath
     */
    public String getPfxPath() {
        return pfxPath;
    }

    /**
     * @param pfxPath the pfxPath to set
     */
    public void setPfxPath(String pfxPath) {
        this.pfxPath = pfxPath;
    }

    /**
     * @return the pfxPassword
     */
    public String getPfxPassword() {
        return pfxPassword;
    }

    /**
     * @param pfxPassword the pfxPassword to set
     */
    public void setPfxPassword(String pfxPassword) {
        this.pfxPassword = pfxPassword;
    }

    /**
     * @return the pfxAlias
     */
    public String getPfxAlias() {
        return pfxAlias;
    }

    /**
     * @param pfxAlias the pfxAlias to set
     */
    public void setPfxAlias(String pfxAlias) {
        this.pfxAlias = pfxAlias;
    }

    /**
     * @return the proxyReq
     */
    public boolean isProxyReq() {
        return proxyReq;
    }

    /**
     * @param proxyReq the proxyReq to set
     */
    public void setProxyReq(boolean proxyReq) {
        this.proxyReq = proxyReq;
    }

    /**
     * @return the proxyIp
     */
    public String getProxyIp() {
        return proxyIp;
    }

    /**
     * @param proxyIp the proxyIp to set
     */
    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    /**
     * @return the proxyPort
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * @param proxyPort the proxyPort to set
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * @return the sessionTimeout
     */
    public int getSessionTimeout() {
        return sessionTimeout;
    }

    /**
     * @param sessionTimeout the sessionTimeout to set
     */
    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    /**
     * @return the proxyUserID
     */
    public String getProxyUserID() {
        return proxyUserID;
    }

    /**
     * @param proxyUserID the proxyUserID to set
     */
    public void setProxyUserID(String proxyUserID) {
        this.proxyUserID = proxyUserID;
    }

    /**
     * @return the proxyUserPassword
     */
    public String getProxyUserPassword() {
        return proxyUserPassword;
    }

    /**
     * @param proxyUserPassword the proxyUserPassword to set
     */
    public void setProxyUserPassword(String proxyUserPassword) {
        this.proxyUserPassword = proxyUserPassword;
    }

    private String aspId;
    private String eSignURL;
    private String eSignURLV2;
    private String pfxPath;
    private String pfxPassword;
    private String pfxAlias;
    private boolean proxyReq;
    private String proxyIp;
    private int proxyPort;
    private int sessionTimeout;
    private String proxyUserID;
    private String proxyUserPassword;
}
