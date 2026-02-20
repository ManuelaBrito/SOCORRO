package org.example;

import java.util.Scanner;

public class Zero {
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);

        System.out.println("Tudo menos Zero");
        int numero = num.nextInt();

        while (numero != 0) {
            numero = num.nextInt();
    }
        System.out.println("Vc digitou zero");
}}
