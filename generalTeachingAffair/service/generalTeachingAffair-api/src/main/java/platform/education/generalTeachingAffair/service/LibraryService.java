package platform.education.generalTeachingAffair.service;

/**
 * @author: yhc
 * @Date: 2021/4/2 11:52
 * @Description: 图书馆管理
 */
public interface LibraryService {

    void studentSend(String libraryACnteen, String libraryLogin, String libraryCreate);


    void teacherSend(String libraryACnteen, String libraryLogin, String libraryCreate);


}
