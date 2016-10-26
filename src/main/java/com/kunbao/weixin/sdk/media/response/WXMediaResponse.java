package com.kunbao.weixin.sdk.media.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import lombok.Getter;

/**
 * Created by baylor on 15/8/12.
 */
@Getter
public class WXMediaResponse extends WXJsonResponse {
    @JsonProperty("filePath")
    private String filePath;
}
