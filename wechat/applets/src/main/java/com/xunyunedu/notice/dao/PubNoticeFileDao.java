package com.xunyunedu.notice.dao;

import com.xunyunedu.notice.pojo.PubNoticeFile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface PubNoticeFileDao {


    Integer insert(PubNoticeFile pubNoticeFile);

    PubNoticeFile selectById(Integer id);


    List<PubNoticeFile> selectByNoticeId(Integer noticeId);

}
