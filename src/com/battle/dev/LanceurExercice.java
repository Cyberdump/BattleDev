package com.battle.dev;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LanceurExercice {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final PrintStream originOut = System.out;
    private static Path repertoire;

    public static void main(String[] argv) throws Exception {
        if (argv.length != 2) throw new Exception("Nombre de paramétre invalide");
        repertoire = Paths.get(argv[1]);
        if (!Files.isDirectory(repertoire)) throw new Exception("Le chemin mis en paramétre n'est pas valide");

        Stream<String> filesIn = Files.list(repertoire)
                .filter(path -> path.getFileName().toString().startsWith("input"))
                .map(Path::toString);

        filesIn.forEachOrdered(file -> {
            try (InputStream input = new FileInputStream(file)) {
                int numInput = getNumeroEntree(file);
                System.out.println("Entrée numéro : " + numInput);
                System.setIn(input);
                String result = lancerExercice(argv[0]);
                if (result != null && !result.isBlank()) {
                    if (!readResult(result, numInput)) System.exit(1);
                } else {
                    System.out.println(ANSI_RED + "Assessment error:" + ANSI_RESET + " Votre code n'a rien renvoyé");
                    System.exit(2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static int getNumeroEntree(String fileName) {
        String sepatator = "\\\\";
        String[] t = fileName.split(sepatator);
        String name = t[t.length-1];
        return Integer.parseInt(name.replace("input", "").replace(".txt", ""));
    }

    private static boolean readResult(String result, int numInput) throws IOException {
        String file = getOutputfile(numInput);
        String[] resultByLine = result.split("\n");
        try (InputStream output = new FileInputStream(file)) {
            String line;
            Scanner sc = new Scanner(output);
            int i = 0;
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                System.out.println("Votre code a renvoyé : " +  resultByLine[i]);
                if (!resultByLine[i].startsWith(line)) {
                    System.out.println("Résultat attendu : " + line);
                    System.out.println(ANSI_RED + "Assessment error:" + ANSI_RESET + " Vous n'avez pas bien déterminé le résultat du pari.");
                    return false;
                }
                System.out.println(ANSI_GREEN + "Assessment success:" + ANSI_RESET + " Vous avez bien déterminé le résultat du pari.");
            }
        }

        return true;
    }

    private static String getOutputfile(int num) throws IOException {
        Optional<Path> filesOut = Files.list(repertoire)
                .filter(path -> path.getFileName().toString().startsWith("output" + num))
                .findFirst();

        return filesOut.map(Path::toString).orElse(null);
    }

    private static String lancerExercice(String exerciceClass) {
        try {
            // Initialisation de la sortie pour lire les résultats
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            // Lancement de l'exercice
            Class<?> c = Class.forName(exerciceClass);
            Method m = c.getMethod("main", String[].class);
            m.invoke(c, (Object) new String[0]);
            // Lecture des resultats
            String result = out.toString().replaceAll("\n$", "");
            // Fermeture du flux
            out.close();
            System.setOut(originOut);
            return result;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException e) {
            System.err.println(e);
        }

        return null;
    }
}
