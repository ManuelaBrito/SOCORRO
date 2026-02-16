package org.example;

import java.util.Scanner;

public class Nota {
double a;
double b;
double c;
double mediaF;


    public static void main(String[] args) {
        Scanner media = new Scanner(System.in);
        System.out.println("Escreva suas tres notas para efetuar a média");
        double a = media.nextDouble();
        double b = media.nextDouble();
        double c = media.nextDouble();

        double mediaF = ((a+b+c)/3);

        System.out.println("A média final é: " + mediaF);
    }
}
