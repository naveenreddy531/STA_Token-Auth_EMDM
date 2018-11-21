package com.hpe.mast.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.mast.beans.Margin;
import com.hpe.mast.beans.MarginItem;
import com.hpe.mast.integration.oca.interfaces.ConfigDataService;

@RestController
@RequestMapping("/rest/authToken")
public class AuthTokenRestEndPoint {

	@Autowired
	private AuthTokenService authTokenService;

	@RequestMapping(value = "/authUser", method = RequestMethod.POST)
	public @ResponseBody String getAuthResponse(@RequestBody AuthTokenBean authTokenBean) {
		String readQuoteJsonData = authTokenService.getAuthResponse(authTokenBean);
		// AuthTokenBean authTokenBean1 =
		// authTokenService.jsonToBean(readQuoteJsonData);
		return readQuoteJsonData;
	}
}
