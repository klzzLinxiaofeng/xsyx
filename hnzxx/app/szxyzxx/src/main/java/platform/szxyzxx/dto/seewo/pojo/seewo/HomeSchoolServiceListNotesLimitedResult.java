package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据发送时间限量获取留言列表
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceListNotesLimitedResult extends OpenApiResult {

    public HomeSchoolServiceListNotesLimitedResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private ResponseBody responseBody;


    public ResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class ResponseBody {
        /**
         * 记录唯一id
         */
        private String id;
        /**
         * 学校ID
         */
        private String schoolUid;
        /**
         * 所在班级ID
         */
        private String classUid;
        /**
         * 学号
         */
        private String studentCode;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 发送者ID
         */
        private String senderUid;
        /**
         * 发送者名字
         */
        private String senderName;
        /**
         * 发送者头像，可空
         */
        private String senderHeadImage;
        /**
         * 接收者ID
         */
        private String receiverUid;
        /**
         * 接收者名字
         */
        private String receiverName;
        /**
         * 接收者头像，可空
         */
        private String receiverHeadImage;
        /**
         * 留言内容
         */
        private String content;
        /**
         * 消息提示，如[表情]
         */
        private String tips;
        /**
         * 语音时长，单位秒
         */
        private Integer voiceLength;
        /**
         * 资源URL，语音文件，图片文件等
         */
        private String resUrl;
        /**
         * 状态：2未读，3已读
         */
        private Integer status;
        /**
         * 留言类型：0、1文本，3语音，4图片，5表情，6贺卡
         */
        private Integer type;
        /**
         * 发送者类型 parent,student,teacher
         */
        private String senderType;
        /**
         * 留言发送时间戳，13位
         */
        private String sendTime;
        /**
         * 更新时间戳，13位
         */
        private String updateTime;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSenderUid() {
            return this.senderUid;
        }

        public void setSenderUid(String senderUid) {
            this.senderUid = senderUid;
        }

        public String getSenderName() {
            return this.senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getSenderHeadImage() {
            return this.senderHeadImage;
        }

        public void setSenderHeadImage(String senderHeadImage) {
            this.senderHeadImage = senderHeadImage;
        }

        public String getReceiverUid() {
            return this.receiverUid;
        }

        public void setReceiverUid(String receiverUid) {
            this.receiverUid = receiverUid;
        }

        public String getReceiverName() {
            return this.receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverHeadImage() {
            return this.receiverHeadImage;
        }

        public void setReceiverHeadImage(String receiverHeadImage) {
            this.receiverHeadImage = receiverHeadImage;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTips() {
            return this.tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public Integer getVoiceLength() {
            return this.voiceLength;
        }

        public void setVoiceLength(Integer voiceLength) {
            this.voiceLength = voiceLength;
        }

        public String getResUrl() {
            return this.resUrl;
        }

        public void setResUrl(String resUrl) {
            this.resUrl = resUrl;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getSenderType() {
            return this.senderType;
        }

        public void setSenderType(String senderType) {
            this.senderType = senderType;
        }

        public String getSendTime() {
            return this.sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

    }


}

