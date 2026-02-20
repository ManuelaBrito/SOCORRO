package org.example;

import java.util.Scanner;

public class ParScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha a quantidade de vetores: ");
        int quantidade = scanner.nextInt();

        int[] numero = new int[quantidade];
        
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Digite o " + (i+1) + "° número: ");
            numero[i] = scanner.nextInt();
            }

        System.out.println("Os números pares são: ");
        for (int i = 0; i <quantidade ; i++) {
            if (numero[i] % 2 == 0) {
                System.out.print(numero[i] + " ");
        }

        }
    }}

