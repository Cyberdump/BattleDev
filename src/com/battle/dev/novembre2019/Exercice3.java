package com.battle.dev.novembre2019;

import java.util.Scanner;

public class Exercice3 {

    public static void main(String[] args) {
        String line;
        Scanner sc = new Scanner(System.in);
        int i = 0;
        int nbCable = 0;
        int nbTeam = 0;
        int[][] tab = null;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            if (i == 0) {
                nbCable = Integer.parseInt(line.split(" ")[0]);
                nbTeam = Integer.parseInt(line.split(" ")[1]);
                tab = new int[nbTeam][3];
            } else {
                tab[i-1][0] = Integer.parseInt(line.split(" ")[0]);
                tab[i-1][1] = Integer.parseInt(line.split(" ")[1]);
                tab[i-1][2] = 0;
            }
            i++;
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        System.out.println(compute(tab, nbTeam, nbCable));
    }

    private static String compute(int[][] tab, int nbTeam, int nbCable) {
        StringBuilder result = new StringBuilder();
        int countCable = 1;

        for (int j = 0; j < nbTeam; j++) {
            if (tab[j][2] == 0) {
                tab[j][2] = countCable++;
                for (int k = 0; k < nbTeam; k++) {
                    if (tab[k][0] >= tab[j][1] && tab[k][2] == 0) {
                        tab[k][2] = tab[j][2];
                    } else if (tab[j][0] >= tab[k][1] && tab[k][2] == 0) {
                        tab[k][2] = tab[j][2];
                    }
                }
            }
        }

        if (countCable > nbCable) {
            return "pas possible";
        }

        for (int i = 0; i < nbTeam; i++) {
            result.append(tab[i][2]).append(" ");
        }

        return result.toString();
    }
}
