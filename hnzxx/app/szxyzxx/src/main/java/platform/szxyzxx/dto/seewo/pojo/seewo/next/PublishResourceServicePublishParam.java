package platform.szxyzxx.dto.seewo.pojo.seewo.next;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.ParameterPosition;

/**
 * seewo-open API: 批量发布新闻公告图片视频资源
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-4-30
 */
public class PublishResourceServicePublishParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private RequestBody requestBody;


    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static PublishResourceServicePublishParamBuilder builder(){
        return new PublishResourceServicePublishParamBuilder();
    }

    public static class PublishResourceServicePublishParamBuilder{
        private RequestBody requestBody;

        public PublishResourceServicePublishParamBuilder requestBody(RequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public PublishResourceServicePublishParam build(){
            PublishResourceServicePublishParam param = new PublishResourceServicePublishParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class RequestBody {
        /**
         * query
         */
        private Query query;

        public Query getQuery() {
            return this.query;
        }

        public void setQuery(Query query) {
            this.query = query;
        }


        public static RequestBodyBuilder builder(){
            return new RequestBodyBuilder();
        }

        public static class RequestBodyBuilder{
            private Query query;

            public RequestBodyBuilder query(Query query){
                this.query = query;
                return this;
            }

            public RequestBody build(){
                RequestBody param = new RequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class Query {
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 发布者用户ID
         */
        private String userUid;
        /**
         * 资源类型key：news，note，pic，video
         */
        private String key;
        /**
         * 发布内容列表
         */
        private List<PublishItemsItem> publishItems;
        /**
         * 发布范围列表
         */
        private List<ReceiverItemsItem> receiverItems;
        /**
         * 图片分组id
         */
        private String tagId;
        /**
         * 发布时间配置
         */
        private PublishValidTimeParam timeParam;

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getUserUid() {
            return this.userUid;
        }

        public void setUserUid(String userUid) {
            this.userUid = userUid;
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<PublishItemsItem> getPublishItems() {
            return this.publishItems;
        }

        public void setPublishItems(List<PublishItemsItem> publishItems) {
            this.publishItems = publishItems;
        }

        public List<ReceiverItemsItem> getReceiverItems() {
            return this.receiverItems;
        }

        public void setReceiverItems(List<ReceiverItemsItem> receiverItems) {
            this.receiverItems = receiverItems;
        }

        public String getTagId() {
            return this.tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public PublishValidTimeParam getTimeParam() {
            return this.timeParam;
        }

        public void setTimeParam(PublishValidTimeParam timeParam) {
            this.timeParam = timeParam;
        }


        public static QueryBuilder builder(){
            return new QueryBuilder();
        }

        public static class QueryBuilder{
            private String schoolUid;
            private String userUid;
            private String key;
            private List<PublishItemsItem> publishItems;
            private List<ReceiverItemsItem> receiverItems;
            private String tagId;
            private PublishValidTimeParam timeParam;

            public QueryBuilder schoolUid(String schoolUid){
                this.schoolUid = schoolUid;
                return this;
            }
            public QueryBuilder userUid(String userUid){
                this.userUid = userUid;
                return this;
            }
            public QueryBuilder key(String key){
                this.key = key;
                return this;
            }
            public QueryBuilder publishItems(List<PublishItemsItem> publishItems){
                this.publishItems = publishItems;
                return this;
            }
            public QueryBuilder receiverItems(List<ReceiverItemsItem> receiverItems){
                this.receiverItems = receiverItems;
                return this;
            }
            public QueryBuilder tagId(String tagId){
                this.tagId = tagId;
                return this;
            }
            public QueryBuilder timeParam(PublishValidTimeParam timeParam){
                this.timeParam = timeParam;
                return this;
            }

            public Query build(){
                Query param = new Query();
                param.setSchoolUid(schoolUid);
                param.setUserUid(userUid);
                param.setKey(key);
                param.setPublishItems(publishItems);
                param.setReceiverItems(receiverItems);
                param.setTagId(tagId);
                param.setTimeParam(timeParam);
                return param;
            }
        }
    }

    public static class PublishItemsItem {
        /**
         * 新闻公告相关参数-标题
         */
        private String title;
        /**
         * 新闻公告相关参数-封面图路径
         */
        private String coverImageUrl;
        /**
         * 新闻公告内容
         */
        private String content;
        /**
         * 图片相关参数--描述
         */
        private String description;
        /**
         * 视频相关参数--视频名字
         */
        private String name;
        /**
         * 视频相关参数--视频时长单位秒，非必填
         */
        private Integer len;
        /**
         * 视频相关参数--视频大小单位byte，非必填
         */
        private Integer size;
        /**
         * 视频相关参数--视频封面url
         */
        private String headImageUrl;
        /**
         * 视频相关参数--视频分辨率  非必填
         */
        private String resolution;
        /**
         * 视频或者图片共有参数，资源链接
         */
        private String url;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverImageUrl() {
            return this.coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getLen() {
            return this.len;
        }

        public void setLen(Integer len) {
            this.len = len;
        }

        public Integer getSize() {
            return this.size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public String getHeadImageUrl() {
            return this.headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public String getResolution() {
            return this.resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        public static PublishItemsItemBuilder builder(){
            return new PublishItemsItemBuilder();
        }

        public static class PublishItemsItemBuilder{
            private String title;
            private String coverImageUrl;
            private String content;
            private String description;
            private String name;
            private Integer len;
            private Integer size;
            private String headImageUrl;
            private String resolution;
            private String url;

            public PublishItemsItemBuilder title(String title){
                this.title = title;
                return this;
            }
            public PublishItemsItemBuilder coverImageUrl(String coverImageUrl){
                this.coverImageUrl = coverImageUrl;
                return this;
            }
            public PublishItemsItemBuilder content(String content){
                this.content = content;
                return this;
            }
            public PublishItemsItemBuilder description(String description){
                this.description = description;
                return this;
            }
            public PublishItemsItemBuilder name(String name){
                this.name = name;
                return this;
            }
            public PublishItemsItemBuilder len(Integer len){
                this.len = len;
                return this;
            }
            public PublishItemsItemBuilder size(Integer size){
                this.size = size;
                return this;
            }
            public PublishItemsItemBuilder headImageUrl(String headImageUrl){
                this.headImageUrl = headImageUrl;
                return this;
            }
            public PublishItemsItemBuilder resolution(String resolution){
                this.resolution = resolution;
                return this;
            }
            public PublishItemsItemBuilder url(String url){
                this.url = url;
                return this;
            }

            public PublishItemsItem build(){
                PublishItemsItem param = new PublishItemsItem();
                param.setTitle(title);
                param.setCoverImageUrl(coverImageUrl);
                param.setContent(content);
                param.setDescription(description);
                param.setName(name);
                param.setLen(len);
                param.setSize(size);
                param.setHeadImageUrl(headImageUrl);
                param.setResolution(resolution);
                param.setUrl(url);
                return param;
            }
        }
    }

    public static class ReceiverItemsItem {
        /**
         * 发布范围：2学校，3班级
         */
        private Integer scope;
        /**
         * 发布的学校，班级ID
         */
        private String receiverId;

        public Integer getScope() {
            return this.scope;
        }

        public void setScope(Integer scope) {
            this.scope = scope;
        }

        public String getReceiverId() {
            return this.receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }


        public static ReceiverItemsItemBuilder builder(){
            return new ReceiverItemsItemBuilder();
        }

        public static class ReceiverItemsItemBuilder{
            private Integer scope;
            private String receiverId;

            public ReceiverItemsItemBuilder scope(Integer scope){
                this.scope = scope;
                return this;
            }
            public ReceiverItemsItemBuilder receiverId(String receiverId){
                this.receiverId = receiverId;
                return this;
            }

            public ReceiverItemsItem build(){
                ReceiverItemsItem param = new ReceiverItemsItem();
                param.setScope(scope);
                param.setReceiverId(receiverId);
                return param;
            }
        }
    }

    public static class PublishValidTimeParam {
        /**
         * 开始时间（时间戳）
         */
        private Long startTime;
        /**
         * 结束时间（时间戳）
         */
        private Long endTime;

        public Long getStartTime() {
            return this.startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        public Long getEndTime() {
            return this.endTime;
        }

        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }


        public static PublishValidTimeParamBuilder builder(){
            return new PublishValidTimeParamBuilder();
        }

        public static class PublishValidTimeParamBuilder{
            private Long startTime;
            private Long endTime;

            public PublishValidTimeParamBuilder startTime(Long startTime){
                this.startTime = startTime;
                return this;
            }
            public PublishValidTimeParamBuilder endTime(Long endTime){
                this.endTime = endTime;
                return this;
            }

            public PublishValidTimeParam build(){
                PublishValidTimeParam param = new PublishValidTimeParam();
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                return param;
            }
        }
    }


}
