package com.kunbao.weixin.sdk.ticket;

import com.kunbao.weixin.sdk.base.WXHttpDispatch;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.ticket.request.WXJsApiTicketRequest;
import com.kunbao.weixin.sdk.ticket.response.WXJsApiTicketResponse;
import com.kunbao.weixin.sdk.token.WXTokenController;

/**
 * Created by baylor on 15/8/12.
 */
public class WXTicketService {

    private static WXJsApiTicketResponse jsApiTicketResponse;
    public WXJsApiTicketResponse getWXJsApiTicketResponse() throws WXException {
        if(jsApiTicketResponse == null || !jsApiTicketResponse.isValid()) {
            WXJsApiTicketRequest request = new WXJsApiTicketRequest(WXTokenController.getToken());
            jsApiTicketResponse = (WXJsApiTicketResponse) WXHttpDispatch.execute(request);
        }
        return jsApiTicketResponse;
    }
}
