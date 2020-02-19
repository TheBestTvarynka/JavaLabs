package com.kpi.lab1.Model;

import java.util.Random;

public class DataSource {
    public static Book[] generateRandomBooks(int amount) {
        String[] IDs = {"001123", "001124", "001125", "001126", "001127"};
        String[] titles = {"The Silent Patient", "Daisy Jones & The Six", "The Testaments", "The City of Girls"};
        String[] author = {"Alex Michaelides", "Taylot Jenkins", "Margaret Atwood", "Elizabeth Gilbert"};
        String[] publishing = {"Best Books", "Home Club", "Best Books", "Best Books"};
        int[] year = {2019, 2019, 2018, 2018};
        int[] pageNumber = {430, 650, 389, 637};
        float[] price = {113.5F, 95F, 148.2F, 85.8F};
        Random rand = new Random();
        Book[] books = new Book[amount];
        for (int i = 0; i < books.length; i++) {
            int n = rand.nextInt(4);
            books[i] = new Book(IDs[n], titles[n], author[n], publishing[n], pageNumber[n], price[n], year[n]);
        }
        return books;
    }
}
