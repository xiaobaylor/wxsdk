package com.kunbao.weixin.sdk.media;

import com.kunbao.weixin.sdk.base.WXHttpDispatch;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.media.request.WXMediaRequest;
import com.kunbao.weixin.sdk.media.response.WXMediaResponse;
import com.kunbao.weixin.sdk.token.WXTokenController;

/**
 * Created by baylor on 15/8/12.
 */
public class WXMediaService {
    public WXMediaResponse getMediaResponse(String filePath, String mediaId) throws WXException {
        WXMediaRequest request = new WXMediaRequest(WXTokenController.getToken(), filePath, mediaId);
        WXMediaResponse response = (WXMediaResponse) WXHttpDispatch.execute(request);
        return response;
    }
}
