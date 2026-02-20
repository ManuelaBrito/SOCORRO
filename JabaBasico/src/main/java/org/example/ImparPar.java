package org.example;

import java.util.Scanner;

public class ImparPar {

    int numero;
    int numero1;
    int verificar;

    public static void main(String[] args) {
        Scanner positivo = new Scanner(System.in);
        System.out.println("Digite um número:");
        int numero = positivo.nextInt();

        if ( numero % 2 == 0) {
            System.out.println(numero + " Este número é par.");
        }else{
                System.out.println(numero + " Este número é impar.");
            }
        }
    }
