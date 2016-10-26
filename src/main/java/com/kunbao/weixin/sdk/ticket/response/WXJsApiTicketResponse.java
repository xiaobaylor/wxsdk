package com.kunbao.weixin.sdk.ticket.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import com.kunbao.weixin.sdk.base.response.WXResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by baylor on 15/8/12.
 */
@Getter
public class WXJsApiTicketResponse extends WXJsonResponse {
    public WXJsApiTicketResponse(){
        createDate = new Date();
    }
    @JsonProperty("ticket")
    private String ticket;

    @JsonProperty("expires_in")
    private int expires;

    private Date createDate;

    public boolean isValid() {
        Date startDate = getCreateDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.SECOND, getExpires());
        Date endDate = calendar.getTime();
        return endDate.after(new Date());
    }
}
