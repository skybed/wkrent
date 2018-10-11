package com.wkrent.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	
	private static Logger logger = Logger.getLogger(HttpUtils.class);

    public static String httpPost(String urlPaths, Object object, Integer connectionTimeOut, Integer readTimeOut) throws IOException {
        logger.info("正在进行请求：" + urlPaths + "请求数据：" + JSON.toJSONString(object));
    	StringBuffer sb = new StringBuffer("");
        String urlPath = new String(urlPaths);
        
        //建立连接
        URL url = new URL(urlPath);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        
        //设置参数
        httpConn.setDoOutput(true);   //需要输出
        httpConn.setDoInput(true);   //需要输入
        httpConn.setUseCaches(false);  //不允许缓存
        httpConn.setRequestMethod("POST");   //设置POST方式连接
        
        //设置请求属性  application/x-www-form-urlencoded
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        httpConn.setRequestProperty("Charset", "UTF-8");
        httpConn.setConnectTimeout(connectionTimeOut);
        httpConn.setReadTimeout(readTimeOut);
        
        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();
        
        //建立输入流，向指向的URL传入参数
        OutputStream outputStream = httpConn.getOutputStream();
        outputStream.write(JSONObject.toJSONString(object).getBytes());
        outputStream.flush();
        outputStream.close();
        
        //获得响应状态
        int resultCode = httpConn.getResponseCode();
        logger.info("请求返回码：" + resultCode);
        if(HttpURLConnection.HTTP_OK == resultCode) {
            BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            String readLine;
            while((readLine=responseReader.readLine()) != null){
                sb.append(readLine).append("\n");
            }
            responseReader.close();
        }
        logger.info("请求返回数据：" + sb);
        return sb.toString();
    }
    
    public static String httpPostByForm(String urlPaths, String object, Integer connectionTimeOut, Integer readTimeOut) throws IOException {
        logger.info("正在进行请求：" + urlPaths + "请求数据：" + JSON.toJSONString(object));
    	StringBuffer sb = new StringBuffer("");
        String urlPath = new String(urlPaths);
        
        //建立连接
        URL url = new URL(urlPath);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        
        //设置参数
        httpConn.setDoOutput(true);   //需要输出
        httpConn.setDoInput(true);   //需要输入
        httpConn.setUseCaches(false);  //不允许缓存
        httpConn.setRequestMethod("POST");   //设置POST方式连接
        
        //设置请求属性  application/x-www-form-urlencoded
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        httpConn.setRequestProperty("Charset", "UTF-8");
        httpConn.setConnectTimeout(connectionTimeOut);
        httpConn.setReadTimeout(readTimeOut);
        
        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();
        
        //建立输入流，向指向的URL传入参数
        OutputStream outputStream = httpConn.getOutputStream();
        outputStream.write(object.getBytes());
        outputStream.flush();
        outputStream.close();
        
        //获得响应状态
        int resultCode = httpConn.getResponseCode();
        logger.info("请求返回码：" + resultCode);
        if(HttpURLConnection.HTTP_OK == resultCode) {
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            String readLine;
            while((readLine=responseReader.readLine()) != null){
                sb.append(readLine).append("\n");
            }
            responseReader.close();
        }
        logger.info("请求返回数据：" + sb);
        return sb.toString();
    }
    
    public static String httpPostByJson(String urlPaths,String requestData, Integer connectionTimeOut, Integer readTimeOut) throws IOException {
        StringBuffer sb = new StringBuffer("");
        String urlPath = new String(urlPaths);
        
        //建立连接
        URL url = new URL(urlPath);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        
        //设置参数
        httpConn.setDoOutput(true);   //需要输出
        httpConn.setDoInput(true);   //需要输入
        httpConn.setUseCaches(false);  //不允许缓存
        httpConn.setRequestMethod("POST");   //设置POST方式连接
        
        //设置请求属性  application/x-www-form-urlencoded
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        httpConn.setRequestProperty("Charset", "UTF-8");
        httpConn.setConnectTimeout(connectionTimeOut);
        httpConn.setReadTimeout(readTimeOut);
        
        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();
        
        //建立输入流，向指向的URL传入参数
        OutputStream outputStream = httpConn.getOutputStream();
        outputStream.write(requestData.getBytes());
        outputStream.flush();
        outputStream.close();
        
        //获得响应状态
        int resultCode = httpConn.getResponseCode();
        if(HttpURLConnection.HTTP_OK == resultCode) {
            BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            String readLine;
            while((readLine=responseReader.readLine()) != null){
                sb.append(readLine).append("\n");
            }
            responseReader.close();
        }
        return sb.toString();
    }
    
    public static String httpGet(String urlPaths,Object object, Integer connectionTimeOut, Integer readTimeOut) throws IOException {
        StringBuffer sb = new StringBuffer("");
        String urlPath = new String(urlPaths);
        
        //建立连接
        URL url = new URL(urlPath);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        
        //设置参数
        httpConn.setDoOutput(true);   //需要输出
        httpConn.setDoInput(true);   //需要输入
        httpConn.setUseCaches(false);  //不允许缓存
        httpConn.setRequestMethod("GET");   //设置POST方式连接
        
        //设置请求属性  application/x-www-form-urlencoded
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        httpConn.setRequestProperty("Charset", "UTF-8");
        
        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();
        
        //建立输入流，向指向的URL传入参数
        OutputStream outputStream = httpConn.getOutputStream();
        outputStream.write(JSONObject.toJSONString(object).getBytes("utf-8"));
        outputStream.flush();
        outputStream.close();
        
        //获得响应状态
        int resultCode = httpConn.getResponseCode();
        if(HttpURLConnection.HTTP_OK == resultCode) {
            
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            String readLine;
            while((readLine=responseReader.readLine()) != null) {
                sb.append(readLine).append("\n");
            }
            responseReader.close();
        }
        return sb.toString();
    }
    
    public static String httpRequest(String url, String param, Integer connectionTimeOut, Integer readTimeOut) throws IOException {
        StringBuffer result = new StringBuffer("");
        BufferedReader in = null;
        String urlNameString = "";
        try {
        	if(param != null){
                urlNameString = url + "?" + param;
        	}else{
        		urlNameString = url;
        	}

            URL realUrl = new URL(urlNameString);
            
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(connectionTimeOut);
            connection.setReadTimeout(readTimeOut);
            
            // 建立实际的连接
            connection.connect();
            
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            	logger.error(e.getMessage(), e);
            }
        }
        return result.toString();
    }
    
}
