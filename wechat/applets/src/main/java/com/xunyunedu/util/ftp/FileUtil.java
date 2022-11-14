package com.xunyunedu.util.ftp;


import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public abstract class FileUtil {
    public static final int BUFFER_SIZE = 4096;

    public FileUtil() {
    }

    public static List<File> getDirFiles(String dirPath) {
        return getDirFiles(new File(dirPath));
    }

    public static List<File> getDirFiles(File dirFile) {
        File[] fileArr = dirFile.listFiles();
        List<File> files = new ArrayList();
        File[] arr$ = fileArr;
        int len$ = fileArr.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            File f = arr$[i$];
            if (f.isFile()) {
                files.add(f);
            }
        }

        return files;
    }

    public static long getFileSize(File file) throws IOException {
        long size = 0L;
        if (file.exists() && file.isFile()) {
            FileChannel fc = null;
            FileInputStream fis = null;

            try {
                fis = new FileInputStream(file);
                fc = fis.getChannel();
                size = fc.size();
            } finally {
                if (fc != null) {
                    fc.close();
                    fc = null;
                }

                if (fis != null) {
                    fis.close();
                    fis = null;
                }

            }
        }

        return size;
    }

    public static boolean deleteFolder(File file) {
        if (file.isDirectory()) {
            String[] children = file.list();

            for (int i = 0; i < children.length; ++i) {
                boolean success = deleteFolder(new File(file, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return file.delete();
    }

    public static boolean createFile(File file) throws IOException {
        if (!file.exists()) {
            boolean success = createFolder(file.getParentFile());
            return !success ? false : file.createNewFile();
        } else {
            return true;
        }
    }

    public static void writeFile(File file, String content, boolean append) throws IOException {
        FileWriter writer = null;
        BufferedWriter bufferWritter = null;

        try {
            createFile(file);
            writer = new FileWriter(file, append);
            bufferWritter = new BufferedWriter(writer, 4096);
            bufferWritter.write(content);
        } finally {
            if (writer != null) {
                writer.close();
                writer = null;
            }

            if (bufferWritter != null) {
                bufferWritter.close();
                bufferWritter = null;
            }

        }

    }

    public static void writeFile(File file, InputStream in, boolean append) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;

        try {
            createFile(file);
            fos = new FileOutputStream(file, append);
            byte[] buf = new byte[4096];
            bis = new BufferedInputStream(in);

            int size;
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }

            if (bis != null) {
                bis.close();
            }

        }

    }

    public static boolean createFolder(File file) {
        if (!file.getParentFile().exists()) {
            boolean success = createFolder(file.getParentFile());
            if (!success) {
                return false;
            }
        }
        return file.mkdir();
    }

    public static String pathProcessor(String path) {
        path = path.replace("//", "/");
        if (path.contains("///")) {
            path = path.replace("///", "/");
        }

        if (path.contains("//")) {
            path = path.replace("//", "/");
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        if (!path.endsWith("/")) {
            path = path + "/";
        }

        return path;
    }
}
