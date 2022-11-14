package platform.education.util;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class JsEncryptionKey {

    private static RSAPrivateKey rsaPrivateKey = null;
    private static BASE64Decoder base64Decoder = new BASE64Decoder();

    private JsEncryptionKey() {
    }

    static {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("rsa_private_key_pkcs8.pem");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                if (read.charAt(0) == '-') {
                    continue;
                }
                stringBuilder.append(read).append("\r");
            }

            byte[] keyByte = base64Decoder.decodeBuffer(stringBuilder.toString());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String decryptByPublic(String data) throws Exception {
        byte[] bytes = base64Decoder.decodeBuffer(data);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        return new String(cipher.doFinal(bytes));
    }

    public static void main(String[] args) throws Exception {
        String validation = "Z5BLIRlaZlconx1KtaAfXCrIopOP2cGfRM+GmuySypbbu5gva+v/MWzXKK45fFkVzcTiC1XafYJ7YANdj0Xz5VCJda3RdCNqxpSSROUwSK57W6eanCne1OLCXt9q7J0HxT5xLWCd3OiTfN7IvLH+z0GqJVUT3jRKBjbFGRvHJTWs+b6p0nON3x3rOXd5DeXbw0a0CnhkVLZQs6GXfNYe4ME7sE9KhirvnFP5myp/PEjp86QneDloxMS7mvyyrDrBvXefwVSgw/UmyiIrCx8/1R9R8eV6iGoKWOXHQaXb+fb2YJFVitB7Ui/pSNXkfiHqmlM2fqzDbSElTHWgpc2V/w==";
        String c = decryptByPublic(validation);
    }
}
