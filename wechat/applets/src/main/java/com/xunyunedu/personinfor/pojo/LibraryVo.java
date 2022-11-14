package com.xunyunedu.personinfor.pojo;

import java.util.List;

/**
 * 图书馆接口数据
 *
 * @author: yhc
 * @Date: 2021/1/25 10:50
 * @Description:
 */
public class LibraryVo {
    private String msg;
    private boolean success;
    private ResponseBean response;
    private int status;

    public static class ResponseBean {

        private int pageCount;
        private int PageSize;
        private int page;
        private int dataCount;
        private List<DataBean> data;

        public static class DataBean {
            private int Status;
            private String No;
            private String ModifyTime;
            private boolean IsDeleted;
            private String BookSerial;
            private int BookSerialId;
            private String CreateTime;
            private String CreateBy;
            private String ShouldBackTime;
            private int CreateId;
            private String BookTitle;
            private String Name;
            private String CardNo;
            private String BackTime;
            private String UHF;
            private int ReaderId;
            private int ContinueNum;
            private String BookUHF;
            private int Id;
            private String IdentityNo;
            private String ModifyId;
            private String Pic;
            private String Author;

            public String getAuthor() {
                return Author;
            }

            public void setAuthor(String author) {
                Author = author;
            }

            public String getPic() {
                return Pic;
            }

            public void setPic(String pic) {
                this.Pic = pic;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public String getNo() {
                return No;
            }

            public void setNo(String No) {
                this.No = No;
            }

            public String getModifyTime() {
                return ModifyTime;
            }

            public void setModifyTime(String ModifyTime) {
                this.ModifyTime = ModifyTime;
            }

            public boolean isIsDeleted() {
                return IsDeleted;
            }

            public void setIsDeleted(boolean IsDeleted) {
                this.IsDeleted = IsDeleted;
            }

            public Object getBookSerial() {
                return BookSerial;
            }

            public void setBookSerial(String BookSerial) {
                this.BookSerial = BookSerial;
            }

            public int getBookSerialId() {
                return BookSerialId;
            }

            public void setBookSerialId(int BookSerialId) {
                this.BookSerialId = BookSerialId;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getCreateBy() {
                return CreateBy;
            }

            public void setCreateBy(String CreateBy) {
                this.CreateBy = CreateBy;
            }

            public String getShouldBackTime() {
                return ShouldBackTime;
            }

            public void setShouldBackTime(String ShouldBackTime) {
                this.ShouldBackTime = ShouldBackTime;
            }

            public int getCreateId() {
                return CreateId;
            }

            public void setCreateId(int CreateId) {
                this.CreateId = CreateId;
            }

            public String getBookTitle() {
                return BookTitle;
            }

            public void setBookTitle(String BookTitle) {
                this.BookTitle = BookTitle;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getCardNo() {
                return CardNo;
            }

            public void setCardNo(String CardNo) {
                this.CardNo = CardNo;
            }

            public String getBackTime() {
                return BackTime;
            }

            public void setBackTime(String BackTime) {
                this.BackTime = BackTime;
            }

            public String getUHF() {
                return UHF;
            }

            public void setUHF(String UHF) {
                this.UHF = UHF;
            }

            public int getReaderId() {
                return ReaderId;
            }

            public void setReaderId(int ReaderId) {
                this.ReaderId = ReaderId;
            }

            public int getContinueNum() {
                return ContinueNum;
            }

            public void setContinueNum(int ContinueNum) {
                this.ContinueNum = ContinueNum;
            }

            public Object getBookUHF() {
                return BookUHF;
            }

            public void setBookUHF(String BookUHF) {
                this.BookUHF = BookUHF;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public Object getIdentityNo() {
                return IdentityNo;
            }

            public void setIdentityNo(String IdentityNo) {
                this.IdentityNo = IdentityNo;
            }

            public Object getModifyId() {
                return ModifyId;
            }

            public void setModifyId(String ModifyId) {
                this.ModifyId = ModifyId;
            }
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getDataCount() {
            return dataCount;
        }

        public void setDataCount(int dataCount) {
            this.dataCount = dataCount;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
