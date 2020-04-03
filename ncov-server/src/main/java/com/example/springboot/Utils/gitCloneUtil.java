package com.example.springboot.Utils;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;

import java.io.File;

public class gitCloneUtil {
    public static int JGitClone(String repositoryURL, String localPath) {
        try {
            System.out.println("git clone repository: "+repositoryURL);
            CloneCommand gitClone = Git.cloneRepository().setURI(repositoryURL);
            gitClone.setDirectory(new File(localPath)).call();
            System.out.println("clone repository into: "+localPath);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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

    public static void main(String[] args) {
        String repositoryURL = "https://github.com/BlankerL/DXY-COVID-19-Data";
        String localPath = "src/main/resources/static/COVID-Data";
        deleteDir("src/main/resources/static/COVID-Data");
        JGitClone(repositoryURL, localPath);
    }
}
