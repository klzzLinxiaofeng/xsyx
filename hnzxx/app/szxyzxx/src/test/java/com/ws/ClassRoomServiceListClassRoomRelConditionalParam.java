package com.ws;

import com.seewo.open.sdk.OpenApiParam;

/**
 * seewo-open API: 条件查询班级场地绑定列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2021-12-30
 */
public class ClassRoomServiceListClassRoomRelConditionalParam extends OpenApiParam {


    /**
     * 请求体，MimeType为 application/json
     */
    
    private JSONRequestBody requestBody;


    public JSONRequestBody getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(JSONRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public static ClassRoomServiceListClassRoomRelConditionalParamBuilder builder(){
        return new ClassRoomServiceListClassRoomRelConditionalParamBuilder();
    }

    public static class ClassRoomServiceListClassRoomRelConditionalParamBuilder{
        private JSONRequestBody requestBody;

        public ClassRoomServiceListClassRoomRelConditionalParamBuilder requestBody(JSONRequestBody requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public ClassRoomServiceListClassRoomRelConditionalParam build(){
            ClassRoomServiceListClassRoomRelConditionalParam param = new ClassRoomServiceListClassRoomRelConditionalParam();
            param.setRequestBody(requestBody);
            return param;
        }
    }

    public static class JSONRequestBody {
        /**
         * 入参条件
         */
        private ThirdClassRoomRelQuery query;

        public ThirdClassRoomRelQuery getQuery() {
            return this.query;
        }

        public void setQuery(ThirdClassRoomRelQuery query) {
            this.query = query;
        }


        public static JSONRequestBodyBuilder builder(){
            return new JSONRequestBodyBuilder();
        }

        public static class JSONRequestBodyBuilder{
            private ThirdClassRoomRelQuery query;

            public JSONRequestBodyBuilder query(ThirdClassRoomRelQuery query){
                this.query = query;
                return this;
            }

            public JSONRequestBody build(){
                JSONRequestBody param = new JSONRequestBody();
                param.setQuery(query);
                return param;
            }
        }
    }

    public static class ThirdClassRoomRelQuery {
        /**
         * 
         */
        private String appId;
        /**
         *  希沃机构uid
         */
        private String unitUid;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getUnitUid() {
            return this.unitUid;
        }

        public void setUnitUid(String unitUid) {
            this.unitUid = unitUid;
        }


        public static ThirdClassRoomRelQueryBuilder builder(){
            return new ThirdClassRoomRelQueryBuilder();
        }

        public static class ThirdClassRoomRelQueryBuilder{
            private String appId;
            private String unitUid;

            public ThirdClassRoomRelQueryBuilder appId(String appId){
                this.appId = appId;
                return this;
            }
            public ThirdClassRoomRelQueryBuilder unitUid(String unitUid){
                this.unitUid = unitUid;
                return this;
            }

            public ThirdClassRoomRelQuery build(){
                ThirdClassRoomRelQuery param = new ThirdClassRoomRelQuery();
                param.setAppId(appId);
                param.setUnitUid(unitUid);
                return param;
            }
        }
    }


}
