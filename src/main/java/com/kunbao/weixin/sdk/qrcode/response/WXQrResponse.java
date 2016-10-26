package com.kunbao.weixin.sdk.qrcode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import lombok.Getter;

/**
 * Created by baylor on 15/8/11.
 */
@Getter
public class WXQrResponse extends WXJsonResponse {
    @JsonProperty("ticket")
    private String ticket;

    @JsonProperty("expire_seconds")
    private String expires;

    @JsonProperty("url")
    private String url;
}
