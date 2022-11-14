package platform.education.service.vo;

import platform.education.service.model.OutSchoolActivitySummary;

import java.util.List;

/**
 * OutSchoolActivitySummary
 *
 * @author AutoCreate
 */
public class OutSchoolActivitySummaryVo extends OutSchoolActivitySummary {
    private static final long serialVersionUID = 1L;

    private List<PictureVo> pictureVoList;

    public List<PictureVo> getPictureVoList() {
        return pictureVoList;
    }

    public void setPictureVoList(List<PictureVo> pictureVoList) {
        this.pictureVoList = pictureVoList;
    }
}