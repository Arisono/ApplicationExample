package com.application.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.application.java.arithmetic.HmacUtils;

/**
 * @author :LiuJie 2015年9月3日 上午8:48:22
 * @注释:工具类
 */
@SuppressWarnings("deprecation")
public class HttpUtil {

	public static Response sendGetRequest(String url, Map<String, Object> params)
			throws Exception {
		return sendGetRequest(url, params, false,null);
	}


	public static Response sendGetRequest(String url,
			Map<String, Object> params, boolean sign,String cookie) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			StringBuilder buf = new StringBuilder(url);
			if (url.indexOf("?") == -1)
				buf.append("?");
			else if (!url.endsWith("&"))
				buf.append("&");
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					buf.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"))
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
			httpGet.addHeader("Cookie", "JSESSIONID=" +null);
			httpGet.addHeader("Cookie", "JSESSIONID=" +cookie);
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
	 * @author LiuJie
	 * @功能:Get 参数,请求头
	 */
	public static Response sendGetHeaderRequest(String url,
			Map<String, Object> params,LinkedHashMap<String, Object> headers, boolean sign) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			StringBuilder buf = new StringBuilder(url);
			if (url.indexOf("?") == -1)
				buf.append("?");
			else if (!url.endsWith("&"))
				buf.append("&");
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					buf.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"))
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
			if (headers!=null) {
				for(String key:headers.keySet()){
					System.out.println("add header:"+key+" value:"+headers.get(key).toString());
					httpGet.setHeader(key, headers.get(key).toString());
				}
			}
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
	 * @author LiuJie
	 * @功能:post请求  添加请求头参数
	 */
	public static Response sendPostHeaderRequest(String url,
			Map<String, Object> params,LinkedHashMap<String, Object> headers, boolean sign) throws Exception {
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
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLEncoder
							.encode(entry.getValue().toString(), "utf-8")));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			if (headers!=null) {
				for(String key:headers.keySet()){
					System.out.println("add header:"+key+" value:"+headers.get(key).toString());
					httpPost.setHeader(key, headers.get(key).toString());
				}
			}
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
	
	/**
	 * @author LiuJie
	 * @功能: post  json参数
	 */
	
	public static Response sendPostJsonRequest(String url,
			Map<String, Object> params,LinkedHashMap<String, Object> headers,String bodyString, boolean sign) throws Exception {
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
			/*List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLEncoder
							.encode(entry.getValue().toString(), "utf-8")));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));*/
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			if (headers!=null) {
				for(String key:headers.keySet()){
					System.out.println("add header:"+key+" value:"+headers.get(key).toString());
					httpPost.setHeader(key, headers.get(key).toString());
				}
			}
			if (bodyString!=null) {
				httpPost.setEntity(new StringEntity(bodyString,"UTF-8"));
			}
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
	
	public static Response sendGetRequest(String url,
			Map<String, Object> params, boolean sign) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			StringBuilder buf = new StringBuilder(url);
			if (url.indexOf("?") == -1)
				buf.append("?");
			else if (!url.endsWith("&"))
				buf.append("&");
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					buf.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"))
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
	public static Response sendPostRequest(String url,
			Map<String, Object> params) throws Exception {
		return sendPostRequest(url, params, false);
	}

	
	public static CloseableHttpClient createSSLClientDefault(){
		try {
		             javax.net.ssl.SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
		                 //信任所有
		                 public boolean isTrusted(X509Certificate[] chain,
		                                 String authType) throws CertificateException {
		                     return true;
		                 }
		             }).build();
		             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		         } catch (KeyManagementException e) {
		             e.printStackTrace();
		         } catch (NoSuchAlgorithmException e) {
		             e.printStackTrace();
		         } catch (KeyStoreException e) {
		             e.printStackTrace();
		         }
		         return  HttpClients.createDefault();
		}
	
	
	public static Response sendHttpsPostRequest(String url,
			Map<String, Object> params, boolean sign) throws Exception {
		CloseableHttpClient httpClient =HttpClients.createDefault();
		CloseableHttpResponse response = null;
		if (sign) {
			url += (url.indexOf("?") == -1 ? "?" : "&") + "_timestamp="
					+Long.valueOf("1441180144");
			url += "&_signature=" + HmacUtils.encode(url);
		}
		System.out.println(url);
		HttpPost httpPost = new HttpPost(url);
		try {
/*			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLEncoder
							.encode(entry.getValue(), "utf-8")));
				}
			}
*/			StringEntity entity = new StringEntity(FlexJsonUtil.toJson(params),"utf-8");
			entity.setContentType("application/json");
			entity.setContentEncoding("UTF-8");  
			httpPost.setEntity(entity);
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
	
	
	public static Response sendPostRequest(String url,
			Map<String, Object> params, boolean sign) throws Exception {
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
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLEncoder
							.encode(entry.getValue().toString(), "utf-8")));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			httpPost.setHeader("Cookie",
					"JSESSIONID=" + params.get("sessionId"));
			//httpPost.setEntity(new StringEntity(FlexJsonUtil.toJson(params)));
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
	
	public static Response sendPostRequestNew(String url,
			Map<String, Object> params, boolean sign) throws Exception {
		@SuppressWarnings({ "resource" })
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = null;
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
				Set<Entry<String, Object>> entrys = params.entrySet();
				for (Map.Entry<String, Object> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLDecoder
							.decode(entry.getValue().toString(), "utf-8")));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(8000).setConnectTimeout(8000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			httpPost.setHeader("Cookie",
					"JSESSIONID=" + params.get("sessionId"));
			//httpPost.setEntity(new StringEntity(FlexJsonUtil.toJson(params)));、
			
			response = httpClient.execute(httpPost);
			CookieStore cookieStore = ((AbstractHttpClient) httpClient)
					.getCookieStore();
		    Response.cookieStore=cookieStore;
			System.out.println(cookieStore.toString());
			return Response.getResponse(response);
		} finally {
			httpPost.releaseConnection();
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
		return null;
	}


	public static Response upload(String postUrl, String filePath,
			Map<String, Object> params) throws IllegalStateException,
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
					StringBody body = new StringBody(params.get(paramKey).toString(),
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
		public  static CookieStore cookieStore;

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
			Response.cookieStore = cookieStore;
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
	
	public static DefaultHttpClient getHttpsClient() {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
				}

				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

			};
			DefaultHttpClient client = new DefaultHttpClient();
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);

			ClientConnectionManager ccm = client.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			// 设置要使用的端口，默认是443
			sr.register(new Scheme("https", 443, ssf));
			return client;
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @author asflex
	 * @throws UnsupportedEncodingException
	 * @date 2014-3-28下午7:24:02
	 * @modify 2014-3-28下午7:24:02
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String post(String url, Map<String, Object> params)
			throws UnsupportedEncodingException {
		DefaultHttpClient httpClient = getHttpsClient();
		HttpPost post = new HttpPost(url);
		List data = null;
		if (params != null) {
			data = new ArrayList(params.size());
			for (Map.Entry entry : params.entrySet()) {
				data.add(new BasicNameValuePair((String) entry.getKey(),
						(String) entry.getValue()));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(data,
					"UTF-8");
			formEntity.setContentType("application/x-www-form-urlencoded");
			post.setEntity(formEntity);
		}
		HttpResponse response;
		try {
			
			response = httpClient.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			return result;
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}
	
	
	
	public static String sendPostRequestold(String url, Map<String, String> params)
			throws IOException {
		String result = null;
		HttpResponse response = null;
		@SuppressWarnings({ "resource" })
		DefaultHttpClient httpclient = new DefaultHttpClient();
		boolean sign = true;
		if (sign) {
			url += (url.indexOf("?") == -1 ? "?" : "&") + "_timestamp="
					+ System.currentTimeMillis();
			url += "&_signature=" + HmacUtils.encode(url);
		}
		HttpPost httpPost = new HttpPost(url);
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (params != null && !params.isEmpty()) {
				Set<Entry<String, String>> entrys = params.entrySet();
				for (Map.Entry<String, String> entry : entrys) {
					nvps.add(new BasicNameValuePair(entry.getKey(), URLDecoder
							.decode(entry.getValue(), "UTF-8")));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			httpPost.setHeader("Cookie",
					"JSESSIONID=" + params.get("sessionId"));
			response = httpclient.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("status:"+response.getStatusLine().getStatusCode());
        String text = EntityUtils.toString(response.getEntity());
        System.out.println("text:"+text);
		if (response.getStatusLine().getStatusCode() == 200
				|| response.getStatusLine().getStatusCode() == 207) {
//			String temp = EntityUtils.toString(response.getEntity());
//			  if (temp.length() > 0) {
//			      result = temp.trim().toString();
//			  }else{
//				result="207";
//			  }
		} else {
			
		}
		return result;
	}
	
	
	/**
	  * @author Administrator
	  * @功能:HttpUrlConnection download
	  */
	public static void download(String httpurl,String path){
		System.out.println("start:"+new SimpleDateFormat("MM-dd:HH:mm:ss:SS").format(new Date()));
		try {
			URL url = new URL(httpurl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Charset","UTF-8");
			connection.setReadTimeout(10*1000);
			connection.setConnectTimeout(10*1000);
			connection.connect();
			int file_leng = connection.getContentLength();
			System.out.println("file length---->"+file_leng);
			//BufferedInputStream bin = new BufferedInputStream(connection.getInputStream());
			InputStream  bin=connection.getInputStream();
			File file = new File(path);
			if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
			}
			OutputStream out=new FileOutputStream(file);
			int size=0;
			int len=0;
			byte[] buf = new byte[1024];
			while((size=bin.read(buf))!=-1){
				len+=size;
				out.write(buf,0,size);
				/* android 中为了更新进度条
				Message msg = handler.obtainMessage();
				msg.arg1=len*100/file_leng;
				handler.sendMessage(msg);
				*/
				//Thread.sleep(1000);
				System.out.println("下载了： "+len*100/file_leng+"%\n");
				}
			bin.close();
			out.close();
			System.out.println("end:"+new SimpleDateFormat("MM-dd:HH:mm:ss:SS").format(new Date()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch(SocketException e){
			e.printStackTrace();
		}
		catch(SocketTimeoutException e){
			System.out.println("下载超时");
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} 
	}
	
	
	
}