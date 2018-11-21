package com.hpe.mast.controller;

import org.springframework.stereotype.Service;

@Service
public interface AuthTokenService {

	public String getAuthResponse(AuthTokenBean authTokenBean);

	public AuthTokenBean jsonToBean(String configJsonData);

}
