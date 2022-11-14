package com.xunyunedu.util.pwdutil;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMd5Util {
    protected static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest = null;

    public FileMd5Util() {
    }

    public static String getFileMd5String(String filePath) throws FileNotFoundException, IOException {
        InputStream fis = new FileInputStream(filePath);
        return getFileMd5String((InputStream) fis);
    }

    public static String getFileMd5String(File file) throws IOException {
        return getFileMd5String((InputStream) (new FileInputStream(file)));
    }

    public static String getFileMd5String(InputStream fis) throws IOException {
        byte[] buffer = new byte[2048];
        boolean var2 = true;

        int length;
        while ((length = fis.read(buffer)) != -1) {
            messagedigest.update(buffer, 0, length);
        }

        return bufferToHex(messagedigest.digest());
    }

    public static String getFileMd5String(InputStream fis, Integer pre) throws IOException {
        Integer maxByte = pre * 1024 * 1024;
        Integer readedByte = 0;
        byte[] buffer = new byte[2048];
        boolean var5 = true;

        try {
            int length;
            try {
                while ((length = fis.read(buffer)) != -1) {
                    readedByte = readedByte + 2048;
                    if (readedByte > maxByte) {
                        break;
                    }

                    messagedigest.update(buffer, 0, length);
                }
            } catch (IOException var10) {
                throw new IOException();
            }
        } finally {
            if (fis != null) {
                fis.close();
                fis = null;
            }

        }

        return bufferToHex(messagedigest.digest());
    }

    public static String getFileMd5(InputStream fis, Integer pre) throws IOException {
        Integer maxByte = pre * 1024 * 1024;
        Integer readedByte = 0;
        byte[] buffer = new byte[2048];
        boolean var5 = true;

        int length;
        try {
            while ((length = fis.read(buffer)) != -1) {
                readedByte = readedByte + 2048;
                if (readedByte > maxByte) {
                    break;
                }

                messagedigest.update(buffer, 0, length);
            }
        } catch (IOException var7) {
            throw new IOException();
        }

        return bufferToHex(messagedigest.digest());
    }

    public static String getMd5String(String s) {
        return getMd5String(s.getBytes());
    }

    public static String getMd5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;

        for (int l = m; l < k; ++l) {
            appendHexPair(bytes[l], stringbuffer);
        }

        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 240) >> 4];
        char c1 = hexDigits[bt & 15];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var1) {
        }

    }
}
