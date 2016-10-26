package com.kunbao.weixin.sdk.message.domain.send.json.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Created by baylor on 15/8/5.
 */
@Getter
public class TemplateData {
    public TemplateData(String field, String value, String color) {
        this.field = field;
        this.value = value;
        this.color = color;
    }

    private String field;

    @JsonProperty("value")
    private String value;

    @JsonProperty("color")
    private String color;

}
