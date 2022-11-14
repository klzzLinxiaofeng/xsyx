package platform.szxyzxx.knowledge.vo;


import java.util.List;

/*
* 成长档案导出知识点vo
*/
public class PdfVo {
    /*
    * 课本id
    */
    private Integer id;
    /*
     * 课本名称
     */
    private String bookName;
    /*
    * 课本需合并行数
    */
    private Integer hangNumber;
    private List<KnowEvaluation> list;


    public Integer getHangNumber() {
        return hangNumber;
    }

    public void setHangNumber(Integer hangNumber) {
        this.hangNumber = hangNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<KnowEvaluation> getList() {
        return list;
    }

    public void setList(List<KnowEvaluation> list) {
        this.list = list;
    }
}
