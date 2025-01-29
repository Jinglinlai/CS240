package main;

import ui.Menu;
import util.TypeWriter;

public class Main {
    public static void main(String[] args) {
        TypeWriter print = new TypeWriter();
        print.SlowType("\nWelcome to Snake! The best video game in the world!\n");

        new Menu();
    }
}
