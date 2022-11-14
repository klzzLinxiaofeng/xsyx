package com.xunyunedu.resource.util;

import com.xunyunedu.resource.constant.IconType;

public class IconUtil {
    public IconUtil() {
    }

    public static Integer setIcon(String suffix) {
        suffix = suffix.toLowerCase();
        if (!suffix.equals("doc") && !suffix.equals("docx")) {
            if (!suffix.equals("xls") && !suffix.equals("xlsx")) {
                if (!suffix.equals("ppt") && !suffix.equals("pptx")) {
                    if (!suffix.equals("jpg") && !suffix.equals("jpeg")) {
                        if (suffix.equals("png")) {
                            return IconType.PNG;
                        } else if (suffix.equals("gif")) {
                            return IconType.GIF;
                        } else if (!suffix.equals("mp4") && !suffix.equals("mkv") && !suffix.equals("avi") && !suffix.equals("rm") && !suffix.equals("rmvb") && !suffix.equals("wav") && !suffix.equals("wkv") && !suffix.equals("wmv")) {
                            if (suffix.equals("pdf")) {
                                return IconType.PDF;
                            } else if (suffix.equals("mp3")) {
                                return IconType.MP3;
                            } else if (suffix.equals("rar")) {
                                return IconType.RAR;
                            } else if (!suffix.equals("flv") && !suffix.equals("f4v")) {
                                if (suffix.equals("lads")) {
                                    return IconType.LADS;
                                } else {
                                    return suffix.equals("swf") ? IconType.SWF : IconType.RAR;
                                }
                            } else {
                                return IconType.FLV;
                            }
                        } else {
                            return IconType.VIEDO;
                        }
                    } else {
                        return IconType.JPG;
                    }
                } else {
                    return IconType.PPT;
                }
            } else {
                return IconType.XLS;
            }
        } else {
            return IconType.DOC;
        }
    }
}
