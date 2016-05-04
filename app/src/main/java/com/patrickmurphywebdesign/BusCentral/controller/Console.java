package com.patrickmurphywebdesign.BusCentral.controller;

/**
 * Created by turnerp on 5/2/2016.
 */
public class Console {
    public static void log(String s){
        System.out.println(s);
    }
    public static void log(int in){
        Integer temp  = new Integer(in);
        System.out.println(temp.toString());
    }
    public static void log(boolean s){
        System.out.println(s);
    }
}
