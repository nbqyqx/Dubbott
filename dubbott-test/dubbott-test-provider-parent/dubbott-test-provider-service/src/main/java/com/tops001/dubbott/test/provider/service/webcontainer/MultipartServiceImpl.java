package com.tops001.dubbott.test.provider.service.webcontainer;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.activation.DataHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.stereotype.Service;

import com.tops001.dubbott.test.provider.api.webcontainer.MultipartService;

@Service
public class MultipartServiceImpl implements MultipartService {

	@Override
	public Response uploadFile1(List<Attachment> list) {
		try{
			for (Attachment attach : list) {
	            DataHandler dh = attach.getDataHandler();
	            Files.copy(dh.getInputStream(), Paths.get(new URI("file:"+DESTDIR+System.currentTimeMillis()+"-"+dh.getName())));
	        }
			return Response.ok(true).type(MediaType.TEXT_PLAIN).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(false).status(500).type(MediaType.TEXT_PLAIN).build();
		}
	}

	@Override
	public Response uploadFile2(Attachment image) {
		DataHandler dh = image.getDataHandler();
        try {
			Files.copy(dh.getInputStream(), Paths.get(new URI("file:"+DESTDIR+System.currentTimeMillis()+"-"+dh.getName())));
			return Response.ok(true).type(MediaType.TEXT_PLAIN).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(false).status(500).type(MediaType.TEXT_PLAIN).build();
		}
	}
	
	private static final String DESTDIR = "/data/tops001/upload/";
	
	static {
		File directory = new File(DESTDIR);
		if(!directory.exists()) {
			directory.mkdirs();
		}
	}

	
}
