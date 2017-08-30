package com.baicai.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.baicai.model.HomeCarousel;
import com.baicai.model.HomeHall3d;
import com.baicai.utils.CallBackUtils;
import com.jfinal.core.Controller;

public class CallBackController extends Controller{
	
	
	public void index() {
		renderNull();
	}
	
	@SuppressWarnings({ "finally" })
	public String executeGet(String url) {
		BufferedReader in = null;

		String content = null;
		try {
			// 定义HttpClient
			@SuppressWarnings("resource")
			DefaultHttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return content;
		}
	}

	public String GetPostBody(InputStream is, int contentLen) {
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			byte[] message = new byte[contentLen];
			try {
				while (readLen != contentLen) {
					readLengthThisTime = is.read(message, readLen, contentLen - readLen);
					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}
					readLen += readLengthThisTime;
				}
				return new String(message);
			} catch (IOException e) {
			}
		}
		return "";
	}
	
	
	protected boolean VerifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody) throws NumberFormatException, IOException
	{
		boolean ret = false;	
		String autorizationInput = new String(request.getHeader("Authorization"));
		String pubKeyInput = request.getHeader("x-oss-pub-key-url");
		byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
		byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
		String pubKeyAddr = new String(pubKey);
		if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/") && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/"))
		{
			System.out.println("pub key addr must be oss addrss");
			return false;
		}
		String retString = executeGet(pubKeyAddr);
		retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
		retString = retString.replace("-----END PUBLIC KEY-----", "");
		String queryString = request.getQueryString();
		String uri = request.getRequestURI();
		String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
		String authStr = decodeUri;
		if (queryString != null && !queryString.equals("")) {
			authStr += "?" + queryString;
		}
		authStr += "\n" + ossCallbackBody;
		ret = doCheck(authStr, authorization, retString);
		return ret;
	}
	
	public static boolean doCheck(String content, byte[] sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
			signature.initVerify(pubKey);
			signature.update(content.getBytes());
			boolean bverify = signature.verify(sign);
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private void response(HttpServletRequest request, HttpServletResponse response, String results, int status) throws IOException {
		String callbackFunName = request.getParameter("callback");
		response.addHeader("Content-Length", String.valueOf(results.length()));
		if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( " + results + " )");
		response.setStatus(status);
		response.flushBuffer();
	}
	
	public void callBack() throws NumberFormatException, IOException, JSONException {
		String ossCallbackBody = GetPostBody(getRequest().getInputStream(), Integer.parseInt(getRequest().getHeader("content-length")));
		boolean ret = VerifyOSSCallbackRequest(getRequest(), ossCallbackBody);
		boolean flag = false;
		System.out.println("verify result:" + ret);
		System.out.println("OSS Callback Body:" + ossCallbackBody);

		if (ret)
		{
	    	JSONObject obj = new JSONObject(ossCallbackBody);
			int type = obj.optInt("type");
			if(type==1||type==2||type==7||type==8||type==9||type==10||type==11||type==12||type==13||type==14||type==15){
				flag = CallBackUtils.updateBasic(obj);
			}else if(type==3){
				flag = CallBackUtils.updateConstruct(obj);
			}else if(type==4){
				flag = CallBackUtils.updateReal(obj);
			}else if(type==5){
				flag = CallBackUtils.update3D(obj);
			}
			response(getRequest(), getResponse(), "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
			renderNull();
		}
		else
		{
			response(getRequest(), getResponse(), "{\"Status\":\"verdify not ok\"}", HttpServletResponse.SC_BAD_REQUEST);
			render("-100");
		}
	}
	
	public void callBackOrder() throws NumberFormatException, IOException, JSONException {
		String ossCallbackBody = GetPostBody(getRequest().getInputStream(), Integer.parseInt(getRequest().getHeader("content-length")));
		boolean ret = VerifyOSSCallbackRequest(getRequest(), ossCallbackBody);
		boolean flag = false;
		System.out.println("verify result:" + ret);
		System.out.println("OSS Callback Body:" + ossCallbackBody);

		if (ret)
		{
	    	JSONObject obj = new JSONObject(ossCallbackBody);
//	    	String remark = getSessionAttr("remark");
//			System.out.println("call_session:"+remark);
			flag = CallBackUtils.updateOrder(obj);
			response(getRequest(), getResponse(), "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
			renderNull();
		}
		else
		{
			response(getRequest(), getResponse(), "{\"Status\":\"verdify not ok\"}", HttpServletResponse.SC_BAD_REQUEST);
			render("-100");
		}
	}
	
}
