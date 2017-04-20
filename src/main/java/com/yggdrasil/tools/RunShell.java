package com.yggdrasil.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by yggdrasil on 2017/4/20.
 */
public class RunShell {
    public static void run(String command){
        try {
            Process ps = Runtime.getRuntime().exec(command);
            ps.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
//            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
//                sb.append(line).append("\n");
            }
//            String result = sb.toString();
//            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}