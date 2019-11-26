package com.battle.dev.novembre2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercice2 {

    public static void main(String[] args) {
        String line;
        Scanner sc = new Scanner(System.in);
        int i = 0;
        int plusCourte = 1001;
        int longueur = 0;
        int[] list = new int[4];

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            if (Integer.parseInt(line) < plusCourte) {
                plusCourte = Integer.parseInt(line);
            }
            list[i++] = Integer.parseInt(line);
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        for (int j = 0; j < 4; j++) {
            if (list[j] != plusCourte) {
                longueur += list[j] - plusCourte;
            }
        }
        System.out.println(longueur);
    }
}
