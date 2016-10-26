package com.kunbao.weixin.sdk.ticket.request;

import com.kunbao.weixin.sdk.base.domain.constant.WXHTTPMethod;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.ticket.response.WXJsApiTicketResponse;
import com.kunbao.weixin.sdk.util.WXJsonUtil;

/**
 * Created by baylor on 15/8/12.
 */
public class WXJsApiTicketRequest extends WXRequest<WXJsApiTicketResponse> {
    public WXJsApiTicketRequest(String token) {
        this.method = WXHTTPMethod.GET;
        this.path = "/cgi-bin/ticket/getticket";
        this.addParameter("access_token", token);
        this.addParameter("type", "jsapi");
    }

    @Override
    public WXJsApiTicketResponse createResponse(String body) throws WXException {
        return WXJsonUtil.jsonToBean(body, WXJsApiTicketResponse.class);
    }
}
