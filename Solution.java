package com.javarush.task.task19.task1918;

/*
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Solution {
    public static void main(String[] args) throws IOException {
//        String file = "E:/JavaRush/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/Tag.txt";
        String teg = "<" + args[0];
        String endTeg = "</" + args[0] + ">";

        List<Integer> indexI = new ArrayList<>();
        List<Integer> indexJ = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        StringBuilder content = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(file));
        while(br.ready()) content.append((char) br.read());
        br.close();

        String fileContent = content.toString();
        Pattern p1 = Pattern.compile(teg);
        Pattern p2 = Pattern.compile(endTeg);
        Matcher m1 = p1.matcher(fileContent);
        Matcher m2 = p2.matcher(fileContent);

        int i = 0;
        while (m1.find()) {
            i = fileContent.indexOf(m1.group(), i + 1);
            indexI.add(i);
        }

        i = 0;
        while (m2.find()) {
            i = fileContent.indexOf(m2.group(), i + 1);
            indexJ.add(i);
        }

        List<String> result = result(indexI, indexJ, fileContent, endTeg.length());

        for(String rr : result) {
            System.out.println(rr);
        }

    }

    public static List<String> result(List<Integer> indexI, List<Integer> indexJ, String fileContent, int length) {
        List<String> result = new LinkedList();
        String str;
        for(int i = 0; i < indexI.size(); i++) {
            int j = returnEnd(indexI, indexJ, indexI.get(i), fileContent);
            str = fileContent.substring(indexI.get(i) , j + length);
            result.add(str);
        }

        return result;
    }

     public static int returnEnd(List<Integer> indexI, List<Integer> indexJ, int startLit, String fileContent) {
        int flag = 0;
         for(int i = startLit; i < fileContent.length(); i++) {
             if(indexI.contains(i)) {
                 flag++;
             }
             if(indexJ.contains(i)){
                 flag--;
                 if(flag == 0) return i;
             }
         }
         return -1;
     }
}
