package com.kunbao.weixin.sdk.message.domain.send.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kunbao.weixin.sdk.message.domain.send.json.metadata.TemplateData;
import com.kunbao.weixin.sdk.message.domain.send.json.metadata.TemplateInfo;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by baylor on 15/8/5.
 */
//{
//        "touser":"OPENID",
//        "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
//        "url":"http://weixin.qq.com/download",
//        "topcolor":"#FF0000",
//        "data":{
//        "first": {
//        "value":"恭喜你购买成功！",
//        "color":"#173177"
//        },
//        "keynote1":{
//        "value":"巧克力",
//        "color":"#173177"
//        },
//        "keynote2": {
//        "value":"39.8元",
//        "color":"#173177"
//        },
//        "keynote3": {
//        "value":"2014年9月16日",
//        "color":"#173177"
//        },
//        "remark":{
//        "value":"欢迎再次购买！",
//        "color":"#173177"
//        }
//        }
//}
@Getter
public class WXTemplateMessage {
    public WXTemplateMessage(String toUser, TemplateInfo templateInfo){
        this.toUser = toUser;
        this.templateId = templateInfo.getTemplateId();
        this.url = templateInfo.getUrl();
        this.topColor = templateInfo.getTopColor();

        data = new HashMap<String, TemplateData>();
    }

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("url")
    private String url;

    @JsonProperty("topcolor")
    private String topColor;

    @JsonProperty("data")
    private Map<String,TemplateData> data;

    public void addData(String field, TemplateData dataEntry){
        this.data.put(field, dataEntry);
    }
}
