package com.battle.dev.novembre2019;

import java.util.Scanner;

public class Exercice1 {

    public static void main(String[] args) {
        String line;
        Scanner sc = new Scanner(System.in);
        int i = 0;
        String perdant = "";
        int longueur = 1001;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            if (i != 0) {
                String[] tab = line.split(" ");
                if (Integer.parseInt(tab[1]) < longueur ) {
                    longueur = Integer.parseInt(tab[1]);
                    perdant = tab[0];
                }
            }
            i++;
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        System.out.println(perdant);
    }
}
