package com.helloworld.logorecognize.tietuku.util;

import android.util.Base64;

/**
 * Created by YueXy on 10/22/2015.
 */
public class Base64Util
{
	public static String base64(byte[] target)
	{
		return Base64.encodeToString(target, Base64.DEFAULT).replace('+', '-').replace('/', '_');
	}

	public static String base64(String target)
	{
		return Base64.encodeToString(target.getBytes(), Base64.DEFAULT).replace('+', '-').replace('/', '_');
	}
}