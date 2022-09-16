package com.ly;

public class Hello3 {
    public static void main(String[] args) {
        /*int i=2;
        i=i++;
        System.out.println(i);*/
        /*byte i =15;
        int  j=8;
        int k=i+j;*/
        int total = 427;
        int a1 = 5;
        int a2 = 6;
        int a3 = 7;
        for (int i = 1; i <= 427 / a1; i++) {

            for (int j = 1; j <= 427 / a2; j++) {

                for (int k = 1; k <= 427 / a3; k++) {
                    if(i*a1+j*a2+k*a3==total){
                        if(Math.abs(i-j)<2 && Math.abs(j-k)<2)
                        System.out.printf("%3d=%3d*%3d+%3d*%3d+%3d*%3d\n",total,i,a1,j,a2,k,a3);
                    }
                }
            }
        }
    }
}
