package com.kunbao.weixin.sdk.base;

import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import com.kunbao.weixin.sdk.base.response.WXJsonResponseChecker;
import com.kunbao.weixin.sdk.base.response.WXResponse;
import com.kunbao.weixin.sdk.media.request.WXMediaRequest;
import com.kunbao.weixin.sdk.util.WXHttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by lemon_bar on 15/7/7.
 */
@Slf4j
public class WXHttpDispatch {
    private final static String CharSet = "UTF-8";

    private final static String URL_FORMAT = "%s?%s";

    public static WXResponse execute(WXRequest request) throws WXException {
        WXResponse wxResponse = null;
        switch (request.getMethod()) {
            case GET:
                wxResponse = doGet(request);
                break;
            case POST:
                wxResponse = doPost(request);
                break;
            case UPLOAD:
                wxResponse = doUpload(request);
                break;
            case XML:
                wxResponse = doPostXml(request);
                break;
            case DOWNLOAD:
                wxResponse = doDownload((WXMediaRequest) request);
                break;
            default:
                throw new WXException("undefined request method.");
        }
        if (wxResponse == null) {
            throw new WXException("response is null");
        }
        if (wxResponse instanceof WXJsonResponse) {
            WXJsonResponseChecker.check((WXJsonResponse) wxResponse);
        }
        return wxResponse;
    }

    private static WXResponse doGet(WXRequest request) throws WXException {
        String url = String.format(URL_FORMAT,
                request.getUrl(),
                constructQuery(request.getParameters(), request.getCharSet()));
        return request.createResponseHandler(WXHttpUtil.doGet(url));
    }

    private static WXResponse doPost(WXRequest request) throws WXException {
        log.debug("request {}", request);
        String url = String.format(URL_FORMAT,
                request.getUrl(),
                constructQuery(request.getParameters(), request.getCharSet()));
        return request.createResponseHandler(WXHttpUtil.doPost(url, request.getBody()));
    }

    private static WXResponse doPostXml(WXRequest request) throws WXException {
        String url = String.format(URL_FORMAT,
                request.getUrl(),
                constructQuery(request.getParameters(), request.getCharSet()));
        return request.createResponseHandler(WXHttpUtil.doPostXml(url, request.getBody()));
    }

    private static WXResponse doUpload(WXRequest request) throws WXException {
        String url = String.format(URL_FORMAT,
                request.getUrl(),
                constructQuery(request.getParameters(), request.getCharSet()));
        return request.createResponseHandler(WXHttpUtil.doUpload(url, request.getFilePath()));
    }

    private static WXResponse doDownload(WXMediaRequest request) throws WXException {
        String url = String.format(URL_FORMAT,
                request.getUrl(),
                constructQuery(request.getParameters(), request.getCharSet()));
        return request.createResponseHandler(WXHttpUtil.doDownload(url, request.getFilePath()));
    }

    private static String constructQuery(Map<String, String> parameters){
        return constructQuery(parameters, CharSet);
    }

    private static String constructQuery(Map<String, String> parameters, String charSet) {
        if(charSet == null){
            charSet = CharSet;
        }

        try {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                if(entry.getValue() == null) {
                    continue;
                }
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(URLEncoder.encode(entry.getValue(), charSet));
                sb.append("&");
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static WXResponse execute(WXRequest request, int timeout) throws WXException {
        WXResponse wxResponse = null;
        switch (request.getMethod()) {
            case GET:
                wxResponse = doGet(request, timeout);
                break;
            case POST:
                wxResponse = doPost(request);
                break;
            case UPLOAD:
                wxResponse = doUpload(request);
                break;
            case XML:
                wxResponse = doPostXml(request);
                break;
            case DOWNLOAD:
                wxResponse = doDownload((WXMediaRequest) request);
                break;
            default:
                throw new WXException("undefined request method.");
        }
        if (wxResponse == null) {
            throw new WXException("response is null");
        }
        if (wxResponse instanceof WXJsonResponse) {
            WXJsonResponseChecker.check((WXJsonResponse) wxResponse);
        }
        return wxResponse;
    }

    private static WXResponse doGet(WXRequest request, int timeout) throws WXException {
        String url = String.format(URL_FORMAT,
                request.getUrl(),
                constructQuery(request.getParameters(), request.getCharSet()));
        return request.createResponseHandler(WXHttpUtil.doGet(url, timeout));
    }
}
