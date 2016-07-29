package com.application.entity;
public class VariantTest{

        public static int staticVar = 0;
        public int instanceVar = 0;

        public VariantTest(){
               staticVar++;
               instanceVar++;
              System.out.println("staticVar:"+staticVar+"\n instanceVar:"+instanceVar);
        }
}