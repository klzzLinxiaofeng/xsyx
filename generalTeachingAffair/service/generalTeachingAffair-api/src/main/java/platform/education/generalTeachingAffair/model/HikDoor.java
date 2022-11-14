package platform.education.generalTeachingAffair.model;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/4/8 10:37
 *  @Description: 海康
 */
public class HikDoor {


    /**
     * method : OnEventNotify
     * params : {"ability":"event_acs","events":[{"data":{"ExtAccessChannel":0,"ExtEventAlarmInID":0,"ExtEventAlarmOutID":0,"ExtEventCardNo":"","ExtEventCaseID":0,"ExtEventCode":196883,"ExtEventCustomerNumInfo":{"AccessChannel":0,"EntryTimes":0,"ExitTimes":0,"TotalTimes":0},"ExtEventDoorID":2,"ExtEventIDCardPictureURL":"","ExtEventIdentityCardInfo":{"Address":"","Birth":"","EndDate":"","IdNum":"","IssuingAuthority":"","Name":"","Nation":0,"Sex":0,"StartDate":"","TermOfValidity":0},"ExtEventInOut":0,"ExtEventLocalControllerID":0,"ExtEventMainDevID":1,"ExtEventPersonNo":"0","ExtEventPictureURL":"","ExtEventReaderID":0,"ExtEventReaderKind":0,"ExtEventReportChannel":0,"ExtEventRoleID":1,"ExtEventSubDevID":0,"ExtEventSwipNum":0,"ExtEventType":0,"ExtEventVerifyID":0,"ExtEventWhiteListNo":0,"ExtReceiveTime":"1558576265810791","Seq":475,"svrIndexCode":""},"eventId":"341BEAA4AEAD480484C595488FE5427C","eventType":196883,"eventTypeName":"acs.acs.eventType.successMultipleAuthCard","happenTime":"2019-05-23T09:51:09.000+08:00","srcIndex":"73fc53c972f64019b04a636c5757489a","srcName":"2604_zhy_门2","srcParentIndex":"1c4bedc887954edbade674adfeb4dfcd","srcType":"door","status":0,"timeout":0}],"sendTime":"2019-05-23T09:51:05.000+08:00"}
     */

    private String method;
    private ParamsBean params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * ability : event_acs
         * events : [{"data":{"ExtAccessChannel":0,"ExtEventAlarmInID":0,"ExtEventAlarmOutID":0,"ExtEventCardNo":"","ExtEventCaseID":0,"ExtEventCode":196883,"ExtEventCustomerNumInfo":{"AccessChannel":0,"EntryTimes":0,"ExitTimes":0,"TotalTimes":0},"ExtEventDoorID":2,"ExtEventIDCardPictureURL":"","ExtEventIdentityCardInfo":{"Address":"","Birth":"","EndDate":"","IdNum":"","IssuingAuthority":"","Name":"","Nation":0,"Sex":0,"StartDate":"","TermOfValidity":0},"ExtEventInOut":0,"ExtEventLocalControllerID":0,"ExtEventMainDevID":1,"ExtEventPersonNo":"0","ExtEventPictureURL":"","ExtEventReaderID":0,"ExtEventReaderKind":0,"ExtEventReportChannel":0,"ExtEventRoleID":1,"ExtEventSubDevID":0,"ExtEventSwipNum":0,"ExtEventType":0,"ExtEventVerifyID":0,"ExtEventWhiteListNo":0,"ExtReceiveTime":"1558576265810791","Seq":475,"svrIndexCode":""},"eventId":"341BEAA4AEAD480484C595488FE5427C","eventType":196883,"eventTypeName":"acs.acs.eventType.successMultipleAuthCard","happenTime":"2019-05-23T09:51:09.000+08:00","srcIndex":"73fc53c972f64019b04a636c5757489a","srcName":"2604_zhy_门2","srcParentIndex":"1c4bedc887954edbade674adfeb4dfcd","srcType":"door","status":0,"timeout":0}]
         * sendTime : 2019-05-23T09:51:05.000+08:00
         */

        private String ability;
        private String sendTime;
        private List<EventsBean> events;

        @Override
        public String toString() {
            return "ParamsBean{" +
                    "ability='" + ability + '\'' +
                    ", sendTime='" + sendTime + '\'' +
                    ", events=" + events +
                    '}';
        }

        public String getAbility() {
            return ability;
        }

        public void setAbility(String ability) {
            this.ability = ability;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public List<EventsBean> getEvents() {
            return events;
        }

        public void setEvents(List<EventsBean> events) {
            this.events = events;
        }

        public static class EventsBean {
            /**
             * data : {"ExtAccessChannel":0,"ExtEventAlarmInID":0,"ExtEventAlarmOutID":0,"ExtEventCardNo":"","ExtEventCaseID":0,"ExtEventCode":196883,"ExtEventCustomerNumInfo":{"AccessChannel":0,"EntryTimes":0,"ExitTimes":0,"TotalTimes":0},"ExtEventDoorID":2,"ExtEventIDCardPictureURL":"","ExtEventIdentityCardInfo":{"Address":"","Birth":"","EndDate":"","IdNum":"","IssuingAuthority":"","Name":"","Nation":0,"Sex":0,"StartDate":"","TermOfValidity":0},"ExtEventInOut":0,"ExtEventLocalControllerID":0,"ExtEventMainDevID":1,"ExtEventPersonNo":"0","ExtEventPictureURL":"","ExtEventReaderID":0,"ExtEventReaderKind":0,"ExtEventReportChannel":0,"ExtEventRoleID":1,"ExtEventSubDevID":0,"ExtEventSwipNum":0,"ExtEventType":0,"ExtEventVerifyID":0,"ExtEventWhiteListNo":0,"ExtReceiveTime":"1558576265810791","Seq":475,"svrIndexCode":""}
             * eventId : 341BEAA4AEAD480484C595488FE5427C
             * eventType : 196883
             * eventTypeName : acs.acs.eventType.successMultipleAuthCard
             * happenTime : 2019-05-23T09:51:09.000+08:00
             * srcIndex : 73fc53c972f64019b04a636c5757489a
             * srcName : 2604_zhy_门2
             * srcParentIndex : 1c4bedc887954edbade674adfeb4dfcd
             * srcType : door
             * status : 0
             * timeout : 0
             */

            private DataBean data;
            private String eventId;
            private int eventType;
            private String eventTypeName;
            private String happenTime;
            private String srcIndex;
            private String srcName;
            private String srcParentIndex;
            private String srcType;
            private int status;
            private int timeout;

            @Override
            public String toString() {
                return "EventsBean{" +
                        "data=" + data +
                        ", eventId='" + eventId + '\'' +
                        ", eventType=" + eventType +
                        ", eventTypeName='" + eventTypeName + '\'' +
                        ", happenTime='" + happenTime + '\'' +
                        ", srcIndex='" + srcIndex + '\'' +
                        ", srcName='" + srcName + '\'' +
                        ", srcParentIndex='" + srcParentIndex + '\'' +
                        ", srcType='" + srcType + '\'' +
                        ", status=" + status +
                        ", timeout=" + timeout +
                        '}';
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public String getEventId() {
                return eventId;
            }

            public void setEventId(String eventId) {
                this.eventId = eventId;
            }

            public int getEventType() {
                return eventType;
            }

            public void setEventType(int eventType) {
                this.eventType = eventType;
            }

            public String getEventTypeName() {
                return eventTypeName;
            }

            public void setEventTypeName(String eventTypeName) {
                this.eventTypeName = eventTypeName;
            }

            public String getHappenTime() {
                return happenTime;
            }

            public void setHappenTime(String happenTime) {
                this.happenTime = happenTime;
            }

            public String getSrcIndex() {
                return srcIndex;
            }

            public void setSrcIndex(String srcIndex) {
                this.srcIndex = srcIndex;
            }

            public String getSrcName() {
                return srcName;
            }

            public void setSrcName(String srcName) {
                this.srcName = srcName;
            }

            public String getSrcParentIndex() {
                return srcParentIndex;
            }

            public void setSrcParentIndex(String srcParentIndex) {
                this.srcParentIndex = srcParentIndex;
            }

            public String getSrcType() {
                return srcType;
            }

            public void setSrcType(String srcType) {
                this.srcType = srcType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTimeout() {
                return timeout;
            }

            public void setTimeout(int timeout) {
                this.timeout = timeout;
            }

            public static class DataBean {
                /**
                 * ExtAccessChannel : 0
                 * ExtEventAlarmInID : 0
                 * ExtEventAlarmOutID : 0
                 * ExtEventCardNo :
                 * ExtEventCaseID : 0
                 * ExtEventCode : 196883
                 * ExtEventCustomerNumInfo : {"AccessChannel":0,"EntryTimes":0,"ExitTimes":0,"TotalTimes":0}
                 * ExtEventDoorID : 2
                 * ExtEventIDCardPictureURL :
                 * ExtEventIdentityCardInfo : {"Address":"","Birth":"","EndDate":"","IdNum":"","IssuingAuthority":"","Name":"","Nation":0,"Sex":0,"StartDate":"","TermOfValidity":0}
                 * ExtEventInOut : 0
                 * ExtEventLocalControllerID : 0
                 * ExtEventMainDevID : 1
                 * ExtEventPersonNo : 0
                 * ExtEventPictureURL :
                 * ExtEventReaderID : 0
                 * ExtEventReaderKind : 0
                 * ExtEventReportChannel : 0
                 * ExtEventRoleID : 1
                 * ExtEventSubDevID : 0
                 * ExtEventSwipNum : 0
                 * ExtEventType : 0
                 * ExtEventVerifyID : 0
                 * ExtEventWhiteListNo : 0
                 * ExtReceiveTime : 1558576265810791
                 * Seq : 475
                 * svrIndexCode :
                 */

                private int ExtAccessChannel;
                private int ExtEventAlarmInID;
                private int ExtEventAlarmOutID;
                private String ExtEventCardNo;
                private int ExtEventCaseID;
                private int ExtEventCode;
                private ExtEventCustomerNumInfoBean ExtEventCustomerNumInfo;
                private int ExtEventDoorID;
                private String ExtEventIDCardPictureURL;
                private ExtEventIdentityCardInfoBean ExtEventIdentityCardInfo;
                private int ExtEventInOut;
                private int ExtEventLocalControllerID;
                private int ExtEventMainDevID;
                private String ExtEventPersonNo;
                private String ExtEventPictureURL;
                private int ExtEventReaderID;
                private int ExtEventReaderKind;
                private int ExtEventReportChannel;
                private int ExtEventRoleID;
                private int ExtEventSubDevID;
                private int ExtEventSwipNum;
                private int ExtEventType;
                private int ExtEventVerifyID;
                private int ExtEventWhiteListNo;
                private String ExtReceiveTime;
                private int Seq;
                private String svrIndexCode;

                @Override
                public String toString() {
                    return "DataBean{" +
                            "ExtAccessChannel=" + ExtAccessChannel +
                            ", ExtEventAlarmInID=" + ExtEventAlarmInID +
                            ", ExtEventAlarmOutID=" + ExtEventAlarmOutID +
                            ", ExtEventCardNo='" + ExtEventCardNo + '\'' +
                            ", ExtEventCaseID=" + ExtEventCaseID +
                            ", ExtEventCode=" + ExtEventCode +
                            ", ExtEventCustomerNumInfo=" + ExtEventCustomerNumInfo +
                            ", ExtEventDoorID=" + ExtEventDoorID +
                            ", ExtEventIDCardPictureURL='" + ExtEventIDCardPictureURL + '\'' +
                            ", ExtEventIdentityCardInfo=" + ExtEventIdentityCardInfo +
                            ", ExtEventInOut=" + ExtEventInOut +
                            ", ExtEventLocalControllerID=" + ExtEventLocalControllerID +
                            ", ExtEventMainDevID=" + ExtEventMainDevID +
                            ", ExtEventPersonNo='" + ExtEventPersonNo + '\'' +
                            ", ExtEventPictureURL='" + ExtEventPictureURL + '\'' +
                            ", ExtEventReaderID=" + ExtEventReaderID +
                            ", ExtEventReaderKind=" + ExtEventReaderKind +
                            ", ExtEventReportChannel=" + ExtEventReportChannel +
                            ", ExtEventRoleID=" + ExtEventRoleID +
                            ", ExtEventSubDevID=" + ExtEventSubDevID +
                            ", ExtEventSwipNum=" + ExtEventSwipNum +
                            ", ExtEventType=" + ExtEventType +
                            ", ExtEventVerifyID=" + ExtEventVerifyID +
                            ", ExtEventWhiteListNo=" + ExtEventWhiteListNo +
                            ", ExtReceiveTime='" + ExtReceiveTime + '\'' +
                            ", Seq=" + Seq +
                            ", svrIndexCode='" + svrIndexCode + '\'' +
                            '}';
                }

                public int getExtAccessChannel() {
                    return ExtAccessChannel;
                }

                public void setExtAccessChannel(int ExtAccessChannel) {
                    this.ExtAccessChannel = ExtAccessChannel;
                }

                public int getExtEventAlarmInID() {
                    return ExtEventAlarmInID;
                }

                public void setExtEventAlarmInID(int ExtEventAlarmInID) {
                    this.ExtEventAlarmInID = ExtEventAlarmInID;
                }

                public int getExtEventAlarmOutID() {
                    return ExtEventAlarmOutID;
                }

                public void setExtEventAlarmOutID(int ExtEventAlarmOutID) {
                    this.ExtEventAlarmOutID = ExtEventAlarmOutID;
                }

                public String getExtEventCardNo() {
                    return ExtEventCardNo;
                }

                public void setExtEventCardNo(String ExtEventCardNo) {
                    this.ExtEventCardNo = ExtEventCardNo;
                }

                public int getExtEventCaseID() {
                    return ExtEventCaseID;
                }

                public void setExtEventCaseID(int ExtEventCaseID) {
                    this.ExtEventCaseID = ExtEventCaseID;
                }

                public int getExtEventCode() {
                    return ExtEventCode;
                }

                public void setExtEventCode(int ExtEventCode) {
                    this.ExtEventCode = ExtEventCode;
                }

                public ExtEventCustomerNumInfoBean getExtEventCustomerNumInfo() {
                    return ExtEventCustomerNumInfo;
                }

                public void setExtEventCustomerNumInfo(ExtEventCustomerNumInfoBean ExtEventCustomerNumInfo) {
                    this.ExtEventCustomerNumInfo = ExtEventCustomerNumInfo;
                }

                public int getExtEventDoorID() {
                    return ExtEventDoorID;
                }

                public void setExtEventDoorID(int ExtEventDoorID) {
                    this.ExtEventDoorID = ExtEventDoorID;
                }

                public String getExtEventIDCardPictureURL() {
                    return ExtEventIDCardPictureURL;
                }

                public void setExtEventIDCardPictureURL(String ExtEventIDCardPictureURL) {
                    this.ExtEventIDCardPictureURL = ExtEventIDCardPictureURL;
                }

                public ExtEventIdentityCardInfoBean getExtEventIdentityCardInfo() {
                    return ExtEventIdentityCardInfo;
                }

                public void setExtEventIdentityCardInfo(ExtEventIdentityCardInfoBean ExtEventIdentityCardInfo) {
                    this.ExtEventIdentityCardInfo = ExtEventIdentityCardInfo;
                }

                public int getExtEventInOut() {
                    return ExtEventInOut;
                }

                public void setExtEventInOut(int ExtEventInOut) {
                    this.ExtEventInOut = ExtEventInOut;
                }

                public int getExtEventLocalControllerID() {
                    return ExtEventLocalControllerID;
                }

                public void setExtEventLocalControllerID(int ExtEventLocalControllerID) {
                    this.ExtEventLocalControllerID = ExtEventLocalControllerID;
                }

                public int getExtEventMainDevID() {
                    return ExtEventMainDevID;
                }

                public void setExtEventMainDevID(int ExtEventMainDevID) {
                    this.ExtEventMainDevID = ExtEventMainDevID;
                }

                public String getExtEventPersonNo() {
                    return ExtEventPersonNo;
                }

                public void setExtEventPersonNo(String ExtEventPersonNo) {
                    this.ExtEventPersonNo = ExtEventPersonNo;
                }

                public String getExtEventPictureURL() {
                    return ExtEventPictureURL;
                }

                public void setExtEventPictureURL(String ExtEventPictureURL) {
                    this.ExtEventPictureURL = ExtEventPictureURL;
                }

                public int getExtEventReaderID() {
                    return ExtEventReaderID;
                }

                public void setExtEventReaderID(int ExtEventReaderID) {
                    this.ExtEventReaderID = ExtEventReaderID;
                }

                public int getExtEventReaderKind() {
                    return ExtEventReaderKind;
                }

                public void setExtEventReaderKind(int ExtEventReaderKind) {
                    this.ExtEventReaderKind = ExtEventReaderKind;
                }

                public int getExtEventReportChannel() {
                    return ExtEventReportChannel;
                }

                public void setExtEventReportChannel(int ExtEventReportChannel) {
                    this.ExtEventReportChannel = ExtEventReportChannel;
                }

                public int getExtEventRoleID() {
                    return ExtEventRoleID;
                }

                public void setExtEventRoleID(int ExtEventRoleID) {
                    this.ExtEventRoleID = ExtEventRoleID;
                }

                public int getExtEventSubDevID() {
                    return ExtEventSubDevID;
                }

                public void setExtEventSubDevID(int ExtEventSubDevID) {
                    this.ExtEventSubDevID = ExtEventSubDevID;
                }

                public int getExtEventSwipNum() {
                    return ExtEventSwipNum;
                }

                public void setExtEventSwipNum(int ExtEventSwipNum) {
                    this.ExtEventSwipNum = ExtEventSwipNum;
                }

                public int getExtEventType() {
                    return ExtEventType;
                }

                public void setExtEventType(int ExtEventType) {
                    this.ExtEventType = ExtEventType;
                }

                public int getExtEventVerifyID() {
                    return ExtEventVerifyID;
                }

                public void setExtEventVerifyID(int ExtEventVerifyID) {
                    this.ExtEventVerifyID = ExtEventVerifyID;
                }

                public int getExtEventWhiteListNo() {
                    return ExtEventWhiteListNo;
                }

                public void setExtEventWhiteListNo(int ExtEventWhiteListNo) {
                    this.ExtEventWhiteListNo = ExtEventWhiteListNo;
                }

                public String getExtReceiveTime() {
                    return ExtReceiveTime;
                }

                public void setExtReceiveTime(String ExtReceiveTime) {
                    this.ExtReceiveTime = ExtReceiveTime;
                }

                public int getSeq() {
                    return Seq;
                }

                public void setSeq(int Seq) {
                    this.Seq = Seq;
                }

                public String getSvrIndexCode() {
                    return svrIndexCode;
                }

                public void setSvrIndexCode(String svrIndexCode) {
                    this.svrIndexCode = svrIndexCode;
                }

                public static class ExtEventCustomerNumInfoBean {
                    /**
                     * AccessChannel : 0
                     * EntryTimes : 0
                     * ExitTimes : 0
                     * TotalTimes : 0
                     */

                    private int AccessChannel;
                    private int EntryTimes;
                    private int ExitTimes;
                    private int TotalTimes;

                    @Override
                    public String toString() {
                        return "ExtEventCustomerNumInfoBean{" +
                                "AccessChannel=" + AccessChannel +
                                ", EntryTimes=" + EntryTimes +
                                ", ExitTimes=" + ExitTimes +
                                ", TotalTimes=" + TotalTimes +
                                '}';
                    }

                    public int getAccessChannel() {
                        return AccessChannel;
                    }

                    public void setAccessChannel(int AccessChannel) {
                        this.AccessChannel = AccessChannel;
                    }

                    public int getEntryTimes() {
                        return EntryTimes;
                    }

                    public void setEntryTimes(int EntryTimes) {
                        this.EntryTimes = EntryTimes;
                    }

                    public int getExitTimes() {
                        return ExitTimes;
                    }

                    public void setExitTimes(int ExitTimes) {
                        this.ExitTimes = ExitTimes;
                    }

                    public int getTotalTimes() {
                        return TotalTimes;
                    }

                    public void setTotalTimes(int TotalTimes) {
                        this.TotalTimes = TotalTimes;
                    }
                }

                public static class ExtEventIdentityCardInfoBean {
                    /**
                     * Address :
                     * Birth :
                     * EndDate :
                     * IdNum :
                     * IssuingAuthority :
                     * Name :
                     * Nation : 0
                     * Sex : 0
                     * StartDate :
                     * TermOfValidity : 0
                     */

                    private String Address;
                    private String Birth;
                    private String EndDate;
                    private String IdNum;
                    private String IssuingAuthority;
                    private String Name;
                    private int Nation;
                    private int Sex;
                    private String StartDate;
                    private int TermOfValidity;

                    @Override
                    public String toString() {
                        return "ExtEventIdentityCardInfoBean{" +
                                "Address='" + Address + '\'' +
                                ", Birth='" + Birth + '\'' +
                                ", EndDate='" + EndDate + '\'' +
                                ", IdNum='" + IdNum + '\'' +
                                ", IssuingAuthority='" + IssuingAuthority + '\'' +
                                ", Name='" + Name + '\'' +
                                ", Nation=" + Nation +
                                ", Sex=" + Sex +
                                ", StartDate='" + StartDate + '\'' +
                                ", TermOfValidity=" + TermOfValidity +
                                '}';
                    }

                    public String getAddress() {
                        return Address;
                    }

                    public void setAddress(String Address) {
                        this.Address = Address;
                    }

                    public String getBirth() {
                        return Birth;
                    }

                    public void setBirth(String Birth) {
                        this.Birth = Birth;
                    }

                    public String getEndDate() {
                        return EndDate;
                    }

                    public void setEndDate(String EndDate) {
                        this.EndDate = EndDate;
                    }

                    public String getIdNum() {
                        return IdNum;
                    }

                    public void setIdNum(String IdNum) {
                        this.IdNum = IdNum;
                    }

                    public String getIssuingAuthority() {
                        return IssuingAuthority;
                    }

                    public void setIssuingAuthority(String IssuingAuthority) {
                        this.IssuingAuthority = IssuingAuthority;
                    }

                    public String getName() {
                        return Name;
                    }

                    public void setName(String Name) {
                        this.Name = Name;
                    }

                    public int getNation() {
                        return Nation;
                    }

                    public void setNation(int Nation) {
                        this.Nation = Nation;
                    }

                    public int getSex() {
                        return Sex;
                    }

                    public void setSex(int Sex) {
                        this.Sex = Sex;
                    }

                    public String getStartDate() {
                        return StartDate;
                    }

                    public void setStartDate(String StartDate) {
                        this.StartDate = StartDate;
                    }

                    public int getTermOfValidity() {
                        return TermOfValidity;
                    }

                    public void setTermOfValidity(int TermOfValidity) {
                        this.TermOfValidity = TermOfValidity;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Door{" +
                "method='" + method + '\'' +
                ", params=" + params +
                '}';
    }
}
