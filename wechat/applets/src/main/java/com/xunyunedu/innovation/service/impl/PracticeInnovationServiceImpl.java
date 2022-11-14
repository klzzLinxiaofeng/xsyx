package com.xunyunedu.innovation.service.impl;

import com.xunyunedu.character.pojo.Picture;
import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.innovation.dao.PracticeInnovationDao;
import com.xunyunedu.innovation.pojo.PracticeInnovation;
import com.xunyunedu.innovation.service.PracticeInnovationService;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.personinfor.pojo.PublicClassPojo;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PracticeInnovationServiceImpl implements PracticeInnovationService {
    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;
    @Autowired
    private PracticeInnovationDao practiceInnovationDao;
    @Override
    public PracticeInnovation findByStudentIdAll(Integer studentId, Integer schoolId) {
        PracticeInnovation practiceInnovation =new PracticeInnovation();
       PracticeInnovation practiceInnovation2=practiceInnovationDao.findByPraInner(studentId,schoolId);
        if(practiceInnovation2==null){
            Student student=practiceInnovationDao.findByStudentId(studentId,schoolId);
            if(student.getName()!=null){
                practiceInnovation.setStudentName(student.getName());
            }
        }else {
                practiceInnovation.setStudentName(practiceInnovation2.getStudentName());
                practiceInnovation.setStudentId(practiceInnovation2.getStudentId());
            practiceInnovation.setTeamJiaDay(practiceInnovation2.getTeamJiaDay());
            practiceInnovation.setPingyu(practiceInnovation2.getPingyu());
            practiceInnovation.setJiangzhuanId(practiceInnovation2.getJiangzhuanId());
            practiceInnovation.setPctreId(practiceInnovation2.getPctreId());
            practiceInnovation.setScore(practiceInnovation2.getScore());
                if (practiceInnovation2.getPctreId() != null) {
                    String [] str=practiceInnovation2.getPctreId().split(",");
                    List<Picture> pictureList=new ArrayList<>();
                    for(int a=0;a<str.length;a++) {
                        Picture picture = new Picture();
                        // 根据PctreId查询图片的url
                        EntityFile file = uploaderDao.findFileByUUID(practiceInnovation.getPctreId());
                        if (file != null) {
                            picture.setPictureId(str[a]);
                            picture.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));

                        }
                        pictureList.add(picture);

                    }
                    practiceInnovation.setPictureList(pictureList);
                }

                if (practiceInnovation2.getJiangzhuanId() != null) {
                    String[] str = practiceInnovation2.getJiangzhuanId().split(",");
                    List<Picture> pictureList = new ArrayList<>();
                    for (int a = 0; a < str.length; a++) {
                        Picture picture = new Picture();
                        // 根据uuid查询奖状的url
                        EntityFile file = uploaderDao.findFileByUUID(practiceInnovation.getJiangzhuanId());
                        if (file != null) {
                            picture.setPictureId(str[a]);
                            picture.setPictureUrl(ftpUtils.relativePath2HttpUrl(file));
                        }
                        pictureList.add(picture);
                    }
                    practiceInnovation.setPictureListTwo(pictureList);
                }
            }

            List<PublicClassPojo> publicClasses = practiceInnovationDao.fidByAllPublicClass(studentId, schoolId);
            if (publicClasses.size() > 0) {
                for (PublicClassPojo publicClass : publicClasses) {
                    setUrl(publicClass);
                }
                practiceInnovation.setPublicClassList(publicClasses);
            }

        return practiceInnovation;
    }

    /**
     * 设置当前课程对应的封面
     *
     * @param publicClassPojo
     */
    public void setUrl(PublicClassPojo publicClassPojo) {
        String coverUuid = publicClassPojo.getCoverUuid();
        if (coverUuid != null && !("").equals(coverUuid)) {
            // 根据uuid查询封面的url
            EntityFile file = uploaderDao.findFileByUUID(coverUuid);
            if (file != null) {
                publicClassPojo.setCoverUrl(ftpUtils.relativePath2HttpUrl(file));
            }
        }
    }
}
