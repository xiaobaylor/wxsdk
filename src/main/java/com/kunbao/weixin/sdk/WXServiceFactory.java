package com.kunbao.weixin.sdk;

import com.kunbao.weixin.sdk.management.account.WXAccountService;
import com.kunbao.weixin.sdk.management.material.WXMaterialService;
import com.kunbao.weixin.sdk.management.menu.WXMenuService;
import com.kunbao.weixin.sdk.management.oauth2.WXOAuthService;
import com.kunbao.weixin.sdk.management.user.WXUserService;
import com.kunbao.weixin.sdk.media.WXMediaService;
import com.kunbao.weixin.sdk.message.WXMessageService;
import com.kunbao.weixin.sdk.security.WXSecurityService;
import com.kunbao.weixin.sdk.ticket.WXTicketService;

/**
 * Created by lemon_bar on 15/7/22.
 */
public class WXServiceFactory {
    private WXSecurityService wxSecurityService = null;

    public synchronized WXSecurityService getWxSecurityService() {
        if (wxSecurityService == null) {
            wxSecurityService = new WXSecurityService();
        }
        return wxSecurityService;
    }

    private WXMessageService wxMessageService = null;

    public synchronized WXMessageService getWxMessageService() {
        if (wxMessageService == null) {
            wxMessageService = new WXMessageService();
        }
        return wxMessageService;
    }

    private WXAccountService wxAccountService = null;

    public synchronized WXAccountService getWxAccountService() {
        if (wxAccountService == null) {
            wxAccountService = new WXAccountService();
        }
        return wxAccountService;
    }

    private WXMaterialService wxMaterialService = null;

    public synchronized WXMaterialService getWxMaterialService() {
        if (wxMaterialService == null) {
            wxMaterialService = new WXMaterialService();
        }
        return wxMaterialService;
    }

    private WXMenuService wxMenuService = null;

    public synchronized WXMenuService getWxMenuService() {
        if (wxMenuService == null) {
            wxMenuService = new WXMenuService();
        }
        return wxMenuService;
    }

    private WXUserService wxUserService = null;

    public synchronized WXUserService getWxUserService() {
        if (wxUserService == null) {
            wxUserService = new WXUserService();
        }
        return wxUserService;
    }

    private WXOAuthService wxOAuthService = null;

    public synchronized WXOAuthService getWxOAuthService() {
        if(wxOAuthService == null){
            wxOAuthService = new WXOAuthService();
        }

        return wxOAuthService;
    }

    private WXTicketService wxTicketService;

    public synchronized WXTicketService getWXTicketService() {
        if(wxTicketService == null) {
            wxTicketService = new WXTicketService();
        }

        return wxTicketService;
    }

    private WXMediaService wxMediaService;

    public synchronized WXMediaService getWxMediaService(){
        if(wxMediaService == null){
            wxMediaService = new WXMediaService();
        }
        return wxMediaService;
    }


}
