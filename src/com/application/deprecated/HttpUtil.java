package com.application.deprecated;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.application.json.FlexJsonUtil;

/**
 * HTTP工具类，封装http请求
 * 
 * @author suntg
 * @date 2015年3月5日14:20:40
 */
@Deprecated
public class HttpUtil {

	/**
	 * 发送GET请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static Response sendGetRequest(String url, Map<String, Object> params)
			throws Exception {
		return sendHttpUriRequest(new HttpGet(getRequestUrl(url, params,
				false)));
	}

	/**
	 * 发送GET请求
	 * 
	 * @param url
	 * @param params
	 * @param sign
	 *            是否发送签名
	 * @return
	 * @throws Exception
	 */
//	@SuppressWarnings("unchecked")
//	public static Response sendGetRequest(String url, Map<String, ?> params,
//			boolean sign) throws Exception {
//		return sendRequest(RequestMethod.GET, url,
//				(Map<String, Object>) params, sign, false);
//	}

	/**
	 * 发送POST请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static Response sendPostRequest(String url, Map<String, Object> params)
			throws Exception {
		return sendPostRequest(url, params, false);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url
	 * @param params
	 * @param sign
	 *            是否发送签名
	 * @return
	 * @throws Exception
	 */
	public static Response sendPostRequest(String url, Map<String, Object> params,
			boolean sign) throws Exception {
		return sendHttpEntityEnclosingRequest(
				new HttpPost(getRequestUrl(url, sign)), params, false);
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url
	 * @param params
	 * @param sign
	 *            是否发送签名
	 * @param encode
	 *            是否使用URLEncode
	 * @return
	 * @throws Exception
	 */
	public static Response sendPostRequest(String url, Map<String, Object> params,
			boolean sign, boolean encode) throws Exception {
		return sendHttpEntityEnclosingRequest(
				new HttpPost(getRequestUrl(url, sign)), params, false);
	}

	/**
	 * 发送DELETE请求
	 * 
	 * @param url
	 * @param params
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public static Response sendDeleteRequest(String url, Map<String, ?> params)
			throws Exception {
		return sendDeleteRequest(url, params, false);
	}*/

	/**
	 * 发送DELETE请求
	 * 
	 * @param url
	 * @param params
	 * @param sign
	 *            是否发送签名
	 * @return
	 * @throws Exception
	 */
	

	/**
	 * 发起http请求
	 * 
	 * @param method
	 *            请求方法GET、POST、PUT、DELETE
	 * @param url
	 *            请求链接
	 * @param params
	 *            参数
	 * @param sign
	 *            是否签名
	 * @return
	 * @throws Exception
	 */
	/*public static Response sendRequest(RequestMethod method, String url,
			Map<String, Object> params, boolean sign, boolean encode)
			throws Exception {
		switch (method) {
		case GET:
			return sendHttpUriRequest(new HttpGet(getRequestUrl(url, params,
					sign)));
		case POST:
			return sendHttpEntityEnclosingRequest(
					new HttpPost(getRequestUrl(url, sign)), params, encode);
		case PUT:
			return sendHttpEntityEnclosingRequest(
					new HttpPut(getRequestUrl(url, sign)), params, encode);
		case DELETE:
			return sendHttpUriRequest(new HttpDelete(getRequestUrl(url, params,
					sign)));
		default:
			return sendHttpUriRequest(new HttpGet(getRequestUrl(url, params,
					sign)));
		}
	}*/

	/**
	 * 发起GET、DELETE请求
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private static Response sendHttpUriRequest(HttpRequestBase request)
			throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(request);
			return Response.getResponse(response);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 发起POST、PUT请求
	 * 
	 * @param request
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static Response sendHttpEntityEnclosingRequest(
			HttpEntityEnclosingRequestBase request, Map<String, Object> params,
			boolean encode) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			if (!encode) {
				request.setEntity(new StringEntity(
						FlexJsonUtil.toJson(params),
						ContentType.APPLICATION_JSON));
			} else {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				if (params != null && !params.isEmpty()) {
					Set<Entry<String, Object>> entrys = params.entrySet();
					for (Map.Entry<String, Object> entry : entrys) {
						nvps.add(new BasicNameValuePair(entry.getKey(),
								URLEncoder.encode(
										FlexJsonUtil.toJson(entry.getValue()),
										"UTF-8")));
					}
				}
				request.setEntity(new UrlEncodedFormEntity(nvps));
			}
			response = httpClient.execute(request);
			return Response.getResponse(response);
		} finally {
			request.releaseConnection();
			try {
				httpClient.close();
			} catch (IOException e) {
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 将请求参数添加到链接中
	 * 
	 * @param url
	 * @param params
	 * @param sign
	 *            是否签名
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getRequestUrl(String url, Map<String, Object> params,
			boolean sign) throws UnsupportedEncodingException {
		StringBuilder buf = new StringBuilder(url);
		if (url.indexOf("?") == -1)
			buf.append("?");
		else if (!url.endsWith("&"))
			buf.append("&");
		// 如果是GET请求，则请求参数在URL中
		if (params != null && !params.isEmpty()) {
			Set<Entry<String, Object>> entrys = params.entrySet();
			for (Map.Entry<String, Object> entry : entrys) {
				buf.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode(entry.getValue().toString(),
								"UTF-8")).append("&");
			}
		}
		if (sign) {
			// 加时间戳，保持相同请求每次签名均不一样
			buf.append("_timestamp=").append(System.currentTimeMillis());
		} else
			buf.deleteCharAt(buf.length() - 1);
		return buf.toString();
	}

	/**
	 * 将签名信息添加到链接中
	 * 
	 * @param url
	 * @param sign
	 *            是否签名
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getRequestUrl(String url, boolean sign)
			throws UnsupportedEncodingException {
		return getRequestUrl(url, null, sign);
	}

	/**
	 * 将输入流转为字节数组
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] read2Byte(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	/**
	 * 将输入流转为字符串
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static String read2String(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		try {
			outSteam.close();
			inStream.close();
		} catch (Exception e) {

		}
		return new String(outSteam.toByteArray(), "UTF-8");
	}

	/**
	 * 发送xml数据
	 * 
	 * @param path
	 *            请求地址
	 * @param xml
	 *            xml数据
	 * @param encoding
	 *            编码
	 * @return
	 * @throws Exception
	 */
	public static byte[] postXml(String path, String xml, String encoding)
			throws Exception {
		byte[] data = xml.getBytes(encoding);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "text/xml; charset=" + encoding);
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		conn.setConnectTimeout(5 * 1000);
		OutputStream outStream = conn.getOutputStream();
		outStream.write(data);
		outStream.flush();
		outStream.close();
//		if (conn.getResponseCode() == HttpStatus.OK.value()) {
//			return read2Byte(conn.getInputStream());
//		}
		return null;
	}

	/**
	 * http上传文件
	 * 
	 * @param postUrl
	 *            请求地址
	 * @param filePath
	 *            附件路径
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static Response upload(String postUrl, String filePath,
			Map<String, String> params) throws IllegalStateException,
			IOException, Exception {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(postUrl);
			FileBody fileBody = new FileBody(new File(filePath));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addPart("file", fileBody);
			if (params != null) {
				for (String paramKey : params.keySet()) {
					StringBody body = new StringBody(params.get(paramKey),
							ContentType.create("text/plain", Consts.UTF_8));
					builder.addPart(paramKey, body);
				}
			}
			HttpEntity reqEntity = builder.build();
			httpPost.setEntity(reqEntity);
			response = httpClient.execute(httpPost);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Response.getResponse(response);
	}

	/**
	 * 下载
	 * 
	 * @param postUrl
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static InputStream download(String postUrl)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(postUrl);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		return response.getEntity().getContent();
	}

	public static class Response {
		private int statusCode;
		private String responseText;

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public String getResponseText() {
			return responseText;
		}

		public void setResponseText(String responseText) {
			this.responseText = responseText;
		}

		public Response() {
		}

		public Response(HttpResponse response) throws IllegalStateException,
				IOException, Exception {
			this.statusCode = response.getStatusLine().getStatusCode();
			this.responseText = HttpUtil.read2String(response.getEntity()
					.getContent());
		}

		public static Response getResponse(HttpResponse response)
				throws IllegalStateException, IOException, Exception {
			if (response != null)
				return new Response(response);
			return null;
		}
	}
}
