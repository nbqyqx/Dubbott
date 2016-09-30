package com.tops001.dubbott.test.provider.service.webcontainer;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/common/dubbott-test-provider-client.xml")
public class MultipartServiceTest {

	@Test
	@Ignore("忽略文件上传测试")
	public void testUploadFile1() throws ClientProtocolException, IOException {

		File file1 = new File("src/test/resources/zhangtianai.jpg");
		Assert.assertTrue(file1.exists());

		File file2 = new File("src/test/resources/2BF0294CA373B264B438100E953A5E85.jpg");
		Assert.assertTrue(file2.exists());

		CloseableHttpClient client = HttpClientBuilder.create().build();
		try {
			HttpPost post = new HttpPost("http://" + HOST + "/dubbott-test-provider-service/v1.0/upload/many");
			FileBody fileBody1 = new FileBody(file1, ContentType.create("image/*"));
			FileBody fileBody2 = new FileBody(file2, ContentType.create("image/*"));

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addPart("file", fileBody1);
			builder.addPart("file", fileBody2);

			HttpEntity entity = builder.build();

			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			Assert.assertEquals(200, response.getStatusLine().getStatusCode());
		} finally {
			client.close();
		}
	}

	@Test
	@Ignore("忽略文件上传测试")
	public void testUploadFile2() throws ClientProtocolException, IOException {

		File file = new File("src/test/resources/zhangtianai.jpg");

		Assert.assertTrue(file.exists());

		CloseableHttpClient client = HttpClientBuilder.create().build();

		try {
			HttpPost post = new HttpPost("http://" + HOST + "/dubbott-test-provider-service/v1.0/upload/one");
			FileBody fileBody = new FileBody(file);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addPart("file", fileBody);

			HttpEntity entity = builder.build();

			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			Assert.assertEquals(200, response.getStatusLine().getStatusCode());
		} finally {
			client.close();
		}
	}

	private static final String HOST = "127.0.0.1:8080";

}