package com.kunbao.weixin.sdk.media.request;

import com.kunbao.weixin.sdk.base.domain.constant.WXBaseUrl;
import com.kunbao.weixin.sdk.base.domain.constant.WXHTTPMethod;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.request.WXRequest;
import com.kunbao.weixin.sdk.media.response.WXMediaResponse;
import com.kunbao.weixin.sdk.util.WXJsonUtil;

import java.io.*;

/**
 * Created by baylor on 15/8/12.
 */
//http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
public class WXMediaRequest extends WXRequest<WXMediaResponse> {

    public WXMediaRequest(String accessToken, String filePath, String mediaId){
        super(WXBaseUrl.FILE);
        this.path = "/cgi-bin/media/get";
        this.method = WXHTTPMethod.DOWNLOAD;
        this.filePath = filePath;

        this.addParameter("access_token", accessToken);
        this.addParameter("media_id", mediaId);
    }

    @Override
    public WXMediaResponse createResponse(String body) throws WXException {
        return WXJsonUtil.jsonToBean(body, WXMediaResponse.class);
    }
}
