package com.Labs;

import java.io.File;
import java.util.Scanner;

public class Folders {

    private CopyFiles copyFiles = new CopyFiles();

    public void createNewFolder(String folder) {

        new File(folder).mkdir();

    }

    private String selectSourceFolder() {

        Scanner input = new Scanner(System.in);
        return input.nextLine();

    }

    public void chooseSourceFolderAndStartProgram() {

        System.out.print("Enter source directory: ");
        boolean rightDirectory = false;
        String dir;
        File sourceFolder;

        while(!rightDirectory) {
            try {
                dir = selectSourceFolder();
                File directory = new File(dir);
                if(directory.isDirectory()){
                    sourceFolder = new File(dir);
                    OperationsWithFile.setFormatType();
                    copyFiles.copyFilesAndCheckFolders(sourceFolder);
                    rightDirectory = true;
                } else {
                    System.out.print("Please, choose right directory: ");
                }
            } catch (Exception e) {
                System.out.print("Exception in dir. select");
            }
        }

    }

    public void chooseSourceFolderAndStartProgram(File folder) {
        copyFiles.copyFilesAndCheckFolders(folder);
    }
}
