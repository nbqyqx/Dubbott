package com.tops001.dubbott.test.provider.service.params;

import javax.ws.rs.core.PathSegment;

import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.params.PathSegmentService;

@Service
public class PathSegmentServiceImpl implements PathSegmentService {

	public String helloPath(PathSegment pathSegment) {
		return pathSegment.getPath();
	}

	public String helloPath(String path, PathSegment pathSegment, String matrix) {
		return path + "-" + pathSegment.getPath() + "-" + pathSegment.getMatrixParameters().get(matrix) + "-" + matrix;
	}
}
