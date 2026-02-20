package org.example;

import java.util.Scanner;

public class NomeScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nome = new String[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Digite o " + (i+1) + "° nome: ");
            nome[i] = scanner.nextLine();
        }
        System.out.println("\nTODOS OS NOMES: ");
        for (int i = 0; i < 5; i++) {
            System.out.println((i+1) + "° nome: " + nome[i]);
    }
}}
