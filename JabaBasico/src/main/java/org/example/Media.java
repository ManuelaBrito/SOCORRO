package org.example;

import java.util.Scanner;

public class Media {
    float nota1;
    float nota2;
    float media;

    public static void main(String[] args) {
        Scanner Aprovado = new Scanner(System.in);

        System.out.println("Qual o valor da nota 1?");
        float nota1 = Aprovado.nextFloat();

        System.out.println("Qual o valor da nota 2?");
        float nota2 = Aprovado.nextFloat();

        float media = ((nota1+nota2)/2);

        if (media >=7){
            System.out.println("PARABÉNS, VOCÊ FOI APROVADO! SUA MÉDIA É " + media);
        } else if (media >= 5 && media <7) {
            System.out.println("QUASE...");
            System.out.println("Sua media foi " + media + " Se prepare para a recuperação.");
        } else {
            System.out.println("Sinto muito, você foi reprovado...");
            System.out.println("Sua média foi: " + media);

        }
        Aprovado.close();
    }
}
