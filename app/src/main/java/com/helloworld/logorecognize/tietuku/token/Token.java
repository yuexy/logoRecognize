package com.helloworld.logorecognize.tietuku.token;

import com.helloworld.logorecognize.tietuku.TieTuKuInfo;
import com.helloworld.logorecognize.tietuku.util.Base64Util;
import com.helloworld.logorecognize.tietuku.util.HmacUtil;

/**
 * Created by YueXy on 10/22/2015.
 */
public class Token
{
	/**
	 * 创建TOKEN
	 *
	 * @param deadlineTime
	 * @param albumId
	 * @return
	 */
	public static String createToken(long deadlineTime, long albumId)
	{
		String accesskey = TieTuKuInfo.ACCESSKEY;
		String secretKey = TieTuKuInfo.SECREKEY;
		String json = "{\"deadline\":%s ,\"album\":\"%s\",\"returnBody\":\"\"}";
		json = String.format(json, deadlineTime, albumId);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}

	/**
	 * 创建TOKEN
	 *
	 * @param deadlineTime
	 * @return
	 */
	public static String createToken(long deadlineTime)
	{
		String accesskey = TieTuKuInfo.ACCESSKEY;
		String secretKey = TieTuKuInfo.SECREKEY;
		String json = "{\"deadline\":%s}";
		json = String.format(json, deadlineTime);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}

	/**
	 * 创建TOKEN
	 *
	 * @param deadlineTime
	 * @param albumId
	 * @param returnJson
	 * @return
	 */
	public static String createToken(long deadlineTime, long albumId, String returnJson)
	{
		String accesskey = TieTuKuInfo.ACCESSKEY;
		String secretKey = TieTuKuInfo.SECREKEY;
		String json = "{\"deadline\":%s ,\"album\":\"%s\",\"returnBody\":%s}";
		json = String.format(json, deadlineTime, albumId, returnJson);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}

	/**
	 * 创建TOKEN
	 *
	 * @param deadlineTime
	 * @param albumId
	 * @param returnJson
	 * @return
	 */
	public static String createToken(String accesskey, String secretKey, long deadlineTime, long albumId, String returnJson)
	{
		String json = "{\"deadline\":%s ,\"album\":\"%s\",\"returnBody\":\"%s\"}";
		json = String.format(json, deadlineTime, albumId, returnJson);
		String base64param = Base64Util.base64(json.getBytes()).trim();
		String sign = HmacUtil.hmac_sha1(base64param, secretKey);
		String token = accesskey + ":" + sign + ":" + base64param;
		return token;
	}
}
