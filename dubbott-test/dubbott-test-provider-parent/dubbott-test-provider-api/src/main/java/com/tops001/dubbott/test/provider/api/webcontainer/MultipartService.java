package com.tops001.dubbott.test.provider.api.webcontainer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

@Path("/upload")
public interface MultipartService {
	
	/**
     * 上传多个文件
     * 指定只能上传图片
     */
    @POST
    @Path("many")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.MULTIPART_FORM_DATA })
    public Response uploadFile1(@Multipart(value = "file", type = "image/*") List<Attachment> list);

    /**
     * 上传单个文件
     * @Multipart(value="file") 可以不加
     */
	@POST
    @Path("one")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.MULTIPART_FORM_DATA })
    public Response uploadFile2(@Multipart(value = "file") Attachment image);
	
}
