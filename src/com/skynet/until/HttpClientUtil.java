package com.skynet.until;

/**
 * @Author: jianhu5
 * @Date: 2020/11/20 14:33
 */

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author : chenlinyan
 * @version : 2.0
 * @date : 2019/9/27 9:40
 */
public class HttpClientUtil {
    private final static String SUCCESS = "SUCCESS";
    private final static String FAIL = "FAIL";

    /**
     * 通过post方式调用http接口
     *
     * @param url       url路径
     * @param jsonParam json格式的参数
     * @param reSend    重发次数
     * @return
     * @throws Exception
     */
    public static String sendPostByJson(String url, String jsonParam, int reSend, String token) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            Header header = new BasicHeader("Accept-Encoding", null);
            httpPost.setHeader(header);
            if (StringUtils.isNotBlank(token)) {
                httpPost.setHeader(new BasicHeader("Cookie", token));
            }
            // 设置报文和通讯格式
            if (null != jsonParam) {
                StringEntity stringEntity = new StringEntity(jsonParam, HttpConstant.UTF8_ENCODE);
                stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
                stringEntity.setContentType(HttpConstant.APPLICATION_JSON);
                httpPost.setEntity(stringEntity);
            }
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            //临时添加
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                Header[] headers = httpResponse.getHeaders("Set-Cookie");
                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].getValue().indexOf("skynet_token") >= 0) {
                        return headers[i].getValue();
                    }
                }
            }
            //end
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
            if (reSend > 0) {
                result = sendPostByJson(url, jsonParam, reSend - 1, token);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
        }
        //请求接口的响应时间
        endTime = System.currentTimeMillis();
        return result;

    }

    /**
     * 通过post方式调用http接口
     *
     * @param url       url路径
     * @param jsonParam json格式的参数
     * @param reSend    重发次数
     * @return
     * @throws Exception
     */
    public static String sendPutByJson(String url, String jsonParam, int reSend, String token) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = HttpClientFactory.getInstance().getHttpClient();
        try {
            // 创建连接
            HttpPut httpPut = new HttpPut(url);
            // 设置请求头和报文
            Header header = new BasicHeader("Accept-Encoding", null);
            httpPut.setHeader(header);
            if (StringUtils.isNotBlank(token)) {
                httpPut.setHeader(new BasicHeader("Cookie", token));
            }
            // 设置报文和通讯格式
            if (null != jsonParam) {
                StringEntity stringEntity = new StringEntity(jsonParam, HttpConstant.UTF8_ENCODE);
                stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
                stringEntity.setContentType(HttpConstant.APPLICATION_JSON);
                httpPut.setEntity(stringEntity);
            }
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPut);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
        }
        //请求接口的响应时间
        endTime = System.currentTimeMillis();
        return result;

    }

    /**
     * 通过post方式调用http接口
     *
     * @param url    url路径
     * @param map    json格式的参数
     * @param reSend 重发次数
     * @return
     * @throws Exception
     */
    public static String sendPostByForm(String url, Map<String, String> map, int reSend) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        UrlEncodedFormEntity entity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            entity = new UrlEncodedFormEntity(list, HttpConstant.UTF8_ENCODE);
            httpPost.setEntity(entity);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            if (reSend > 0) {
                result = sendPostByForm(url, map, reSend - 1);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
        }
        //请求接口的响应时间
        endTime = System.currentTimeMillis();
        return result;

    }

    /**
     * 通过post方式调用http接口
     *
     * @param url      url路径
     * @param xmlParam json格式的参数
     * @param reSend   重发次数
     * @return
     * @throws Exception
     */
    public static String sendPostByXml(String url, String xmlParam, int reSend) {
        //声明返回结果
        String result = "";
        //开始请求API接口时间
        long startTime = System.currentTimeMillis();
        //请求API接口的响应时间
        long endTime = 0L;
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        try {
            // 创建连接
            httpClient = HttpClientFactory.getInstance().getHttpClient();
            // 设置请求头和报文
            HttpPost httpPost = HttpClientFactory.getInstance().httpPost(url);
            StringEntity stringEntity = new StringEntity(xmlParam, HttpConstant.UTF8_ENCODE);
            stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
            stringEntity.setContentType(HttpConstant.TEXT_XML);
            httpPost.setEntity(stringEntity);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, HttpConstant.UTF8_ENCODE);
        } catch (Exception e) {
            if (reSend > 0) {
                result = sendPostByJson(url, xmlParam, reSend - 1, null);
                if (result != null && !"".equals(result)) {
                    return result;
                }
            }
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
            }
            //请求接口的响应时间
            endTime = System.currentTimeMillis();
            return result;
        }

    }


    public static String upload(String token, String url, String path) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();

            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Cookie", token);
            // 把文件转换成流对象FileBody
            FileBody bin = new FileBody(new File(path));
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("files", bin).build();
            httpPost.setEntity(reqEntity);

            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            System.out.println("The response value of token:" + response.getFirstHeader("token"));
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity, HttpConstant.UTF8_ENCODE);
            // 销毁
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
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
    }
}