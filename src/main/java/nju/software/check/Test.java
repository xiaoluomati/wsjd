package nju.software.check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by away on 2018/4/22.
 */
public class Test {

    public static void main(String[] args) {


        for (int num = 30; num <= 60; num +=2) {
            List<Integer> deck = new ArrayList<>();
            System.out.println("---------------------------------");
            System.out.println("≈∆ø‚ £”‡: " + num);
            for (int i = 0; i < num / 2; i++) {
                deck.add(0);
            }
            for (int i = 0; i < num / 2; i++) {
                deck.add(2);
            }
            deck.add(1);
            deck.add(1);
            deck.add(1);
            jens(deck);
            jens(deck);
            System.out.println("-------------");
            System.out.println("œ»÷©÷Î");
            show(deck);
            System.out.println("-------------");

            deck = new ArrayList<>();
            for (int i = 0; i < num / 2; i++) {
                deck.add(0);
            }
            for (int i = 0; i < num / 2; i++) {
                deck.add(2);
            }
            jens(deck);
            jens(deck);
            deck.add(1);
            deck.add(1);
            deck.add(1);

            System.out.println("-------------");
            System.out.println("œ»jens");
            show(deck);
            System.out.println("-------------");
        }

    }


    private static void show(List<Integer> deck) {
        int loop = 100_000;
        double spider = 0;
        List<Integer> testDeck = new ArrayList<>(deck);
        for (int i = 0; i < loop; i++) {
            Collections.shuffle(testDeck);
            spider += draw(testDeck);
            testDeck = new ArrayList<>(deck);
        }
        System.out.println("≥ÈµΩ÷©÷Î–Ë“™≈∆Œ™: " + spider / loop + "≥È");
    }

    private static int draw(List<Integer> deck) {
        int spider = 0;
        int card = deck.remove(0);
        while (card != 1) {
            card = deck.remove(0);
            spider++;
        }
        return spider;
    }

    private static void jens(List<Integer> deck) {
        int len = deck.size();
        Random random = new Random(System.nanoTime());
        int i = random.nextInt(len);
        int card = deck.get(i);
        while (card == 1 || card == 2) {
            i = random.nextInt(len);
            card = deck.get(i);
        }
        deck.remove(i);
    }












}
