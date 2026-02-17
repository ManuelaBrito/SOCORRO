package org.example;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        double soma;
        double sub;
        double mult;
        double divi;
        Scanner num = new Scanner(System.in);

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Soma");
            System.out.println("2 - Subtração");
            System.out.println("3 - Multiplicação");
            System.out.println("4 - Divisão");
            System.out.println("0 - Encerra o programa");

            int operacao = num.nextInt();

            if (operacao == 0) {
                System.out.println("Calculadora fechada.");
                System.out.println("Até mais!");
                break;
            }

            switch (operacao) {
                case 1:
                    System.out.println("Escolha dois números para efetuar a soma:");
                    double soma1 = num.nextDouble();
                    double soma2 = num.nextDouble();

                    soma = (soma1 + soma2);

                    System.out.println("A soma de " + soma1 + " + " + soma1 + " é igual a: " + soma);
                    System.out.println("");
                    break;

                case 2:
                    System.out.println("Escolha dois números para efetuar uma subtração");
                    double sub1 = num.nextDouble();
                    double sub2 = num.nextDouble();

                    sub = (sub1 - sub2);

                    System.out.println("A subtração de " + sub1 + " - " + sub2 + " é igual a: " + sub);
                    System.out.println("");
                    break;

                case 3:
                    System.out.println("Escolha dois números para multiplicar:");
                    double mult1 = num.nextDouble();
                    double mult2 = num.nextDouble();

                    mult = (mult1 * mult2);

                    System.out.println("A multiplicação de " + mult1 + " vezes "  + mult2  + ", é igual a: " + mult);
                    System.out.println("");
                    break;

                case 4:
                    System.out.println("Escolha dois números para dividir:");
                    double divi1 = num.nextDouble();
                    double divi2 = num.nextDouble();

                    divi = (divi1/divi2);

                    System.out.println("A divisão de " + divi1 + " por " + divi2 + " é igual a: " + divi );
                    System.out.println("");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }}
