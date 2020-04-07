package com.example.springboot.Utils;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class gitCloneUtil {
    public static int JGitClone(String repositoryURL, String localPath) {
        try {
            System.out.println("git clone repository: "+repositoryURL);
            CloneCommand gitClone = Git.cloneRepository().setURI(repositoryURL).setBranch("master");
            gitClone.setDirectory(new File(localPath)).call();
            System.out.println("clone repository into: "+localPath);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int gitClone(String repositoryURL, String localPath) {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try {
            Repository repository = builder.setGitDir(new File(localPath)).readEnvironment().findGitDir().build();

            Git git = new Git(repository);
            CloneCommand clone = git.cloneRepository();


            clone.setBare(false);
            clone.setCloneAllBranches(true);
            clone.setDirectory(new File(localPath)).setURI(repositoryURL);

            clone.call();

        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public static boolean deleteDir(String path) {
        File file = new File(path);
        if (!file.exists()) {//判断是否待删除目录是否存在
            return false;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹
        for (String name : content) {
            File temp = new File(path, name);
            if (temp.isDirectory()) {//判断是否是目录
                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容
                temp.delete();//删除空目录
            } else {
                if (!temp.delete()) {//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }
        return true;
    }

    private static void downloadZip(String downloadUrl, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            URL url = new URL(downloadUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            //log.error("download error ! url :{}, exception:{}", downloadUrl, e);
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        // String repositoryURL = "https://github.com/BlankerL/DXY-COVID-19-Data";
        String repositoryURL = "https://github.com/canghailan/Wuhan-2019-nCoV";
        String localPath = "src/main/resources/static/COVID-Data";

        //String areaTimeSerialURL = "https://github.com/BlankerL/DXY-COVID-19-Data/raw/master/json/DXYArea-TimeSeries.json";
        //String overallTimeSerialURL = "https://github.com/BlankerL/DXY-COVID-19-Data/raw/master/json/DXYOverall-TimeSeries.json";
        //String tmp = "https://gitee.com/idme/DXY-COVID-19-Data/raw/master/json/DXYOverall-TimeSeries.json";
        //deleteDir("src/main/resources/static/COVID-Data");
        JGitClone(repositoryURL, localPath);
        //downloadZip(tmp, new File(localPath+"/DXYOverall-TimeSeries.json"));
    }
}
