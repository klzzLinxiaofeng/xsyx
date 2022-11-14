package com.ws;


import java.io.*;

public class ErweiMa {
    private static final String toPdfTool = "D:\\wkhtml\\wkhtmltopdf\\bin\\wkhtmltopdf.exe";
    /*public static void main(String[] args) throws ParseException {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String str="2021-12-08T08:27";

        String endTime=str.substring(0,str.indexOf("T"));
        String endTimeShiFen=str.substring(str.indexOf("T")+1,str.length());
        Date date22= sdf.parse(endTimeShiFen);
        Date date1= sdf2.parse(endTime);
        Date date5= sdf2.parse(sdf2.format(date));
        Date date6= sdf.parse(sdf.format(date));
        System.out.println(sdf2.format(date));

        if(date22.compareTo(date6) < 0 && date1.compareTo(date5) > 0){

            System.out.println("开始播报");
        }


    }*/




         /**
         6  * Created by jstarseven on 2017/7/25.
         7  */
      //wkhtmltopdf在系统中的路径


             /**
 13      * html转pdf
 14      *
 15      * @param srcPath  html路径，可以是硬盘上的路径，也可以是网络路径
 16      * @param destPath pdf保存路径
 17      * @return 转换成功返回true
 18      */
             public static boolean convert(String srcPath, String destPath) {
                         File file = new File(destPath);
                         File parent = file.getParentFile();
                        //如果pdf保存路径不存在，则创建路径
                        if (!parent.exists())
                                 parent.mkdirs();
                         StringBuilder cmd = new StringBuilder();
                         cmd.append(toPdfTool);
                       cmd.append(" ");
                        cmd.append(srcPath);
                        cmd.append(" ");
                        cmd.append(destPath);
                         boolean result = true;
                         try {
                                Process proc = Runtime.getRuntime().exec(cmd.toString());
                                 HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
                                 HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
                                 error.start();
                                output.start();
                                 proc.waitFor();
                             } catch (Exception e) {
                                 result = false;
                              e.printStackTrace();
                             }

                         return result;
                     }

             public static void main(String[] args) {
                         convert("file:///D:/WX/WeChat%20Files/wxid_fwbj0b1lj14k22/FileStorage/File/2021-12/%E6%88%90%E9%95%BF%E6%A1%A3%E6%A1%88/trackRecord.html", "E:\\data\\pdf\\jstarseven.pdf");
                     }
 }

         class HtmlToPdfInterceptor extends Thread {
            private InputStream is;

             public HtmlToPdfInterceptor(InputStream is) {
                         this.is = is;     }

             public void run() {
                         try {
                                 InputStreamReader isr = new InputStreamReader(is, "utf-8");
                                 BufferedReader br = new BufferedReader(isr);
                                 String line;
                                 while ((line = br.readLine()) != null) {
                                         System.out.println(line); //输出内容
                                     }
                             } catch (IOException e) {
                                 e.printStackTrace();
                }
                     }
 }
