package com.kunbao.weixin.sdk.message.domain.send.json.metadata;

import lombok.Getter;

/**
 * Created by baylor on 15/8/5.
 */
@Getter
public class TemplateInfo {
    public TemplateInfo(String templateId, String url, String topColor){
        this.templateId = templateId;
        this.url = url;
        this.topColor = topColor;
    }
    private String templateId;
    private String url;
    private String topColor;
}
