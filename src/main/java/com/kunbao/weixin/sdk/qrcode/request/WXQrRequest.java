package com.kunbao.weixin.sdk.qrcode.request;

import com.kunbao.weixin.sdk.base.domain.constant.WXHTTPMethod;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.qrcode.response.WXQrResponse;
import com.kunbao.weixin.sdk.util.WXJsonUtil;

/**
 * Created by baylor on 15/8/11.
 */
//永久二维码请求说明
//
//http请求方式: POST
//URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
//POST数据格式：json
//POST数据例子：{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
//或者也可以使用以下POST数据创建字符串形式的二维码参数：
//{"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "123"}}}

//正确的Json返回结果:
//
// {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==",
// "expire_seconds":60,
// "url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}

public class WXQrRequest extends WXRequest<WXQrResponse> {

    public WXQrRequest(String token, String scene_str){
        super();
        this.method = WXHTTPMethod.POST;
        this.path = "/cgi-bin/qrcode/create";
        this.addParameter("access_token", token);
        this.body = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""+scene_str+"\"}}}";
    }

    @Override
    public WXQrResponse createResponse(String body) throws WXException {
        return WXJsonUtil.jsonToBean(body, WXQrResponse.class);
    }
}
