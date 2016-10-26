package com.kunbao.weixin.sdk.management.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * User : hongze.zhu
 * Date : 16/5/16
 * Time : 下午3:12
 * Seriously, you should say something about your bullshit
 */
@Getter
public class WxUserTagBatchMove {
    @JsonProperty("openid_list")
    private List<String> openIdList;

    @JsonProperty("tagid")
    private int tagId;

    public WxUserTagBatchMove(List<String> openIdList, int tagId) {
        this.openIdList = openIdList;
        this.tagId = tagId;
    }

    protected WxUserTagBatchMove() {
    }
}
