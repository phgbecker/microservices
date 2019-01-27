package com.victor.microservice.resttesting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Classe para ajuda a consumir serviços REST
 * @author victorvieira
 *
 */
public class RESTHelper {
	
	private final static String USER_AGENT = "Mozilla/5.0";

	public static final String API_GET_ENOTAS_ENDPOINT = "https://api.enotasgw.com.br/v2/empresas/{eNotasKey}/nfc-e/{idEmissao}/";

	public static final String API_POST_RETURN_ENDPOINT = "https://{app}/pub/rest/enotas/return";

	public static final String API_POST_EXCLUDE_REST = "https://app.contaazul.com/rest/invoice/{invoiceId}'";
	
	public static final String PRODUCTION_KEY = "Basic YmZkYmNjNDctNDdkOC00ZmM1LThiNDYtYWE1ZmRlYzIwMTAw";
	
	public static final String SANDBOX_KEY = "Basic OGRkOTZlOTMtNDM1Ny00ZjAzLTk1YTEtOTI1ZDQwNGIwMTAw";
	
	public static final String PRODUCTION_APP = "app.contaazul.com";
	
	public static final String SANDBOX_APP = "app{nbr}.dev.contaazul.local";
	
	/**
	//command Run with sucess:
	//keytool -import -trustcacerts -keystore cacerts -storepass 123456 -noprompt -alias app12.dev.contaazul.local -file app12.cer
	-Djavax.net.ssl.keyStore="/home/victorvieira/Dev/keystore.jks"
	-Djavax.net.ssl.keyStore=/usr/lib/jvm/java-8-oracle/jre/lib/security/cacerts
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		//app name TODO: Ver com roberto aqui
//		importCertificate("app12.cer");

		//Path to certificate
//		setCertificateProperties();
		fixUntrustCertificate();
		
		callOldRestAPI("https://app12.dev.contaazul.local/pub/rest/enotas/return");
//		newCallRestAPI("https://app12.dev.contaazul.local/pub/rest/enotas/return");
	}

	private static void callOldRestAPI(String endpoint) throws Exception {
		final String JSON = "{" +
		    "\"tipo\":\"NFC-e\"," +
		    "\"empresaId\": \"530172\"," +
		    "\"nfeMotivoStatus\": \"\"," +
		    "\"nfeId\": \"881564\"," +
		    "\"nfeStatus\": \"Autorizada\"" +
		"}";
		
		URL obj = new URL( endpoint );
		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
		//evitar problema de certificado
		conn.setHostnameVerifier( new HostnameVerifier() {	
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		} );
		
		conn.setRequestMethod( "POST" );
		conn.setRequestProperty( "User-Agent", USER_AGENT );
		conn.setRequestProperty( "Content-Type", "application/json" );

		conn.setDoOutput( true );
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter( conn.getOutputStream() );
		outputStreamWriter.write( JSON );
		outputStreamWriter.flush();

		System.out.println( conn.getResponseCode() );
		
	}

	private static void setCertificateProperties() {
		String certificatesTrustStorePath = "/home/victorvieira/Dev/keystore.jks";
		System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
		
		System.setProperty("javax.net.ssl.keyStore", certificatesTrustStorePath);
		System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
      System.setProperty("javax.net.ssl.keyStorePassword", "123456");



        String trustStore = System.getProperty("javax.net.ssl.trustStore");
        if (trustStore == null) {
            System.out.println("javax.net.ssl.trustStore is not defined");
        } else {
            System.out.println("javax.net.ssl.trustStore = " + trustStore);
        }
	}

	private static void newCallRestAPI(String endpoint) {
		final Form form = new Form()
	            .param("tipo", "NFC-e")
	            .param("empresaId", "530172")
	            .param("nfeMotivoStatus", "Teste erro API")
	            .param("nfeId", "881564")
	            .param("nfeStatus", "Negada");
				
		Client client = ClientBuilder.newClient();
		javax.ws.rs.core.Response response = client.target(endpoint)
				.request( MediaType.APPLICATION_JSON).post(Entity.form(form));
		System.out.println( response.getStatus() );
		System.out.println( response.getHeaders() );
	}

	private static void importCertificate(String fileName) throws Exception {
		//Put everything after here in your function.
		KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(null);//Make an empty store
		InputStream fis = new FileInputStream("/home/victorvieira/Dev/" + fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);

		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		while (bis.available() > 0) {
		    Certificate cert = cf.generateCertificate(bis);
		    trustStore.setCertificateEntry("fiddler"+bis.available(), cert);
		}
		
	}
	
	public static void fixUntrustCertificate() throws KeyManagementException, NoSuchAlgorithmException {
		
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // set the  allTrusting verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
}	

}
