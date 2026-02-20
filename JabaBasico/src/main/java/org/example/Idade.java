package org.example;

import java.util.Scanner;

public class Idade {

int idade;


    public static void main(String[] args) {
        Scanner verificarIdade = new Scanner(System.in);

        System.out.println("Insira sua idade:");
        int idade = verificarIdade.nextInt();

        if (idade >= 18) {
            System.out.println("Você é maior de idade");
        } else {
            System.out.println("Você é menor de idade");
        }
    }
}
