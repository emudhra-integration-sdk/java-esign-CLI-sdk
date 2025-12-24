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
public class SignInput {

    private String appearanceType;
    private String base64doc;
    private String docInfo;
    private String docURL;
    private String signerName;
    private String reason;
    private String location;
    private String pageCoordinates;
    private boolean coSign;
    private String appearanceText;
    private boolean isHash;
    private String imageSignBase64;
    private String featureValueLeft;
    private String featureValueRight;
    private String oneLinerText;
    private ContentSearchFeature searchString;
    private boolean rightOrigin;

    /**
     * @return the base64doc
     */
    public String getBase64doc() {
        return base64doc;
    }

    /**
     * @param base64doc the base64doc to set
     */
    public void setBase64doc(String base64doc) {
        this.base64doc = base64doc;
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
     * @return the signerName
     */
    public String getSignerName() {
        return signerName;
    }

    /**
     * @param signerName the signerName to set
     */
    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the pageCoordinates
     */
    public String getPageCoordinates() {
        return pageCoordinates;
    }

    /**
     * @param pageCoordinates the pageCoordinates to set
     */
    public void setPageCoordinates(String pageCoordinates) {
        this.pageCoordinates = pageCoordinates;
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

    /**
     * @return the coSign
     */
    public boolean isCoSign() {
        return coSign;
    }

    /**
     * @param coSign the coSign to set
     */
    public void setCoSign(boolean coSign) {
        this.coSign = coSign;
    }

    /**
     * @return the appearanceText
     */
    public String getAppearanceText() {
        return appearanceText;
    }

    /**
     * @param appearanceText the appearanceText to set
     */
    public void setAppearanceText(String appearanceText) {
        this.appearanceText = appearanceText;
    }

    public String getAppearanceType() {
        return appearanceType;
    }

    public void setAppearanceType(String appearanceType) {
        this.appearanceType = appearanceType;
    }

    public String getImageSignBase64() {
        return imageSignBase64;
    }

    public void setImageSignBase64(String imageSignBase64) {
        this.imageSignBase64 = imageSignBase64;
    }

    public String getFeatureValueLeft() {
        return featureValueLeft;
    }

    public void setFeatureValueLeft(String featureValueLeft) {
        this.featureValueLeft = featureValueLeft;
    }

    public String getFeatureValueRight() {
        return featureValueRight;
    }

    public void setFeatureValueRight(String featureValueRight) {
        this.featureValueRight = featureValueRight;
    }

    public String getOneLinerText() {
        return oneLinerText;
    }

    public void setOneLinerText(String OneLinerText) {
        this.oneLinerText = OneLinerText;
    }

    public ContentSearchFeature getSearchString() {
        return searchString;
    }

    public void setSearchString(ContentSearchFeature searchString) {
        this.searchString = searchString;
    }

    public boolean isRightOrigin() {
        return rightOrigin;
    }

    public void setRightOrigin(boolean rightOrigin) {
        this.rightOrigin = rightOrigin;
    }

    
}
