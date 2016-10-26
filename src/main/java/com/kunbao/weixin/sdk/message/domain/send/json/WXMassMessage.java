package com.kunbao.weixin.sdk.message.domain.send.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kunbao.weixin.sdk.message.domain.send.json.metadata.TemplateData;
import com.kunbao.weixin.sdk.message.domain.send.json.metadata.TemplateInfo;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class WXMassMessage {
    public WXMassMessage(List<String> openIds,String mediaId,String msgType){
        this.toUser = openIds;
        this.mpNews = new HashMap<String, String>();
        mpNews.put("media_id",mediaId);
        this.msgType = msgType;
    }

    @JsonProperty("touser")
    private List<String> toUser;

    @JsonProperty("mpnews")
    private Map<String,String> mpNews;

    @JsonProperty("msgtype")
    private String msgType;

}
