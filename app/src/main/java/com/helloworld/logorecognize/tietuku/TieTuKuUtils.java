package com.helloworld.logorecognize.tietuku;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by YueXy on 10/22/2015.
 */
public class TieTuKuUtils
{
	public static String doUpload(File f, String token)
	{
		String url = TieTuKuInfo.UPLOAD_API;

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		FileBody bin = new FileBody(f);
		MultipartEntity reqEntity = new MultipartEntity();

		try
		{
			reqEntity.addPart("file", bin);
			reqEntity.addPart("Token", new StringBody(token));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		try
		{
			httpPost.setEntity(reqEntity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			StringBuffer buffer = new StringBuffer();
			if (entity != null)
			{
				//start 读取整个页面内容
				InputStream is = entity.getContent();
				BufferedReader in = new BufferedReader(new InputStreamReader(is));

				String line = "";
				while ((line = in.readLine()) != null)
				{
					buffer.append(line);
				}
			}
			return buffer.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return "";
	}
}
