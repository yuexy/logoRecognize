package com.helloworld.logorecognize;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.helloworld.logorecognize.baidu.BaiduUtil;
import com.helloworld.logorecognize.tietuku.TieTuKuUtils;
import com.helloworld.logorecognize.tietuku.token.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class MainActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init()
	{
		new Thread()
		{
			public void run()
			{
				String token = Token.createToken(new Date().getTime() + 3600, 1145559, "{\"height\":\"h\",\"width\":\"w\",\"s_url\":\"url\"}");
				System.out.println("TOKEN=> " + token);

				File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/JAVA.jpg");
				String res = TieTuKuUtils.doUpload(f, token);

				System.out.println("resule=> " + res);

				JSONObject jsonObject = null;
				try
				{
					jsonObject = new JSONObject(res);
					String url = jsonObject.getString("url");

					System.out.println("URL=> " + url);

					res = BaiduUtil.getRecognizeRes(url);

					System.out.println(res);
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

			}
		}.start();
	}
}
