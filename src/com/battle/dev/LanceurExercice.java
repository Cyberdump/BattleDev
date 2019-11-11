package com.battle.dev;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LanceurExercice {

    public static void main(String[] argv) throws FileNotFoundException {
        InputStream input = new FileInputStream(argv[0]);
        System.setIn(input);

        try {
            Class<?> c = Class.forName(argv[1]);
            Method m = c.getMethod("main", String[].class);
            m.invoke(c, (Object) argv);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.err.println(e);
        }
    }
}
