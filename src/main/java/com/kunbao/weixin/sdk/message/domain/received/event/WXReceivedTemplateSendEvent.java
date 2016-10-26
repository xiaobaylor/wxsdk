package com.kunbao.weixin.sdk.message.domain.received.event;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by baylor on 15/8/24.
 */

//<xml>
//<ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
//<FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
//<CreateTime>1395658920</CreateTime>
//<MsgType><![CDATA[event]]></MsgType>
//<Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
//<MsgID>200163836</MsgID>
//<Status><![CDATA[success]]></Status>
//</xml>
@Getter
@ToString(callSuper = true)
@XmlRootElement(name = "xml")
public class WXReceivedTemplateSendEvent extends WXReceivedEvent {
}
