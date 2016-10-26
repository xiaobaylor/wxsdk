package com.kunbao.weixin.sdk.message.request;

import com.kunbao.weixin.sdk.base.domain.constant.WXHTTPMethod;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import com.kunbao.weixin.sdk.message.domain.send.json.WXMassMessage;
import com.kunbao.weixin.sdk.message.domain.send.json.WXTemplateMessage;
import com.kunbao.weixin.sdk.util.WXJsonUtil;

/**
 * http请求方式: POST
 * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
 * Created by baylor on 15/8/5.
 */
public class WXMassMessageSendRequest extends WXRequest<WXJsonResponse> {
    private WXMassMessageSendRequest(String token) {
        super();
        this.method = WXHTTPMethod.POST;
        this.path = "/cgi-bin/message/mass/send";
        this.addParameter("access_token", token);
    }

    public WXMassMessageSendRequest(String token, WXMassMessage wxMassMessage) throws WXException {
        this(token);
        this.body = WXJsonUtil.beanToJson(wxMassMessage);
    }

    @Override
    public WXJsonResponse createResponse(String body) throws WXException {
        return WXJsonUtil.jsonToBean(body, WXJsonResponse.class);
    }
}
