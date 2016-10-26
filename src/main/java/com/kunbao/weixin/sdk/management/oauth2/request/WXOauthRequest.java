package com.kunbao.weixin.sdk.management.oauth2.request;


import com.kunbao.weixin.sdk.base.domain.constant.WXBaseUrl;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.management.oauth2.response.WXOAuthResponse;
import com.kunbao.weixin.sdk.util.WXJsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by baylor on 15/7/25.
 */
@Slf4j
public class WXOAuthRequest extends WXRequest<WXOAuthResponse> {

    private final static String GRANT_TYPE_DEFAULT = "authorization_code";

    public WXOAuthRequest(String appId, String appSecret, String code) {
        super(WXBaseUrl.COMMON);
        this.path = "/sns/oauth2/access_token";
        this.addParameter("grant_type", GRANT_TYPE_DEFAULT);
        this.addParameter("appid", appId);
        this.addParameter("secret", appSecret);
        this.addParameter("code", code);
    }

    @Override
    public WXOAuthResponse createResponse(String body) throws WXException {
        log.debug("body is {}", body);
        WXOAuthResponse resp = WXJsonUtil.jsonToBean(body, WXOAuthResponse.class);
        if(!resp.isSuccess()) {
            log.error("OAuthResponse auth code is {}",this.getParameters().get("code"));
        }
        return resp;
    }
}
