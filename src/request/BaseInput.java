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
     * @return the licenceFilePath
     */
    public String getLicenceFilePath() {
        return licenceFilePath;
    }

    /**
     * @param licenceFilePath the licenceFilePath to set
     */
    public void setLicenceFilePath(String licenceFilePath) {
        this.licenceFilePath = licenceFilePath;
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

    private String licenceFilePath;
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
