package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 获取限定时间内学校或用户留言列表，最多返回500条留言
 * 按留言修改时间增量排序，最多返回500条
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class HomeSchoolServiceListNoteByTimeResult extends OpenApiResult {

    public HomeSchoolServiceListNoteByTimeResult(HttpResponse response) {
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
         * data
         */
        private List<DataItem> data;
        /**
         * 操作结果信息
         */
        private String message;
        /**
         * 业务状态码，000000为成功
         */
        private String code;

        public List<DataItem> getData() {
            return this.data;
        }

        public void setData(List<DataItem> data) {
            this.data = data;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

    public static class DataItem {
        /**
         * 班级uid
         */
        private String classUid;
        /**
         * 留言内容
         */
        private String content;
        /**
         * 留言创建时间，格式为：yyyy-MM-dd HH:mm;ss
         */
        private String createTime;
        /**
         * 留言id
         */
        private String id;
        /**
         * 留言的选项
         */
        private List<OptionsItem> options;
        /**
         * 家长电话
         */
        private String parentPhone;
        /**
         * 接受者头像
         */
        private String receiverHeadImage;
        /**
         * 接收者名字
         */
        private String receiverName;
        /**
         * 接受者类型，child:孩子。parent:家长
         */
        private String receiverType;
        /**
         * 接收者id
         */
        private String receiverUid;
        /**
         * 回复信息列表
         */
        private List<RepliesItem> replies;
        /**
         * 学校uid
         */
        private String schoolUid;
        /**
         * 留言发送时间，格式为：yyyy-MM-dd HH:mm;ss
         */
        private String sendTime;
        /**
         * 发送者头像
         */
        private String senderHeadImage;
        /**
         * 发送者名字
         */
        private String senderName;
        /**
         * 发送者类型。child:孩子。parent:家长
         */
        private String senderType;
        /**
         * 发送者id
         */
        private String senderUid;
        /**
         * 留言的状态。 0 未发送。1 未接收  2 未阅读。 3 已阅读。4 已回复。
         */
        private Integer status;
        /**
         * 学生学号
         */
        private String studentCode;
        /**
         * 留言的类型。1快捷回复。2小投票。3语音留言
         */
        private Integer type;
        /**
         * 留言被修改时间（发送时，状态改变时，收到回复时触发）。格式为：yyyy-MM-dd HH:mm;ss
         */
        private String updateTime;
        /**
         * 语言留言长度
         */
        private Integer voiceLength;
        /**
         * 语言留言音频文件url
         */
        private String voiceUrl;

        public String getClassUid() {
            return this.classUid;
        }

        public void setClassUid(String classUid) {
            this.classUid = classUid;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<OptionsItem> getOptions() {
            return this.options;
        }

        public void setOptions(List<OptionsItem> options) {
            this.options = options;
        }

        public String getParentPhone() {
            return this.parentPhone;
        }

        public void setParentPhone(String parentPhone) {
            this.parentPhone = parentPhone;
        }

        public String getReceiverHeadImage() {
            return this.receiverHeadImage;
        }

        public void setReceiverHeadImage(String receiverHeadImage) {
            this.receiverHeadImage = receiverHeadImage;
        }

        public String getReceiverName() {
            return this.receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverType() {
            return this.receiverType;
        }

        public void setReceiverType(String receiverType) {
            this.receiverType = receiverType;
        }

        public String getReceiverUid() {
            return this.receiverUid;
        }

        public void setReceiverUid(String receiverUid) {
            this.receiverUid = receiverUid;
        }

        public List<RepliesItem> getReplies() {
            return this.replies;
        }

        public void setReplies(List<RepliesItem> replies) {
            this.replies = replies;
        }

        public String getSchoolUid() {
            return this.schoolUid;
        }

        public void setSchoolUid(String schoolUid) {
            this.schoolUid = schoolUid;
        }

        public String getSendTime() {
            return this.sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getSenderHeadImage() {
            return this.senderHeadImage;
        }

        public void setSenderHeadImage(String senderHeadImage) {
            this.senderHeadImage = senderHeadImage;
        }

        public String getSenderName() {
            return this.senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getSenderType() {
            return this.senderType;
        }

        public void setSenderType(String senderType) {
            this.senderType = senderType;
        }

        public String getSenderUid() {
            return this.senderUid;
        }

        public void setSenderUid(String senderUid) {
            this.senderUid = senderUid;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getStudentCode() {
            return this.studentCode;
        }

        public void setStudentCode(String studentCode) {
            this.studentCode = studentCode;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Integer getVoiceLength() {
            return this.voiceLength;
        }

        public void setVoiceLength(Integer voiceLength) {
            this.voiceLength = voiceLength;
        }

        public String getVoiceUrl() {
            return this.voiceUrl;
        }

        public void setVoiceUrl(String voiceUrl) {
            this.voiceUrl = voiceUrl;
        }

    }

    public static class OptionsItem {
        /**
         * 选项内容
         */
        private String content;
        /**
         * id
         */
        private String id;

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public static class RepliesItem {
        /**
         * 回复信息id
         */
        private String id;
        /**
         * 回复选项列表
         */
        private List<OptionsItem> options;
        /**
         * 回复的接受者（对应留言的发送者）
         */
        private String replyReceiverUid;
        /**
         * 回复的发送者（对应留言的接收者）
         */
        private String replySenderUid;
        /**
         * 回复的时间，格式为：yyyy-MM-dd HH:mm;ss
         */
        private String replyTime;
        /**
         * status
         */
        private Integer status;

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<OptionsItem> getOptions() {
            return this.options;
        }

        public void setOptions(List<OptionsItem> options) {
            this.options = options;
        }

        public String getReplyReceiverUid() {
            return this.replyReceiverUid;
        }

        public void setReplyReceiverUid(String replyReceiverUid) {
            this.replyReceiverUid = replyReceiverUid;
        }

        public String getReplySenderUid() {
            return this.replySenderUid;
        }

        public void setReplySenderUid(String replySenderUid) {
            this.replySenderUid = replySenderUid;
        }

        public String getReplyTime() {
            return this.replyTime;
        }

        public void setReplyTime(String replyTime) {
            this.replyTime = replyTime;
        }

        public Integer getStatus() {
            return this.status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }


}

