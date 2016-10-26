package com.kunbao.weixin.sdk.management.oauth2.request;


import com.kunbao.weixin.sdk.base.domain.constant.WXBaseUrl;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.management.oauth2.response.WXOAuthResponse;
import com.kunbao.weixin.sdk.util.WXJsonUtil;

/**
 * Created by baylor on 15/7/25.
 */

/**
 * 获取第二步的refresh_token后，请求以下链接获取access_token：
 https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
 appid	是	公众号的唯一标识
 grant_type	是	填写为refresh_token
 refresh_token	是	填写通过access_token获取到的refresh_token参数
 */
public class WXOAuthRefreshRequest extends WXRequest<WXOAuthResponse> {

    private final static String GRANT_TYPE_DEFAULT = "refresh_token";

    public WXOAuthRefreshRequest(String appId, String refresh_token) {
        super(WXBaseUrl.COMMON);
        this.path = "/sns/oauth2/refresh_token";
        this.addParameter("grant_type", GRANT_TYPE_DEFAULT);
        this.addParameter("appid", appId);
        this.addParameter("refresh_token", refresh_token);
    }

    @Override
    public WXOAuthResponse createResponse(String body) throws WXException {
        return WXJsonUtil.jsonToBean(body, WXOAuthResponse.class);
    }
}
