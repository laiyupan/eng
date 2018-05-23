/**
 * 
 */
package com.eng.one.nine.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * @author laiyp
 * @date 2018年4月23日
 *
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

	public static File MultipartFileToFile(MultipartFile file) {
		
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件后缀
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		// 用uuid作为文件名，防止生成的临时文件重复
		File excelFile = null;
		try {
			excelFile = File.createTempFile(fileName, prefix);
			// MultipartFile to File
			file.transferTo(excelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return excelFile;
		

	}
	
	/**
     * 解压文件到指定目录
     *
     * @param //zipFile 目标文件
     * @param //descDir 解压目录
     * @author isDelete 是否删除目标文件
     */
    @SuppressWarnings("unchecked")
    public static String unZipFiles(String zipFilePath, String fileSavePath, boolean isDelete) {
        boolean isUnZipSuccess = true;
        try {
            (new File(fileSavePath)).mkdirs();
            File f = new File(zipFilePath);
            if ((!f.exists()) && (f.length() <= 0)) {
                throw new RuntimeException("not find "+zipFilePath+"!");
            }
            //一定要加上编码，之前解压另外一个文件，没有加上编码导致不能解压
            ZipFile zipFile = new ZipFile(f, "utf-8");
            String gbkPath, strtemp;
            Enumeration<ZipEntry> e = zipFile.getEntries();
            while (e.hasMoreElements()) {
                org.apache.tools.zip.ZipEntry zipEnt = e.nextElement();
                gbkPath = zipEnt.getName();
                strtemp = fileSavePath + File.separator + gbkPath;
               
                if (zipEnt.isDirectory()) { //目录
                    File dir = new File(strtemp);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                } else {
                    // 读写文件
                    InputStream is = zipFile.getInputStream(zipEnt);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    // 建目录
                    String strsubdir = gbkPath;
                    for (int i = 0; i < strsubdir.length(); i++) {
                        if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
                            String temp = fileSavePath + File.separator
                                    + strsubdir.substring(0, i);
                            File subdir = new File(temp);
                            if (!subdir.exists())
                                subdir.mkdir();
                        }
                    }
                    FileOutputStream fos = new FileOutputStream(strtemp);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int len;
                    byte[] buff = new byte[5120];
                    while ((len = bis.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }
                    bos.close();
                    fos.close();
                }
                break;
            }
            zipFile.close();
        } catch (Exception e) {
//            logger.error("解压文件出现异常：", e);
            isUnZipSuccess = false;
            System.out.println("extract file error: " + zipFilePath);
            //fileOperateUtil.WriteStringToFile(fileOperateUtil.logPath, "extract file error: " + zipFilePath);
        }
        /**
         * 文件不能删除的原因：
         * 1.看看是否被别的进程引用，手工删除试试(删除不了就是被别的进程占用)
         2.file是文件夹 并且不为空，有别的文件夹或文件，
         3.极有可能有可能自己前面没有关闭此文件的流(我遇到的情况)
         */
        if (isDelete && isUnZipSuccess) {
            boolean flag = new File(zipFilePath).delete();
            
//            logger.debug("删除源文件结果: " + flag);
            //fileOperateUtil.WriteStringToFile(fileOperateUtil.logPath, "delete " + zipFilePath + "result: " + flag);
        }
        return fileSavePath;
        
//        logger.debug("compress files success");
    }
    
    public static boolean isFile(String path){
    	File file=new File(path);    
        if(!file.exists())    
        {
        	return false;
        }  
        return true;
    }
    
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        BufferedReader br = null;
        FileReader fileReader=null;
        try{
        	fileReader=new FileReader(file);
        	br=new BufferedReader(fileReader);//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }finally {
        	try {
				fileReader.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return result.toString();
    }
     
	

	/**
	 * 删除
	 * 
	 * @param files
	 */
	public static void deleteFile(File... files) {
		for (File file : files) {
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
}
