package com.Labs;

public class Start {

    private static Folders copyTxt = new Folders();

    public static void start() {

        copyTxt.createNewFolder("LABDIR");
        copyTxt.chooseSourceFolderAndStartProgram();

    }

    public static Folders getFolders() {

        return copyTxt;

    }
}
