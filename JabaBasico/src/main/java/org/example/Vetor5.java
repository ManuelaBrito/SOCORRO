package org.example;

import java.util.Scanner;

public class Vetor5 {

    public static void main(String[] args) {
        Scanner vetor = new Scanner(System.in);

        int[] num = new int[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Digite o " + (i+1)+ "° numero: ");
            num[i] = vetor.nextInt();
        }
        System.out.println("\nNúmeros digitados:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Posição " + i + ": " + num[i]);
        }

    }

}
