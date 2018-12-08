package com.Labs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FilenameFilter;

public class CopyFiles {

    private int counter = 1;

    private void copyFileUsingStream(File source, File dest) {

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (Exception ex) {
            System.out.println("Unable to copy file:" + ex.getMessage());
        } finally {
            try {
                is.close();
                os.close();
            } catch (Exception ex) {
            }
        }

    }

    public void copyFilesAndCheckFolders(File sFile) {

        OperationsWithFile operationsWithFile = new OperationsWithFile();

        for (File fSource : getAllTxtInDir(sFile)) {
            copyAndWriteInFile(operationsWithFile, fSource, getNewFilePathAndName());
        }

        getAllFoldersFromDir(sFile);

    }

    private File[] getAllTxtInDir(File sFile) {

        File[] sourceFiles = sFile.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".txt")) {
                    return true;
                } else {
                    return false;
                }
            }

        });
        return sourceFiles;

    }

    private void getAllFoldersFromDir(File sFile) {

        File[] sourceOfAllFiles = sFile.listFiles();
        for (File folder : sourceOfAllFiles) { //find folder and run txtsearch
            if (folder.isDirectory()) {
                (Start.getFolders()).chooseSourceFolderAndStartProgram(folder);

            }
        }

    }

    private File getNewFilePathAndName() {

        String name = "var3file" + counter + ".txt";
        File fTarget = new File(new File("LABDIR"), name);
        counter++;
        return fTarget;

    }

    private void copyAndWriteInFile(OperationsWithFile operationsWithFile, File fSource, File fTarget) {

        copyFileUsingStream(fSource, fTarget);
        operationsWithFile.writeInFile(fTarget);

    }
}
