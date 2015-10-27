package com.helloworld.logorecognize.baidu;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by yuexy on 2015/10/28.
 */
public class BaiduUtil
{
	public static String getRecognizeRes(String imgUrl) throws IOException
	{
		String res = "";
		String url = "http://image.baidu.com/n/pc_search?queryImageUrl=" + URLEncoder.encode(imgUrl, "utf-8") + "&fm=result&pos=&uptype=drag";

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		HttpResponse httpResponse = httpClient.execute(httpGet);

		res = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

		return res;
	}
}
