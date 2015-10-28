package com.helloworld.logorecognize;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.helloworld.logorecognize.baidu.BaiduUtil;
import com.helloworld.logorecognize.tietuku.TieTuKuUtils;
import com.helloworld.logorecognize.tietuku.token.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class MainActivity extends ActionBarActivity
{
	private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/logo";

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init()
	{
		File root = new File(PATH);
		if (!root.exists())
			root.mkdirs();

		imageView = (ImageView) findViewById(R.id.testImg);


		Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
		it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(PATH + "/hello.png")));

		startActivityForResult(it, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode != 1)
			return;

		if (resultCode != RESULT_OK)
			return;

		new Thread()
		{
			public void run()
			{
				String token = Token.createToken(new Date().getTime() + 3600, 1145559, "{\"height\":\"h\",\"width\":\"w\",\"s_url\":\"url\"}");
				System.out.println("TOKEN=> " + token);

				File f = new File(PATH + "/hello.png");
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
