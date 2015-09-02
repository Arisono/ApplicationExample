package com.test.java.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.test.java.SHA256.HmacUtils;


public class HttpUtil {

	public static Response sendGetRequest(String url, Map<String, String> params)
			throws Exception {
		return sendGetRequest(url, params, false);
	}


	public static Response sendGetRequest(String url,
			Map<String, String> params, boolean sign) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			StringBuilder buf = new StringBuilder(url);
			if (url.indexOf("?") == -1)
				buf.append("?");
			else if (!url.endsWith("&"))
				buf.append("&");
			// 濡傛灉鏄疓ET璇锋眰锛屽垯璇锋眰鍙傛暟鍦║RL涓�
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, String>> entrys = params.entrySet();
				for (Map.Entry<String, String> entry : entrys) {
					buf.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(), "UTF-8"))
							.append("&");
				}
			}
			if (sign) {
				buf.append("_timestamp=").append(System.currentTimeMillis());
				String message = buf.toString();
				buf.append("&_signature=").append(HmacUtils.encode(message));
			} else
				buf.deleteCharAt(buf.length() - 1);
			System.out.println("请求url:"+buf.toString());
			HttpGet httpGet = new HttpGet(buf.toString());
			response = httpClient.execute(httpGet);
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
	 * 鍙戦�POST璇锋眰
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static Response sendPostRequest(String url,
			Map<String, String> params) throws Exception {
		return sendPostRequest(url, params, false);
	}

	
	public static Response sendPostRequest(String url,
			Map<String, String> params, boolean sign) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		if (sign) {
			url += (url.indexOf("?") == -1 ? "?" : "&") + "_timestamp="
					+Long.valueOf("1441180144");
			url += "&_signature=" + HmacUtils.encode(url);
		}
		System.out.println(url);
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, String>> entrys = params.entrySet();
				for (Map.Entry<String, String> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLEncoder
							.encode(entry.getValue(), "UTF-8")));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpClient.execute(httpPost);
			return Response.getResponse(response);
		} finally {
			httpPost.releaseConnection();
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
		// if (conn.getResponseCode() == HttpStatus.OK.value()) {
		// return read2Byte(conn.getInputStream());
		// }
		return null;
	}


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
		private CookieStore cookieStore;

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
          
		
		public CookieStore getCookieStore() {
			return cookieStore;
		}

		public void setCookieStore(CookieStore cookieStore) {
			this.cookieStore = cookieStore;
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