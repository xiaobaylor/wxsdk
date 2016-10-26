package com.kunbao.weixin.sdk.management.menu.request;

import com.kunbao.weixin.sdk.base.domain.constant.WXHTTPMethod;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import com.kunbao.weixin.sdk.management.menu.domain.Menu;
import com.kunbao.weixin.sdk.util.WXJsonUtil;

/**
 * Created by lixingdong on 16/5/3.
 */
public class WXMenuAddconditionalRequest extends WXRequest<WXJsonResponse> {
    public WXMenuAddconditionalRequest(String token, String menu) throws WXException {
        super();
        this.method = WXHTTPMethod.POST;
        this.path = "/cgi-bin/menu/addconditional";
        this.addParameter("access_token",token);
        this.body = menu;
    }
    @Override
    public WXJsonResponse createResponse(String body) throws WXException {
        return WXJsonUtil.jsonToBean(body, WXJsonResponse.class);
    }
}

