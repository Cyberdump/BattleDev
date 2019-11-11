package com.battle.dev.mars2019;

import java.util.Scanner;

public class Exercice2 {

    public static void main(String[] args) {
        String line;
        Scanner sc = new Scanner(System.in);

        int i = 0;
        int nbAllerRetour = 0;
        int poids = 0;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            if (i != 0) {
                poids += Integer.parseInt(line);
                if (poids > 100) {
                    nbAllerRetour++;
                    poids = Integer.parseInt(line);
                }
            }
            i++;
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        System.out.println(++nbAllerRetour);
    }
}
