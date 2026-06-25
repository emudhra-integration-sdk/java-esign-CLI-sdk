/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esignasplibrarycli;

import com.emudhra.esign.eSign;
import com.emudhra.esign.eSignInput;
import com.emudhra.esign.eSignServiceReturn;
import com.emudhra.esign.eSignSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.emcastle.util.encoders.Base64;
import request.BankKYCRequest;
import request.BaseInput;
import request.GetGatewayParamInput;
import request.GetSignedDocInput;
import request.GetStatusInput;
import request.SignInput;
import request.UserInfo;
import response.CLIOutput;

/**
 *
 * @author 20476
 */
public class ESignASPLibraryCLI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Invalid Argument");
                printHelp();
                return;
            }

            CliArgs cliArgs = new CliArgs(args);
//            if (cliArgs.switchPresent("-test")) {
//                printHelp();
//                System.out.println("============= Running Test =================");
//                eSignAspLibraryCLITest.test();
//                return;
//            }
            if (!cliArgs.switchPresent("-method")) {
                System.out.println("Invalid argument -method must be present");
                printHelp();
                return;
            }
            if (!cliArgs.switchPresent("-input")) {
                System.out.println("Invalid argument -input must be present");
                printHelp();
                return;
            }
            if (!cliArgs.switchPresent("-output")) {
                System.out.println("Invalid argument -output must be present");
                printHelp();
                return;
            }
            if (cliArgs.switchPresent("-h")) {
                printHelp();
                return;
            }
            if (cliArgs.switchPresent("-help")) {
                printHelp();
                return;
            }
            String method = cliArgs.switchValue("-method").trim();
            String inputJsonPath = cliArgs.switchValue("-input").trim();
            String outputJsonPath = cliArgs.switchValue("-output").trim();
            processRequest(method, inputJsonPath, outputJsonPath);

        } catch (IOException | IllegalArgumentException | NoSuchAlgorithmException e) {
            e.printStackTrace(System.out);
            System.out.println("Failure");
            return;
        }
        System.out.println("Successful");
    }

    public static void processRequest(String method, String inputJsonFilePath, String outputJsonFilePath) throws IllegalArgumentException, IOException, NoSuchAlgorithmException {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        File inputJsonPath = new File(inputJsonFilePath);
        File outputJsonPath = new File(outputJsonFilePath);
        switch (method.toUpperCase()) {
            case "GETGATEWAYPARAM": {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputJsonPath)); FileWriter writer = new FileWriter(outputJsonPath);) {
                    Type t = new TypeToken<GetGatewayParamInput>() {
                    }.getType();
                    GetGatewayParamInput req = gson.fromJson(bufferedReader, t);
                    eSign eSignObj = initLibrary(req);
                    ArrayList<eSignInput> inputs = new ArrayList<>();
                    
                    for (SignInput si : req.getInputs()) {
                        if (si.isIsHash()) {
                            eSignInput input = new eSignInput(si.getDocInfo(), si.getBase64doc(), si.getDocURL());
                            inputs.add(input);
                        } else {
                            
                            eSignInput input =  eSignInputsFeatureHandler.geteSignInputs(si,si.getBase64doc());
//                            eSignInput eSignInput = new eSignInput(si.getBase64doc(), si.getDocInfo(), si.getDocURL(), si.getLocation(), si.getReason(), si.getSignerName(), si.isCoSign(), si.getPageCoordinates(), si.getAppearanceText());
                            inputs.add(input);
                        }
                    }

                    eSignServiceReturn serviceReturn = eSignObj.getGatewayParameter(inputs, req.getSignerID(), req.getTransactionID(), req.getResponseURL(), req.getRedirectURL(), req.getTempFolderPath(), req.geteSignType(),  req.getAuthMode());
                    CLIOutput out = new CLIOutput(serviceReturn, "GETGATEWAYPARAM");
                    gson.toJson(out, writer);
                }
            }
            break;
            case "GETSIGNEDPDF": {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputJsonPath)); FileWriter writer = new FileWriter(outputJsonPath);) {
                    Type t = new TypeToken<GetSignedDocInput>() {
                    }.getType();
                    GetSignedDocInput req = gson.fromJson(bufferedReader, t);
                    eSign eSignObj = initLibrary(req);
                    String responseXML = new String(Base64.decode(req.getResponseXML()), StandardCharsets.UTF_8);
                    eSignServiceReturn serviceReturn = eSignObj.getSigedDocument(responseXML, req.getPreSignedTempFile());
                    CLIOutput out = new CLIOutput(serviceReturn, "GETSIGNEDPDF");
                    gson.toJson(out, writer);
                }
            }
            break;
            case "GETTRANSACTIONSTATUS": {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputJsonPath)); FileWriter writer = new FileWriter(outputJsonPath);) {
                    Type t = new TypeToken<GetStatusInput>() {
                    }.getType();
                    GetStatusInput req = gson.fromJson(bufferedReader, t);
                    eSign eSignObj = initLibrary(req);
                    eSignServiceReturn serviceReturn = eSignObj.getStatus(req.getTransactionID());
                    CLIOutput out = new CLIOutput(serviceReturn, "GETTRANSACTIONSTATUS");
                    gson.toJson(out, writer);
                }
            }
            break;
            case "PERFORMBANKKYC": {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputJsonPath));
                        FileWriter writer = new FileWriter(outputJsonPath);) {
                    Type t = new TypeToken<BankKYCRequest>() {
                    }.getType();
                    BankKYCRequest req = gson.fromJson(bufferedReader, t);
                    eSign eSignObj = initLibrary(req);
                    UserInfo ui = req.getUserInfo();
                    com.emudhra.esign.UserInfo info = new com.emudhra.esign.UserInfo(ui.getName(), ui.getMobile(), ui.getEmail(), ui.getAddress(), ui.getStateProvince(), ui.getCountry(),
                            ui.getPostalCode(),ui.getDateOfBirth(), ui.getGender(), ui.getPan(), ui.getAadhaar(), ui.getPhotoFormat(), ui.getPhotoBase64());
                    eSignServiceReturn serviceReturn = eSignObj.performBankKYC(req.getTransactionID(), req.getIFSCCode(), req.getBankName(), req.getAccountNumber(), info, req.getBankKYCURL());
                    CLIOutput out = new CLIOutput(serviceReturn, "PERFORMBANKKYC");
                    gson.toJson(out, writer);
                }
            }
            break;
            default: {
                throw new IllegalArgumentException("Illeagal argument " + method + " for -method parameter.");
            }
        }
    }

    private static eSign initLibrary(BaseInput req) throws NoSuchAlgorithmException {
        return new eSign(
                req.getAspId(),
                req.geteSignURL(),
                req.geteSignURLV2(),
                req.getPfxPath(),
                req.getPfxPassword(),
                req.getPfxAlias(),
                req.isProxyReq(),
                req.getProxyIp(),
                req.getProxyPort(),
                req.getSessionTimeout(),
                eSignSettings.LogType.AllLog,
                req.getProxyUserID(),
                req.getProxyUserPassword(), null, 0);
    }
    
    private static Map<String, String> getRequestParametersMap(Map<String, String[]> map) {
        Map<String, String> RequestParameters = new HashMap<>();

        for (String parameterName : map.keySet()) {
            String[] values = map.get(parameterName);
            if (values != null && values.length > 0) {
                RequestParameters.put(parameterName, values[0]);
            } else {
                RequestParameters.put(parameterName, null);
            }
        }
        return RequestParameters;
    }

    private static void printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("============== eSign ASP library CLI help ===========\n\n");
        sb.append("usage:\n");
        sb.append("\tjava -jar .\\eSignASPLibraryCLI.jar -method method -input input.json -output output.json");
        sb.append("\n");
        sb.append("\n-input\t: input file path containing input json.");
        sb.append("\n-output\t: output file to receive output json.");
        sb.append("\n-method\t: supports three methods.");
        sb.append("\n\t\t1.getGatewayParam.");
        sb.append("\n\t\t2.getSignedPdf.");
        sb.append("\n\t\t3.getTransactionStatus.");
        sb.append("\n\t\t3.performBankKYC.");
        sb.append("\n\nPlease check document for more details.");
        sb.append("\n\n==================================================\n\n");
        System.out.println(sb);
    }

}
