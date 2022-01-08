package rpa.video;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Jianbo
 * @version 1.0
 * @date 2021/1/23 下午12:36
 */
public class UploadVideo {

    public static void getAllFilesAndUpload(File file) throws Exception {

        //获取.mp4文件
        File[] files = file.listFiles(pathname -> pathname.getName().toLowerCase().endsWith(".mp4"));
        if (files != null && files.length > 0) {

            for (File f : files) {

                String filename = f.getName();
                String title = filename.substring(0, filename.lastIndexOf("."));
                String uploadPath = f.toString();

                // 标题有30文字限定，重命名文件。
                uploadPath = GetNewFilePath(uploadPath, title);

                DoUpload.AddVideo2Bilibili(uploadPath);

            }
        }
    }


    public static void getAllFilesAndUploadToutiao(File file) throws Exception {
        //获取.mp4文件
        File[] files = file.listFiles(pathname -> pathname.getName().toLowerCase().endsWith(".mp4"));
        if (files != null && files.length > 0) {
            for (File f : files) {
                DoUpload.AddToutiao(f.toString());
                f.delete();
            }
        }
    }


    public static void getAllFilesAndUploadToDelete(File file) throws Exception {
        //获取.mp4文件
        File[] files = file.listFiles(pathname -> pathname.getName().toLowerCase().endsWith(".mp4"));
        if (files != null && files.length > 0) {
            for (File f : files) {
                String uploadPath = f.toString();
                DoUpload.AddVideo2Bilibili(uploadPath);
                f.delete();
            }
        }
    }







    /**
     * 通过文件路径直接修改文件名
     *
     * @param filePath    需要修改的文件的完整路径
     * @return
     */
    private static String GetNewFilePath(String filePath, String fileName) {
        File f = new File(filePath);
        if (!f.exists()) { // 判断原文件是否存在（防止文件名冲突）
            return null;
        }
        String newFilePath = "";
        String newFileName = "";

        Calendar cl = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String yyyyMMddHHmmss = sdf.format(cl.getTime());
        String HHmmss = yyyyMMddHHmmss.substring(8);
        if (fileName.length()>25){
            newFileName = HHmmss + fileName.substring(0,24);
        }else {
            newFileName = HHmmss + fileName;
        }

        newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName
                + filePath.substring(filePath.lastIndexOf("."));

        File nf = new File(newFilePath);
        try {
            f.renameTo(nf); // 修改文件名
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

}
