package platform.szxyzxx.web.common.util;


public class MultiDomainResolver {


    public static String resolver(String serverName, String basePath) {
        if(basePath.contains(";")) {
            String[] basePaths = basePath.split(";");

            if (basePaths.length > 1) {
                for (String path : basePaths) {

                    if (path.contains(serverName)) {
                        basePath = path.substring(path.indexOf("=") + 1, path.length());
                    }

                }
            } else {
                basePath.replace(";", "");
            }

        }

        return basePath;
    }


}
