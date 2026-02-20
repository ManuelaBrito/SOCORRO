package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

public class PosNeg {

    int numero;

    public static void main(String[] args) {
        Scanner Pos = new Scanner(System.in);

        System.out.println("Insira um número:");
        int numero = Pos.nextInt();

        if (numero >= 0) {
            System.out.println("O número " + numero + " é positivo.");
        } else {
            System.out.println("O número " + numero + " é negativo");
        }
    }}