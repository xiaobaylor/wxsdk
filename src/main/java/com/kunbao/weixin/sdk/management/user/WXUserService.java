package com.kunbao.weixin.sdk.management.user;

import com.kunbao.weixin.sdk.base.WXHttpDispatch;
import com.kunbao.weixin.sdk.base.exception.WXException;
import com.kunbao.weixin.sdk.base.response.WXJsonResponse;
import com.kunbao.weixin.sdk.management.user.domain.*;
import com.kunbao.weixin.sdk.management.user.request.*;
import com.kunbao.weixin.sdk.management.user.response.*;
import com.kunbao.weixin.sdk.token.WXTokenController;

import java.util.List;

/**
 * Created by lemon_bar on 15/7/22.
 */
public class WXUserService {
    public WXUserGetResponse getUserList(String nextOpenId) throws WXException {
        WXUserGetRequest request = new WXUserGetRequest(WXTokenController.getToken(), nextOpenId);
        WXUserGetResponse response = (WXUserGetResponse) WXHttpDispatch.execute(request);
        return response;
    }

    public WXUserInfoResponse getUserInfo(String openId, WXLang lang) throws WXException {
        WXUserInfoRequest request = new WXUserInfoRequest(WXTokenController.getToken(), openId, lang);
        WXUserInfoResponse response = (WXUserInfoResponse) WXHttpDispatch.execute(request);
        return response;
    }

    public WXUserInfoListResponse getBatchUserInfo(WXUserList userList) throws WXException {
        WXUserInfoBatchRequest request = new WXUserInfoBatchRequest(WXTokenController.getToken(), userList);
        WXUserInfoListResponse response = (WXUserInfoListResponse)WXHttpDispatch.execute(request);
        return response;
    }

    public WXUserGroup createUserGroup(String groupName) throws WXException {
        WXUserGroupWrapper userGroup = new WXUserGroupWrapper(groupName);
        WXUserGroupCreateRequest request = new WXUserGroupCreateRequest(WXTokenController.getToken(), userGroup);
        WXUserGroupCreateResponse response = (WXUserGroupCreateResponse) WXHttpDispatch.execute(request);
        return response.getUserGroup();
    }

    public List<WXUserGroup> getUserGroup() throws WXException {
        WXUserGroupGetRequest request = new WXUserGroupGetRequest(WXTokenController.getToken());
        WXUserGroupGetResponse response = (WXUserGroupGetResponse) WXHttpDispatch.execute(request);
        return response.getUserGroups();
    }

    public int getUserInGroupId(String openId) throws WXException {
        WXUserGroupGetIdRequest request = new WXUserGroupGetIdRequest(WXTokenController.getToken(), openId);
        WXUserGroupGetIdResponse response = (WXUserGroupGetIdResponse) WXHttpDispatch.execute(request);
        return response.getGroupId();
    }

    public boolean updateUserGroup(int groupId, String groupName) throws WXException {
        WXUserGroupWrapper groupWrapper = new WXUserGroupWrapper(groupId, groupName);
        WXUserGroupUpdateRequest request = new WXUserGroupUpdateRequest(WXTokenController.getToken(), groupWrapper);
        WXJsonResponse response = (WXJsonResponse) WXHttpDispatch.execute(request);
        return response.isSuccess();
    }

    public boolean moveUserToGroup(String openId, int groupId) throws WXException {
        WXUserMove userMove = new WXUserMove(openId, groupId);
        WXUserMoveGroupRequest request = new WXUserMoveGroupRequest(WXTokenController.getToken(), userMove);
        WXJsonResponse response = (WXJsonResponse) WXHttpDispatch.execute(request);
        return response.isSuccess();
    }

    public boolean moveBatchUserToGroup(List<String> openIdList, int groupId) throws WXException {
        WXUserBatchMove batchMove = new WXUserBatchMove(openIdList, groupId);
        WXUserBatchMoveGroupRequest request = new WXUserBatchMoveGroupRequest(WXTokenController.getToken(), batchMove);
        WXJsonResponse response = (WXJsonResponse) WXHttpDispatch.execute(request);
        return response.isSuccess();
    }

    public boolean deleteUseGroup(int groupId) throws WXException {
        WXUserGroupDeleteRequest request = new WXUserGroupDeleteRequest(WXTokenController.getToken(), groupId);
        WXJsonResponse response = (WXJsonResponse) WXHttpDispatch.execute(request);
        return response.isSuccess();
    }

    public boolean remarkUser(String openId, String remark) throws WXException {
        WXUserRemarkRequest request = new WXUserRemarkRequest(WXTokenController.getToken(), openId, remark);
        WXJsonResponse response = (WXJsonResponse) WXHttpDispatch.execute(request);
        return response.isSuccess();
    }

    public boolean moveTagBatch(List<String> openIdList, int tagId) throws WXException {
        WxUserTagBatchMove batchMove = new WxUserTagBatchMove(openIdList, tagId);
        WxUserTagBatchMoveRequest request = new WxUserTagBatchMoveRequest(WXTokenController.getToken(), batchMove);
        WXJsonResponse response = (WXJsonResponse) WXHttpDispatch.execute(request);
        return response.isSuccess();
    }
}
