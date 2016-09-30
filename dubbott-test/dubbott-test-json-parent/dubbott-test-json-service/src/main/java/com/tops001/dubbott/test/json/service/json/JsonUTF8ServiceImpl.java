package com.tops001.dubbott.test.json.service.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.json.api.json.JsonUTF8Service;
import com.tops001.dubbott.test.json.model.json.Country;

@Service
public class JsonUTF8ServiceImpl implements JsonUTF8Service {

    public Country[] getCountriesUpperCase() {
        List<Country> countries = new ArrayList<Country>();
        countries.add(new Country("DK", "DANMARK"));
        countries.add(new Country("EG", "ÆGYPTEN"));
        return countries.toArray(new Country[countries.size()]);
    }

    public Country[] getCountriesLowerCase() {
        List<Country> countries = new ArrayList<Country>();
        countries.add(new Country("DK", "danmark"));
        countries.add(new Country("EG", "ægypten"));
        return countries.toArray(new Country[countries.size()]);
    }
    
}
