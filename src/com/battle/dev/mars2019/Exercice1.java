package com.battle.dev.mars2019;

import java.util.Scanner;

public class Exercice1 {

    public static void main(String[] args) {
        String line;
        Scanner sc = new Scanner(System.in);

        int i = 0;
        int position = 0;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            String[] elems = line.split(" ");

            if (i == 0) {
                position = Integer.parseInt(line);
            } else {
                position += Integer.parseInt(elems[0]);
                position -= Integer.parseInt(elems[1]);
            }
            i++;
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        if (position > 10000) {
            System.out.println("KO");
        } else if (position > 100) {
            System.out.println("100");
        } else {
            System.out.println("1000");
        }
    }
}
