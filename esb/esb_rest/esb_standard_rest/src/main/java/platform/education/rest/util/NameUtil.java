package platform.education.rest.util;

/**
 * Created by Administrator on 2018/3/5.
 */
public class NameUtil {

    public static String getAbbrTeamName(String teamName, Integer teamNumber) {
        String regex = ".*年级.*班|.*年.*班|高.*班";
        if (teamName.matches(regex)) {
            teamName = teamNumber + "班";
        }
        return teamName;
    }
}
