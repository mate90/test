package com.example.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class TestApplication {

    private static final Logger logger = LogManager.getLogger(TestApplication.class);
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestApplication.class, args);
        // logger.info("Hello from Log4j 2 :{}","Info");
        // logger.error("Hello from Log4j 2 :{}","Error");
        // logger.warn("Hello from Log4j 2 :{}","Warning");
        // Pattern p = Pattern.compile(".s");//. represents single character  
        // Matcher m = p.matcher("as");  
        // InputStream in = new FileInputStream("C:/Users/Mehdi/Documents/test/202112060000.CDR");
        // String file_name = "C:/Users/Mehdi/Documents/test/202112060000.CDR";
        // if(file_name.matches(".*202111\\d{6}\\.CDR"))
        //     System.out.println(getMD5Checksum(file_name));
        // String cResult;
        // String dResult;
        String cUrl = "http://localhost:7073/WSVOIP/createNewUser?userName=2191021015&password=abc@12345&comment=abc";
        String dUrl = "http://localhost:7073/WSVOIP/deleteUser?userName=2191021015";
        // String cUrl = "http://localhost:8082/createNewUser?userName=2191021015&password=abc@12345&comment=abc";
        // String dUrl = "http://localhost:8082/deleteUser?userName=2191021015";
        // String cUrl = "http://89.165.3.105:9999/wsvoip/createNewUser?userName=2191021015&password=abc@12345&comment=abc";
        // String dUrl = "http://89.165.3.105:9999/wsvoip/deleteUser?userName=2191021015";
        for (int i = 0 ; i < 100; i++) {
          logger.info("Create {} Response:{}", i, getRestTemplate().getForObject(cUrl, String.class));
          logger.info("Delete {} Response:{}", i, getRestTemplate().getForObject(dUrl, String.class));
          
        }
    }
    public static RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
      TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
      SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
      SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
      CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
      HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
      requestFactory.setHttpClient(httpClient);
      return new RestTemplate(requestFactory);
  }
   // see this How-to for a faster way to convert
   // a byte array to a HEX string
  //  public static String getMD5Checksum(String filename) throws Exception {
  //   logger.info("Hello from Log4j 2 :{}","Info");
  //   InputStream fis =  new FileInputStream(filename);

  //   byte[] buffer = new byte[1024];
  //   MessageDigest complete = MessageDigest.getInstance("MD5");
  //   int numRead;
  //   do {
  //    numRead = fis.read(buffer);
  //    if (numRead > 0) {
  //      complete.update(buffer, 0, numRead);
  //      }
  //    } while (numRead != -1);
  //   fis.close();
  //    byte[] b = complete.digest();
  //    String result = "";
  //    for (int i=0; i < b.length; i++) {
  //      result +=
  //         Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
  //     }
  //    return result;
  //  }

}
