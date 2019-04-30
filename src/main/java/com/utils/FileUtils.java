package com.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {
    /**
     * 下载文件的接口
     * @param filePath
     * @param response
     * @param request
     * @return
     */
    public static String downloadFile(String filePath, HttpServletResponse response, HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String downPath = filePath;

        try{
            File file = new File(filePath);
            long fileLength = file.length();
            String fileName = file.getName();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[2048];
            int bytesRead = 0;
            while (-1 != (bytesRead = bis.read(buffer, 0, buffer.length))) {// 开始输出文件
                bos.write(buffer, 0, bytesRead);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {// 出现异常无论如何都要关闭输入输出流
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return null;
    }

}
