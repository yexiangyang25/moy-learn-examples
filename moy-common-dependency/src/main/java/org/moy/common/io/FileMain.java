package org.moy.common.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileMain {

    public static void main(String[] args) throws IOException {
        String dateFormat = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        File filePath = Paths.get(System.getProperty("java.io.tmpdir"), "loms", dateFormat).toFile();
        File file = new File(filePath, "myfile.txt");
        FileUtils.touch(file);

        System.out.println(file.getAbsolutePath());
        System.out.println("file exists:" + file.exists() + " file isDir: " + file.isDirectory() + " file isFile:" + file.isFile());
    }
}
