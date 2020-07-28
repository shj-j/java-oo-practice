// package com.twu;

public class Menu {
    public static void main(String args[]){
        loginMenu();
    }

    public static void loginMenu() {
        System.out.println("Welcome to trending topics board! Please choose your role.");
        System.out.println("1.User.");
        System.out.println("2.Admin.");
        System.out.println("3.Exit.");
    }

    public static void userMenu(String username){
        System.out.println("Welcome "+username+", please choose what you want to do");
        System.out.println("[c].Check the trending topics");
        System.out.println("[a].Add trending");
        System.out.println("[v].Vote for trending");
        System.out.println("[b].Buy trending");
        System.out.println("[e].Exit");
    }

    public static void adminMenu(String adminname){
        System.out.println("Welcome "+adminname+", please choose what you want to do");
        System.out.println("[c].Check the trending topics");
        System.out.println("[a].Add trending");
        System.out.println("[s].Add super trending");
        System.out.println("[e].Exit");
    }
}