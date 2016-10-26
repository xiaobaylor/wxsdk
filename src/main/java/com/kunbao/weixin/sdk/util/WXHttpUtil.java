package com.kunbao.weixin.sdk.util;

import com.kunbao.weixin.sdk.base.exception.WXException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.nio.CharBuffer;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by lemon_bar on 15/7/7.
 */
@Slf4j
public class WXHttpUtil {

    private final static String DEFAULT_CHARACTER_SET = "UTF-8";

    public static CloseableHttpClient createSSLInsecureClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
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
        return HttpClients.createDefault();
    }

    public static CloseableHttpClient createSSLInsecureClient(int timeout) {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout)
                    .setSocketTimeout(timeout).build();

            return HttpClients.custom().setDefaultRequestConfig(config).setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static CloseableHttpClient createClient(String url) {
        CloseableHttpClient client = null;
        if (url.startsWith("https")) {
            client = createSSLInsecureClient();
        } else {
            client = HttpClients.createDefault();
        }

        return client;
    }

    public static CloseableHttpClient createClient(String url, int timeout) {
        CloseableHttpClient client = null;
        if (url.startsWith("https")) {
            client = createSSLInsecureClient(timeout);
        } else {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout)
                    .setSocketTimeout(timeout).build();
            client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        }

        return client;
    }

    public static String doDownload(String URL, String filePath) throws WXException {

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        try {
            client = createClient(URL);
            HttpGet req = new HttpGet(URL);
            response = client.execute(req);
            HttpEntity resEntity = response.getEntity();
            for (org.apache.http.Header header : response.getAllHeaders()) {
                if (header.getName().equals("Content-Type") && header.getValue().equals("image/jpeg")) {
                    InputStream in = resEntity.getContent();
                    FileOutputStream fout = null;
                    try {
                        filePath += System.currentTimeMillis() + ".jpg";
                        fout = new FileOutputStream(new File(filePath));
                        int l = -1;
                        byte[] tmp = new byte[1024];
                        while ((l = in.read(tmp)) != -1) {
                            fout.write(tmp, 0, l);
                            // 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试
                        }
                        fout.flush();
                        fout.close();
                        return "{\"errcode\":0,\"errmsg\":\"success\", \"filePath\":\"" + filePath + "\"}";
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        if(fout != null){
                            fout.close();
                        }
                    }
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new WXException(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String doGet(String URL) throws WXException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        try {
            client = createClient(URL);
            HttpGet req = new HttpGet(URL);
            response = client.execute(req);
            HttpEntity resEntity = response.getEntity();

            return EntityUtils.toString(resEntity, DEFAULT_CHARACTER_SET);
        } catch (IOException e) {
            e.printStackTrace();
            throw new WXException(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String doPost(String URL, String body) throws WXException {
        log.debug("doPost in WXHttpUtil URL {} body {}", URL, body);
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = createClient(URL);

            HttpPost req = new HttpPost(URL);
            req.setEntity(new StringEntity(body, DEFAULT_CHARACTER_SET));
            log.debug("doPost in WXHttpUtil req {}", req);
            response = client.execute(req);
            log.debug("doPost in WXHttpUtil response {}", response);
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity, DEFAULT_CHARACTER_SET);
        } catch (IOException e) {
            throw new WXException(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String doPostXml(String URL, String body) throws WXException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = createClient(URL);
            HttpPost req = new HttpPost(URL);
            req.addHeader("Content-Type", "text/xml");
            req.setEntity(new StringEntity(body, DEFAULT_CHARACTER_SET));
            response = client.execute(req);
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity, DEFAULT_CHARACTER_SET);
        } catch (IOException e) {
            e.printStackTrace();
            throw new WXException(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String doUpload(String URL, String file) throws WXException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = createClient(URL);
            HttpPost req = new HttpPost(URL);
            FileBody fileBody = new FileBody(new File(file));
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("media", fileBody)
                    .build();
            req.setEntity(reqEntity);
            response = client.execute(req);
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity, DEFAULT_CHARACTER_SET);
        } catch (IOException e) {
            throw new WXException(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String doGet(String URL, int timeout) throws WXException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        try {
            client = createClient(URL, timeout);
            HttpGet req = new HttpGet(URL);
            log.debug("set time out {}", timeout);
            response = client.execute(req);
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity, DEFAULT_CHARACTER_SET);
        } catch (IOException e) {
            e.printStackTrace();
            throw new WXException(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
