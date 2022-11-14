package platform.education.oa.vo;

import platform.education.oa.model.AdjustClass;


/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-26 16:15
 */
public class AdjustClassCondition extends AdjustClass {
    private static final long serialVersionUID = 1L;

    private String searchWord;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

}
