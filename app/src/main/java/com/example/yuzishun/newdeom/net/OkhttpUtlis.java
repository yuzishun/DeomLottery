package com.example.yuzishun.newdeom.net;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;


import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.MyApplication;
import com.example.yuzishun.newdeom.utils.SSL;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Created by yuzishun on 2018/11/22.
 */

public class OkhttpUtlis {
    private static OkhttpUtlis mInstance;
    private OkHttpClient mOkHttpClient;
    private String token;
    public OkhttpUtlis() {
        OkHttpClient.Builder ClientBuilder = new OkHttpClient.Builder();
        ClientBuilder.readTimeout(20, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(6, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(60, TimeUnit.SECONDS);//写入超时
        ClientBuilder.addInterceptor(new LoggingInterceptor());//日志拦截器
//        支持HTTPS请求，跳过证书验证
        ClientBuilder.sslSocketFactory(createSSLSocketFactory());
//        ClientBuilder.sslSocketFactory(new SSL(trustAllCert), trustAllCert);
        ClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
//                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
//                Boolean result = hv.verify("www.wannianjuke.com",session);
                return true;
            }
        });

        mOkHttpClient=ClientBuilder.build();
        SpUtil spUtil = new SpUtil(MyApplication.getContext(),"token");
        token = spUtil.getString("token","");
    }


    public void PostAsynMap(String url, HashMap<String,String> hashMap, Callback callback){



        Gson gson = new Gson();

        final String params =gson.toJson(hashMap);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, params);



        final Request request = new Request.Builder()
                .url(url)
                .removeHeader("User-Agent").addHeader("User-Agent","Android:"+getSystemModel())

                .addHeader("token", token)
                .post(body)

                .build();
        mOkHttpClient.newCall(request).enqueue(callback);


    }

    public void GetAsynMap(String url,Callback callback){


        Request request = new Request.Builder().addHeader("token", token).removeHeader("User-Agent").addHeader("User-Agent",getSystemModel()+"Android")
                .url(url)
                .get()

                .build();
        mOkHttpClient.newCall(request).enqueue(callback);



    }


    //定义一个信任所有证书的TrustManager
    final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     * @return
     */
    public SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }
    /**
     * 用于信任所有证书
     */
    class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }
    public class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以要什么都可以从这里拿
            Request request = chain.request();

            long t1 = System.nanoTime();//请求发起的时间


            Response response = chain.proceed(request);
            Log.e("LogOkHttp",String.format("发送请求 %s on %s%n%s",response.code(),response.headers(),
                    request.url(), chain.connection(), request.headers()));
            if(response.code()==200){

            }else {
                Looper.prepare();
                ToastUtil.showToast(MyApplication.getContext(),"网络错误Code值为:"+response.code());
                Looper.loop();
            }

            long t2 = System.nanoTime();//收到响应的时间

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，d需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
                Log.e("LogOkHttp",String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                        response.request().url(),
                        responseBody.string(),
                        (t2 - t1) / 1e6d,
                        response.headers()));

            return response;
        }
    }


}