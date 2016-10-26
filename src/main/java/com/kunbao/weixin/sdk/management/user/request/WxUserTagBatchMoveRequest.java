package com.kunbao.weixin.sdk.management.user.request;

import com.kunbao.weixin.sdk.base.domain.constant.WXHTTPMethod;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import com.kunbao.weixin.sdk.management.user.domain.WxUserTagBatchMove;
import com.kunbao.weixin.sdk.util.WXJsonUtil;

/**
 * User : hongze.zhu
 * Date : 16/5/16
 * Time : 下午3:14
 * Seriously, you should say something about your bullshit
 */
public class WxUserTagBatchMoveRequest extends WXRequest<WXJsonResponse> {

    public WxUserTagBatchMoveRequest(String token, WxUserTagBatchMove batchMove) throws WXException {
        super();
        this.method = WXHTTPMethod.POST;
        this.path = "/cgi-bin/tags/members/batchtagging";
        this.addParameter("access_token", token);
        this.body = WXJsonUtil.beanToJson(batchMove);
    }

    @Override
    public WXJsonResponse createResponse(String body) throws WXException {
        return WXJsonUtil.jsonToBean(body, WXJsonResponse.class);
    }
}
