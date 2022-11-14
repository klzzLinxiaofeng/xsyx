package com.xunyunedu.paper.dao;

import com.xunyunedu.paper.pojo.OaPaperUserRead;
import org.apache.ibatis.annotations.Param;

public interface OaPaperUserReadDao {

    Integer insert(OaPaperUserRead oaPaperUserRead);
    void updateToRead(@Param("userId") Integer userId,@Param("paperId") Integer paperId);
    Integer isExists(@Param("userId") Integer userId,@Param("paperId") Integer paperId);

}
