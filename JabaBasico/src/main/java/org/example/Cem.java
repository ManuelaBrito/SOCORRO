package org.example;

public class Cem {
    public static void main(String[] args) {

        int conta = 0;
        int i = 0;
        while (i <= 100){
            int soma = conta + i;
            System.out.println("Conta: " + conta + " + " + i + " = " + soma);
            i++;
            conta++;
        }
    }
}
