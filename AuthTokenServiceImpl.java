package com.hpe.mast.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hpe.mast.beans.Margin;
import com.hpe.mast.beans.MarginItem;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import sun.misc.BASE64Encoder;
//import Decoder.BASE64Encoder;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

	public String getAuthResponse(AuthTokenBean authTokenBean) {

		String userName = "gunjit.anand@hpe.com";
		String password = "YWNlLnBhbi01MA==";
		String buildUrl = "https://g4t9527.houston.hpecorp.net:8543/EntityMdmPartyService-0.1.3-SNAPSHOT/authentication";

		String URL = buildUrl;
		String authString = userName + ":" + password;
		String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		ClientResponse readConfigResponse = null;
		String configJSON = null;

		final JSONObject jsonToSend = new JSONObject();
		try {
			jsonToSend.put("userName", "gunjit.anand@hpe.com");
			jsonToSend.put("password", "YWNlLnBhbi01MA==");

			String truststorefilename = System.getProperty("CONFIG_DIR");
			truststorefilename = "C://Users//du20013955//Desktop//STA//emdm-hub-devitcshpecorpnet.jks";
			System.setProperty("javax.net.ssl.trustStore", truststorefilename);
			System.setProperty("javax.net.ssl.trustStorePassword", "wipro@123");
			Client client = Client.create();
			WebResource webResource = client.resource(URL);

			readConfigResponse = webResource.type(MediaType.APPLICATION_JSON)
					.header("Authorization", "Basic " + authStringEnc)
					.post(ClientResponse.class, jsonToSend.toString());
			System.out.println("STATUS--" + readConfigResponse.getStatus());
			readConfigResponse.bufferEntity();
			configJSON = readConfigResponse.getEntity(String.class);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			if (null != readConfigResponse)
				readConfigResponse.close();

		}

		return configJSON;

	}

	public AuthTokenBean jsonToBean(String configJsonData) {
		try {
			JSONObject configJson = new JSONObject(configJsonData);

			AuthTokenBean authTokenBean = new AuthTokenBean();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

}
