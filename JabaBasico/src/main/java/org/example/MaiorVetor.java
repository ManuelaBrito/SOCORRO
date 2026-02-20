package org.example;

import java.util.Scanner;

public class MaiorVetor {
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);

        int[] maior = new int [5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Digite o " + (i+1) + "° numero");
            maior[i] = num.nextInt();
        }

        int max =0;
        for (int i = 0; i <5; i++) {
            if (maior[i]>max) {
                max = maior[i];
            }
        }
        System.out.println("\nO maior número é: " + max);
    }
}
