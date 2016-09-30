package com.tops001.dubbott.test.provider.service.params;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.EncodingParamService;

@Service
public class EncodingParamServiceImpl implements EncodingParamService {

    private String appVersion;

    @Override
	public void setAppVersion(String appVersion) {
    	this.appVersion = appVersion;
	}

    public String getShopInCountry(String location) {
        return "getShopInCountry:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopInCountryMethod(String location) {
        return "getShopInCountryMethod:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopInCityMethod(String location) {
        return "getShopInCityMethod:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopInCity(String location) {
        return "getShopInCity:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopOnStreetMethod(String location) {
        return "getShopOnStreetMethod:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopOnStreet(String location) {
        return "getShopOnStreet:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopInRegion(String location) {
        return "getShopInRegion:location=" + location + ";appversion=" + appVersion;
    }

    public String getShopInRegionMethod(String location) {
        return "getShopInRegionMethod:location=" + location + ";appversion=" + appVersion;
    }

}
