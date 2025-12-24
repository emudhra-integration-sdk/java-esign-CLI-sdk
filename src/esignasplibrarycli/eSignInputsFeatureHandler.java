package esignasplibrarycli;

import com.emudhra.esign.*;

import java.io.IOException;
import request.ContentSearchFeature;
import request.SignInput;

public class eSignInputsFeatureHandler {

    public static eSign.PageTobeSigned pageToBeSigned = eSign.PageTobeSigned.First;

    public static eSignInput geteSignInputs(SignInput requestMap, String base64) throws IOException {
        ContentSearchFeature ContentSearchType = requestMap.getSearchString();
        String type = requestMap.getAppearanceType();
        ContentSearch searchString = new ContentSearch();
        CustomStyle customStyle = new CustomStyle();
        eSignInput bulider;

        if (ContentSearchType != null) {
            pageToBeSigned = eSign.PageTobeSigned.PageLevel;
            ContentSearch.Position positions = requestMap.getSearchString().getPosition();
            searchString.setHeight(requestMap.getSearchString().getHeight());
            searchString.setWidth(requestMap.getSearchString().getWidth());
            searchString.setOffset(requestMap.getSearchString().getOffset());
            searchString.setSearchText(requestMap.getSearchString().getSearchText());
            searchString.setPosition(positions);
        }
//        else {
//            bulider = eSignInputBuilder.init()
//                    .setDocBase64(base64)
//                    .setAppearanceType(eSign.AppearanceType.StandardSignature)
//                    .setDocInfo("test")
//                    .setDocURL("")
//                    .setLocation(requestMap.getSearchText())
//                    .setReason("test")
//                    .setSignedBy("Signer Name")
//                    .setCoSign(true)
//                    .setPageTobeSigned(eSign.PageTobeSigned.All)
//                    .setCoordinates(eSign.Coordinates.BottomRight)
//                    .build();
//            return bulider;
//        }

        switch (type) {
            case "StandardSignature": {
                String reason = requestMap.getReason();
                String Location = requestMap.getLocation();
                if (ContentSearchType != null) {
                    bulider = eSignInputBuilder.init()
                            .setAppearanceType(eSign.AppearanceType.StandardSignature)
                            .setDocBase64(base64)
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setReason((reason.isEmpty()) ? "" : reason)
                            .setLocation((Location.isEmpty()) ? "" : Location)
                            .setTickRequired(true)
                            .setCoSign(requestMap.isCoSign())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setContentSearch(searchString)
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                } else {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.StandardSignature)
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setLocation((Location.isEmpty()) ? "" : Location)
                            .setReason((reason.isEmpty()) ? "" : reason)
                            .setTickRequired(true)
                            .setCoSign(requestMap.isCoSign())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setPageLevelCoordinates(requestMap.getPageCoordinates())
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                }
            }
            break;

            case "SignatureImage": {
                if (ContentSearchType != null) {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.SignatureImage)
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setSignatureImage(requestMap.getImageSignBase64())
                            .setCoSign(requestMap.isCoSign())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setContentSearch(searchString)
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                } else {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.SignatureImage)
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setSignatureImage(requestMap.getImageSignBase64())
                            .setCoSign(requestMap.isCoSign())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setPageLevelCoordinates(requestMap.getPageCoordinates())
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                }
            }
            break;

            case "advanceSignature": {
                String leftSideText = requestMap.getFeatureValueLeft();
                String rightSideText = requestMap.getFeatureValueRight();
                if (leftSideText.equals("")) {
                    leftSideText = "Digitally signed";
                }
                if (rightSideText.equals("")) {
                    rightSideText = "\nSigned by: " + requestMap.getSignerName() + "\n"
                            + "Reason: \n"
                            + "Location: ";
                }
                AdvanceSignature advSig = new AdvanceSignature();
                advSig.setImageType(Enums.ImageType.Other);
                advSig.setImagebase64(requestMap.getImageSignBase64());
                advSig.setLeftSideText(leftSideText);
                advSig.setRightSideText(rightSideText);
                if (ContentSearchType != null) {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.advanceSignature)
                            .setAdvanceSignature(advSig)
                            .setCoSign(requestMap.isCoSign())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setContentSearch(searchString)
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                } else {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.advanceSignature)
                            .setAdvanceSignature(advSig)
                            .setCoSign(requestMap.isCoSign())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setPageLevelCoordinates(requestMap.getPageCoordinates())
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                }
            }
            break;

            case "OneLiner": {
                String oneLiner = requestMap.getOneLinerText();
                if (oneLiner.equals("")) {
                    oneLiner = "Digitally Signed by " + requestMap.getSignerName() + "";
                }

                String finalOneLiner1 = oneLiner;
                if (ContentSearchType != null) {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.OneLiner)
                            .setOneLiner(finalOneLiner1)
                            .setCoSign(requestMap.isCoSign())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setContentSearch(searchString)
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                } else {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.OneLiner)
                            .setOneLiner(finalOneLiner1)
                            .setCoSign(requestMap.isCoSign())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setPageLevelCoordinates(requestMap.getPageCoordinates())
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                }
            }
            break;

            case "ColoredGraphic":
                String reason = requestMap.getReason();
                String Location = requestMap.getLocation();
                if (ContentSearchType != null) {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.ColoredGraphic)
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setReason((reason.isEmpty()) ? "" : reason)
                            .setLocation((Location.isEmpty()) ? "" : Location)
                            .setCoSign(requestMap.isCoSign())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setContentSearch(searchString)
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                } else {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.ColoredGraphic)
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setReason((reason.isEmpty()) ? "" : reason)
                            .setLocation((Location.isEmpty()) ? "" : Location)
                            .setCoSign(requestMap.isCoSign())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setPageLevelCoordinates(requestMap.getPageCoordinates())
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                }
                break;
                
            case "BackgroundImage":
                if (ContentSearchType != null) {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.BackgroundImage)
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setReason((requestMap.getReason().isEmpty()) ? "" : requestMap.getReason())
                            .setLocation((requestMap.getLocation().isEmpty()) ? "" : requestMap.getLocation())
                            .setSignatureImage(requestMap.getImageSignBase64())
                            .setCoSign(requestMap.isCoSign())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setContentSearch(searchString)
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                } else {
                    bulider = eSignInputBuilder.init()
                            .setDocBase64(base64)
                            .setAppearanceType(eSign.AppearanceType.BackgroundImage)
                            .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                            .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                            .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                            .setReason((requestMap.getReason().isEmpty()) ? "" : requestMap.getReason())
                            .setLocation((requestMap.getLocation().isEmpty()) ? "" : requestMap.getLocation())
                            .setSignatureImage(requestMap.getImageSignBase64())
                            .setCoSign(requestMap.isCoSign())
                            .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                            .setPageLevelCoordinates(requestMap.getPageCoordinates())
                            .isRightOrigin(requestMap.isRightOrigin())
                            .build();
                }
                break;
            default:
                bulider = eSignInputBuilder.init()
                        .setDocBase64(base64)
                        .setAppearanceType(eSign.AppearanceType.StandardSignature)
                        .setDocInfo((requestMap.getDocInfo().isEmpty()) ? "DocInfo1" : requestMap.getDocInfo())
                        .setDocURL((requestMap.getDocURL().isEmpty()) ? "http://" : requestMap.getDocURL())
                        .setSignedBy((requestMap.getSignerName().isEmpty()) ? "" : requestMap.getSignerName())
                        .setLocation((requestMap.getLocation().isEmpty()) ? "" : requestMap.getLocation())
                        .setReason((requestMap.getReason().isEmpty()) ? "" : requestMap.getReason())
                        .setCoSign(requestMap.isCoSign())
                        .setTickRequired(true)
                        .setPageTobeSigned(eSign.PageTobeSigned.PageLevel)
                        .setPageLevelCoordinates(requestMap.getPageCoordinates())
                        .isRightOrigin(requestMap.isRightOrigin())
                        .build();
                break;
        }
        return bulider;
    }
}
