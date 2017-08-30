package com.baicai.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.Callback.CalbackBodyType;
import com.jfinal.upload.UploadFile;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class CommonUtils {
	
	public static String url="http://gw.api.taobao.com/router/rest";
	public static String appkey="23826330";
	public static String secret="580392ccdff485e3030c90e76b71953a";
	
	public static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
	public static String accessKeyId = "LTAIpTX4JcsrVsV3";
	public static String accessKeySecret = "9h15xkwMukmQtuRfLNQwYbZ0GnP4sp";
	public static String callbackUrl = "http://120.25.62.161/callBack/callBack";
	public static String callbackOrderUrl = "http://120.25.62.161/callBack/callBackOrder";
	
	public static String makeCode(){
		String str = "";
		str += (int)(Math.random()*9+1);
		for(int i = 0; i < 5; i++){
			str += (int)(Math.random()*10);
		}
		System.out.println(str);
		return str;
	}
	
	//发送短信验证码并返回验证码
		public static String alidayuCode(String mobile) throws ApiException {
			String codeString;
			String code = CommonUtils.makeCode();
			codeString = "{" + "\"code\":\"" + code + "\"" + "}";
			TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			
		//	req.setExtend("123456");          //公共回传参数 
			req.setSmsType("normal");         //短信类型，传入值请填写normal
			req.setSmsFreeSignName("白菜适家"); //短信签名
			req.setSmsParamString(codeString);    //短信模板变量
			req.setRecNum(mobile);     //短信接收号码
			req.setSmsTemplateCode("SMS_67185693");    //短信模板ID
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			System.out.println("111");
			System.out.println(rsp.getBody());
			return code;
		}

	//自动注册
		public static void alidayuPsd(String mobile,String psd) throws ApiException {
			String codeString;
			codeString = "{" + "\"phone\":\"" + mobile + "\"" + ",\"password\":\"" + psd + "\"" + "}";
			System.out.println(codeString);
			TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			
		//	req.setExtend("123456");          //公共回传参数 
			req.setSmsType("normal");         //短信类型，传入值请填写normal
			req.setSmsFreeSignName("白菜适家"); //短信签名
			req.setSmsParamString(codeString);    //短信模板变量
			req.setRecNum(mobile);     //短信接收号码
			req.setSmsTemplateCode("SMS_67570113");    //短信模板ID
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			System.out.println(rsp.getBody());
		}

	//预约提醒
		public static void alidayuTip(String mobile,String user) throws ApiException {
			String codeString;
			SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");//设置日期格式
			codeString = "{" + "\"phone\":\"" + user + "\"" + ",\"time\":\"" + df.format(new Date()) + "\"" + "}";
			System.out.println(codeString);
			TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			
		//	req.setExtend("123456");          //公共回传参数 
			req.setSmsType("normal");         //短信类型，传入值请填写normal
			req.setSmsFreeSignName("白菜适家"); //短信签名
			req.setSmsParamString(codeString);    //短信模板变量
			req.setRecNum(mobile);     //短信接收号码
			req.setSmsTemplateCode("SMS_67725079");    //短信模板ID
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			System.out.println("111");
			System.out.println(rsp.getBody());
		}

    //生成随机数字和字母,  
    public static String getStringRandom() {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < 8; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母
                int temp = 65;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        System.err.println(val);
        return val;  
    }  
    
    public static String getDate() {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return formatter.format(new Date());
    }
    
    
	public static void getImg(UploadFile uFile, int type) throws IOException {
	
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new Date());
		//String url = "http://cxqtest.oss-cn-shenzhen.aliyuncs.com/chen/1.jpg";
	    try {
	        PutObjectRequest putObjectRequest = new PutObjectRequest("cxqtest", date+".jpg", 
	        		new File(uFile.getUploadPath(),uFile.getFileName())); 
	        Callback callback = new Callback();	       
	        putObjectRequest.setCallback(callback);
	        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
	        byte[] buffer = new byte[1024];
	        putObjectResult.getCallbackResponseBody().read(buffer);
	        putObjectResult.getCallbackResponseBody().close();
	        System.out.println("OK");
			File file = new File(uFile.getUploadPath(),uFile.getFileName());
			file.delete();	
			
	    } catch (OSSException oe) {
	        System.out.println("Caught an OSSException, which means your request made it to OSS, "
	                + "but was rejected with an error response for some reason.");
	        System.out.println("Error Message: " + oe.getErrorCode());
	        System.out.println("Error Code:       " + oe.getErrorCode());
	        System.out.println("Request ID:      " + oe.getRequestId());
	        System.out.println("Host ID:           " + oe.getHostId());
	    } catch (ClientException ce) {
	        System.out.println("Caught an ClientException, which means the client encountered "
	                + "a serious internal problem while trying to communicate with OSS, "
	                + "such as not being able to access the network.");
	        System.out.println("Error Message: " + ce.getMessage());
	    } finally {
	        ossClient.shutdown();
	    }
	}
}


