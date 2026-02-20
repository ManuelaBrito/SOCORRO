package org.example;

import java.util.Scanner;

public class Vetorsoma {
    int soma1;
    static int num;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] soma = new int[5];
        int numero = 0;

        for (int i = 0; i < 5 ; i++) {
            System.out.println("Digite o " + (i+1) + "° numero");
            soma[i] = scanner.nextInt();
        }
        for (int i = 0; i < 5; i++) {
            numero = numero + soma[i];
        }
        System.out.println("A soma é: " + numero);



    }
}
