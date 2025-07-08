package com.br.vitormapamodel.utils;

import java.util.Random;

/* Gerar ISBN */
public class ISBNGenerator {

    /* Gera um ISBN com 12 dígitos, começando por "321" */
    public static String ISBNGen() {
        Random generator = new Random();
        StringBuilder ISBN = new StringBuilder("321");

        // Gera 9 dígitos aleatórios para completar o ISBN
        for (int i = 0; i < 9; i++) {
            ISBN.append(generator.nextInt(10));
        }

        return ISBN.toString();
    }
}


