package com.tops001.dubbott.test.provider.service.params;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.AutoDecodeParamService;

@Service
public class AutoDecodeParamServiceImpl implements AutoDecodeParamService {

    private String appVersion;

    @Override
	public void setAppVersion(String appVersion) {
    	this.appVersion = appVersion;
	}

    public String getShopInCountryDecoded(String location) {
        return "getShopInCountryDecoded:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopInCityDecoded(String location) {
        return "getShopInCityDecoded:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopOnStreetDecoded(String location) {
        return "getShopOnStreetDecoded:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopInRegionDecoded(String location) {
        return "getShopInRegionDecoded:location=" + location + ";appversion=" + appVersion;
    }

}
