package com.Labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class OperationsWithFile {

    private static int formatType = 0;

    private void format(int type, StringBuilder text) {

        boolean rightFormat = false;
        while (!rightFormat) {
            if (type == 1 && !rightFormat) {
                formatType1(text);
                rightFormat = true;
            } else if (type == 2 && !rightFormat) {
                formatType2(text);
                rightFormat = true;
            } else if (type == 3 && !rightFormat) {
                formatType3(text);
                rightFormat = true;
            } else if (type == 4 && !rightFormat) {
                formatType4(text);
                rightFormat = true;
            } else if (type == 5 && !rightFormat) {
                formatType5(text);
                rightFormat = true;
            } else {
                System.out.println("Wrong format type. Please enter existing one (number 1 - 5)");
            }

        }

    }

    private void formatType1(StringBuilder format) {

        String lines = format.toString().replaceAll(" ", "");
        format.delete(0, format.length());
        format.append(lines);

//        for (int i = 0; i < format.length(); i++) {
//            if (format.charAt(i) == ' ') {
//                format.deleteCharAt(i);
//                i--;
//            }
//        }

    }

    private void formatType2(StringBuilder format) {
        //Make all words with 'd' underline

        String lines = format.toString().replaceAll("\\b\\w*d+\\w*\\b", "<S>$0</S>");
        format.delete(0, format.length());
        format.append(lines);

//        findWordAndSurround(format, "d", "<S>", "</S>");

    }

    private void formatType3(StringBuilder format) {
        //Make all words with 'a' underline

        String lines = format.toString().replaceAll("\\b\\w*a+\\w*\\b", "<B>$0</B>");
        format.delete(0, format.length());
        format.append(lines);

//        findWordAndSurround(format, "a", "<B>", "</B>");
    }

    private void formatType4(StringBuilder format) {
        //Delete all words with 'e'

        String lines = format.toString().replaceAll("\\b\\w*e+\\w*\\b", "");
        format.delete(0, format.length());
        format.append(lines);

//        findWordAndDelete(format);
    }

    private void formatType5(StringBuilder format) {
        //If word starts with capital char make it underline

        String lines = format.toString().replaceAll("\\b[A-Z]\\w*\\b", "<S>$0</S>");
        format.delete(0, format.length());
        format.append(lines);

//        underlineCapitalWords(format);
    }

    public void writeInFile(File fTarget) {

        try {
            StringBuilder text = new StringBuilder(new Scanner(fTarget).useDelimiter("\\Z").next());
            format(formatType, text);
            writer(fTarget, text);
        } catch (Exception e) {
            writer(fTarget, new StringBuilder());
        }

    }

    private void writer(File fTarget, StringBuilder format) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fTarget);
            writer.println("<html><body>");
            writer.println(format);
            writer.println("</body></html>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }

    }

    public static void setFormatType() {
        boolean rightFormat = false;
        int type;
        System.out.println("1) Remove all \'Space\'");
        System.out.println("2) Make all words with \'d\' underline");
        System.out.println("3) Make all words with \'a\' bold");
        System.out.println("4) Delete all words with \'e\'");
        System.out.println("5) If word starts with capital char make it underline");
        System.out.print("Choose number of the format type: ");
        while(!rightFormat) {
            try {
                Scanner scan = new Scanner(System.in);
                type = scan.nextInt();
                if (type > 0 && type < 6) {
                    rightFormat = true;
                    formatType = type;
                    break;
                }
                System.out.print("Format type not found. Please, write only number 1-5: ");
            } catch (Exception e) {
                System.out.print("Format type not found. Please, write only number 1-5: ");
            }
        }
    }

//    private int findWordStart(int i, StringBuilder sb) {
//        while (i >= 0 && sb.charAt(i) != ' ') i--;
//        return i + 1;
//    }
//
//    private int findWordEnd(int i, StringBuilder sb) {
//        while (i < (sb.length() - 1) && sb.charAt(i) != ' ') i++;
//        return i;
//    }
//
//    private void findWordAndSurround(StringBuilder format, String letter, String bWord, String aWord) {
//        StringBuilder newText = new StringBuilder();
//        String[] lines = format.toString().split("\\n");
//        for (String s : lines) {
//            StringBuilder line = new StringBuilder(s);
//            StringBuilder lineNew = new StringBuilder(line);
//            int index = line.indexOf(letter);
//            if (index == -1) {
//                newText.append(line);
//                newText.append("\n");
//            } else {
//                int fromIndex = 0;
//                while (index != -1) {
//                    index = lineNew.indexOf(letter, fromIndex);
//                    if (index != -1) {
//                        int wordStarts = findWordStart(index, lineNew);
//                        int wordEnds = findWordEnd(index, lineNew);
//                        lineNew.insert(wordStarts, bWord);
//                        lineNew.insert(wordEnds + 3, aWord);
//                        fromIndex = wordEnds + 8;
//                    }
//                }
//                newText.append(lineNew);
//                newText.append("\n");
//            }
//        }
//        format.delete(0, format.length());
//        format.append(newText);
//    }
//
//    private void findWordAndDelete(StringBuilder format) {
//
//        StringBuilder newText = new StringBuilder();
//        String[] lines = format.toString().split("\\n");
//        for (String s : lines) {
//            StringBuilder line = new StringBuilder(s);
//            StringBuilder lineNew = new StringBuilder(line);
//            int index = line.indexOf("e");
//            if (index == -1) {
//                newText.append(line);
//                newText.append("\n");
//            } else {
//                while (index != -1) {
//                    index = lineNew.indexOf("e");
//                    if (index != -1) {
//                        deleteWord(lineNew, index);
//                    }
//                }
//                newText.append(lineNew);
//                newText.append("\n");
//            }
//        }
//        format.delete(0, format.length());
//        format.append(newText);
//    }
//
//    private void deleteWord(StringBuilder lineNew, int index) {
//        int wordStarts = findWordStart(index, lineNew);
//        int wordEnds = findWordEnd(index, lineNew);
//        index = wordStarts;
//        int i = 0;
//        while (i <= (wordEnds - wordStarts) && index < (lineNew.length() - 1)) {
//            lineNew.deleteCharAt(index);
//            i++;
//        }
//    }
//
//    private void underlineCapitalWords(StringBuilder format) {
//
//        StringBuilder newText = new StringBuilder();
//        String[] lines = format.toString().split("\\n");
//        for (String s : lines) {
//            StringBuilder line = new StringBuilder(s);
//            StringBuilder lineNew = new StringBuilder(line);
//            int fromIndex = 0;
//            int index = getCapitalWord(lineNew, fromIndex);
//            if (index == -1) {
//                newText.append(line);
//                newText.append("\n");
//            } else {
//                while (index != -1) {
//                    index = getCapitalWord(lineNew, fromIndex);
//                    if (index != -1) {
//                        int wordStarts = findWordStart(index, lineNew);
//                        int wordEnds = findWordEnd(index, lineNew);
//                        lineNew.insert(wordStarts, "<S>");
//                        lineNew.insert(wordEnds + 3, "</S>");
//                        fromIndex = wordEnds + 8;
//                    }
//                }
//                newText.append(lineNew);
//                newText.append("\n");
//            }
//        }
//        format.delete(0, format.length());
//        format.append(newText);
//    }
//
//    private int getCapitalWord(StringBuilder lineNew, int index) {
//
//        while (index < lineNew.length() - 1 && index >= 0) {
//            if (index == 0 && Character.isUpperCase(lineNew.charAt(index))) {
//                return index;
//            } else if (!(index - 1 < 0) && lineNew.charAt(index - 1) == ' ' && Character.isUpperCase(lineNew.charAt(index))) {
//                return index;
//            } else {
//                index++;
//            }
//        }
//        return -1;
//    }
}

