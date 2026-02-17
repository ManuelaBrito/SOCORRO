package org.example;

import java.util.Scanner;

public class Tabuada {
    public static void main(String[] args) {
        Scanner tabu = new Scanner(System.in);

        System.out.println("Digite um número para ver sua tabuada:");
        int num = tabu.nextInt();


        int multiplique = 1;
        while (multiplique <= 10) {
            int resultado = num * multiplique;
            System.out.println("A tabuada de " + num + " X " + multiplique + " é: " + resultado);
            multiplique++;
        }

        tabu.close();
    }
}
