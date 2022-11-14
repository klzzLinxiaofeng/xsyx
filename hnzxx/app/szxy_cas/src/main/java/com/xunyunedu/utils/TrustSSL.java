package com.xunyunedu.utils;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


import javax.net.ssl.*;




public class TrustSSL {
    private static class TrustAnyTrustManager implements X509TrustManager {
    
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
    
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    public static void main(String[] args) throws Exception {
    	URL console = new URL("https://szxycas.studyo.cn/v1/tickets?username=864569&password=123456");
        HttpURLConnection conn = (HttpURLConnection) console.openConnection();
        if (conn instanceof HttpsURLConnection)  {
        	SSLContext sc = SSLContext.getInstance("SSL");
        	sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
        	((HttpsURLConnection) conn).setSSLSocketFactory(sc.getSocketFactory());
        	((HttpsURLConnection) conn).setHostnameVerifier(new TrustAnyHostnameVerifier());
        }
        conn.connect();
        System.out.println(conn.getResponseCode());
    }
}