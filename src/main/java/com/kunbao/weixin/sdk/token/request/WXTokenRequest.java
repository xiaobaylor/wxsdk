package com.kunbao.weixin.sdk.token.request;

import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.token.response.WXTokenResponse;
import com.kunbao.weixin.sdk.util.WXJsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * http请求方式: GET
 * https://api.weixin.qq.com/cgi-bin/token
 * ?grant_type=client_credential
 * &appid=APPID
 * &secret=APPSECRET
 * Created by lemon_bar on 15/7/7.
 */
@Slf4j
public class WXTokenRequest extends WXRequest<WXTokenResponse> {

    private final static String GRANT_TYPE_DEFAULT = "client_credential";

    public WXTokenRequest(String appId, String appSecret) {
        super();
        this.path = "/cgi-bin/token";
        this.addParameter("grant_type", GRANT_TYPE_DEFAULT);
        this.addParameter("appid", appId);
        this.addParameter("secret", appSecret);
    }

    @Override
    public WXTokenResponse createResponse(String body) throws WXException {
        log.info("get token : {}", body);
        return WXJsonUtil.jsonToBean(body, WXTokenResponse.class);
    }
}
