package com.tops001.dubbott.test.provider.service.params;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.MatrixParamNotSetService;

@Service
public class MatrixParamNotSetServiceImpl implements MatrixParamNotSetService {

	public String getDefault(int count) {
		return count + "";
	}

	public String getDefault(short smallCount) {
		return smallCount + "";
	}

	public String getDefault(long longCount) {
		return longCount + "";
	}

	public String getDefault(float floatCount) {
		return floatCount + "";
	}

	public String getDefault(double count) {
		return count + "";
	}

	public String getDefault(byte count) {
		return count + "";
	}

	public String getDefault(char letter) {
		return letter + "";
	}

	public String getDefault(Set<Integer> stuff) {
		return stuff.size() + "";
	}

	public String getDefault(List<String> stuff) {
		return stuff.size() + "";
	}

}
