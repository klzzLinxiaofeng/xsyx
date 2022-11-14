package platform.education.generalTeachingAffair.service;

import com.hikvision.artemis.sdk.config.ArtemisConfig;
import platform.education.generalTeachingAffair.model.*;

import java.util.List;

/**
 * @author: yhc cmb
 * @Date: 2020/9/30 11:41
 * @Description: 用于实现http请求管理接口
 */
public interface HttpService {

    void addEmploye(TeacherDetailInfo tdi, String url, Integer schoolId, Integer status, List<List<DetailVo>> postData, Integer type, final UserDetailInfo udi);

    void addHikvisionPerson(ArtemisConfig artemisConfig, String addPersonUrl, String bindCarUrl, String untieCarUrl, Integer type,String schoolYear);

    String addAndUpdateHkPersonById(ArtemisConfig artemisConfig,Integer id,boolean isStu);

    String unbindHkCar(ArtemisConfig artemisConfig,String personId,String carNo);

    String bindHkCar(ArtemisConfig artemisConfig,String personId,String carId);

    HikvisionResponse queryCarInfoByCarNo(ArtemisConfig artemisConfig, String carNo);

    HikvisionResponse queryPersonInfoById(ArtemisConfig artemisConfig,String personId);


    void addLibraryData(LibraryVo libraryVo, String libraryACnteen, String libraryLogin, String libraryCreate, TeacherDetailInfo tdi, final UserDetailInfo udi, Integer type);

    String postData(String url);
    /**
     * 请求食堂接口
     * 返回完整请求数据
     *
     * @param url
     * @return
     */
    String sendData(String url);

    void syncSendShoolBusStu(String url, String url2);


    void modifyCardSchoolBus(String url, String url2);

    /**
     * 添加班级到海康
     *  @param artemisConfig
     * @param hikvisionResourceTeamUrl
     * @param schoolIds
     */
    void addTeamHik(ArtemisConfig artemisConfig, String hikvisionResourceTeamUrl, Integer schoolIds,String schoolYear);

    String addUserToHk(boolean isStu,String empCode,String card,ArtemisConfig artemisConfig);

    /*
    * 请求广播设备ids接口
    */
    String [] findByids(String bobaoHost, String devicesIdUrl,String devicesTtsUrl);
    /*
     * 请求广播tts接口
     */
    String  findByTts(int [] ids, String text,Integer times,
                        String bobaoHost, String devicesIdUrl,String devicesTtsUrl);


    String createShitan(Integer type,String url);
}
