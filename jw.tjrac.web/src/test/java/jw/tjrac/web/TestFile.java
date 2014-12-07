package jw.tjrac.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestFile {

    @Test
    public void test() {
        String dir = "E:/driver/apache-tomcat-6.0.35-windows-x64__uninstall/apache-tomcat-6.0.35/webapps/jw.tjrac.web/upload/";
        String fileName = "2f0bdd4e-d788-4d6d-99b1-a3fa29319e46.txt";
        File f = new File(dir, fileName);
        File f2 = new File("E:/eclipse/workspace/jw.tjrac.web/src/main/java/jw/tjrac/web/FormTemplateController.java");
        FileInputStream fis = null;
        if (!f.exists()) {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fis = new FileInputStream(f2);
            FileUtils.copyInputStreamToFile(fis, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
