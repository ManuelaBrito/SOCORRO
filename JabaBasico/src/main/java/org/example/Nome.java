package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Nome {
    int a;
    int b;
    int somatoria;
    int c;
    int dobro;
    int triplo;
    double raiz;
    double temp;
    double Faren;

    public static void main(String[] args) {

        Scanner soma = new Scanner(System.in);
        System.out.println("Olá Manuela Brito Migri, escolha um número para efetuar uma soma:");
        int a = soma.nextInt();
        System.out.println("Escolha um segundo número:");
        int b = soma.nextInt();

        int somatoria = (a + b);

        System.out.println("A soma de " + a + " mais " + b + " é igual a: " + somatoria);
        System.out.println("");
        System.out.println("Escreva um número para saber o dobro dele, triplo e a raiz quadrada:");
        int c = soma.nextInt();

        int dobro = (c * 2);
        int triplo = (c * 3);
        double raiz = Math.sqrt(c);

        System.out.println("O dobro de " + c + " é: " + dobro);
        System.out.println("O triplo de " + c + " é: " + triplo);
        System.out.println("A raiz quadrada de " + c + " é:" + raiz);
        System.out.println("");
        System.out.println("Escreva uma temperatura em C°, para transformar em graus Fahrenheit");
        double temp = soma.nextDouble();

        double faren = ((temp * 9/5) + 32);
        System.out.println("A temperatura em Farenheit é: " + faren);
    }
    }
