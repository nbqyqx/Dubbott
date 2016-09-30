package com.tops001.dubbott.test.provider.service.params;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.DefaultValueService;

@Service
public class DefaultValueServiceImpl implements DefaultValueService {

    private String version;

    private String limit;

    private String sort;
    
    @Override
	public void setVersion(String version) {
    	this.version = version;
	}

	@Override
	public void setLimit(String limit) {
		this.limit = limit;
	}

    public String getRow(Page page) {
        return "getRow:" + "offset="
               + page.getOffset()
               + ";version="
               + version
               + ";limit="
               + limit
               + ";sort="
               + sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

}
