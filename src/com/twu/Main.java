// package com.twu;
import java.util.Scanner;

// import org.graalvm.compiler.core.common.type.ArithmeticOpTable.Op;


public class Main {

    static Scanner inputString = null;
    public static String inputFunc(){
        inputString = new Scanner(System.in); ;
        // String st =  inputString.nextLine();
        return inputString.nextLine();
    }
    public static void main(String[] args) {
       
        Boolean flag_login = true;
        while(flag_login){
            System.out.println("!!!!!!!! start !!!!! ");
             // show the initial menu to choose
            Menu.loginMenu();
            String ipt = inputFunc();
            switch(ipt){
                //  if it's normal user
                case "1":
                    System.out.println("Please enter your username: ");
                    String userName = inputFunc();
                    boolean flag_user = true;
                    while(flag_user){ // while user didn't exit user menu
                        Menu.userMenu(userName);
                        char inputOperator = inputFunc().charAt(0);
                        switch (inputOperator) {
                            case 'e': // exit the user menu, jump to login menu
                                flag_user = false;
                                break;
                            default: // do operation that user choose
                                Operation.runOperation(inputOperator);
                                break;
                        }
                    }
                    break;
                // if it's admin user
                case "2":
                    System.out.println("Please enter your adminname: ");
                    String adminName = inputFunc();
                    System.out.println("Please enter your password: ");
                    String adminPsw = inputFunc();
                    // check if username or password valid
                    if (adminName != "admin" && adminPsw != "1234"){
                        System.out.println("Soory!! Please check your username and password! \n");
                        break;
                    }
                    boolean flag_admin = true;
                    while(flag_admin){ //while user didn't exit admin menu
                        // show admin menu
                        Menu.adminMenu(adminName);
                        char inputOperator = inputFunc().charAt(0);
                        switch (inputOperator) {
                            case 'e': // exit the admin menu, jump to login menu
                                flag_admin = false;
                                break;
                            default: // do operation that user choose
                                Operation.runOperation(inputOperator);
                                break;
                        }
                    }
                    break;
                //  exit login menu, stop program
                case "3":
                    return;
                default:
                    System.out.println("Please input valid code");
                    break;
            }
            
        }
        inputString.close();
    }
}
