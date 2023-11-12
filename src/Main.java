import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> myArrList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char choice;
        do {
            displayMenuAndList();
            choice = getRegExString("[AaDdPpQq]", "Enter your choice (A/D/P/Q): ");
            switch (choice) {
                case 'A':
                    addItemToList();
                    break;
                case 'D':
                    deleteItemFromList();
                    break;
                case 'P':
                    printList();
                    break;
                case 'Q':
                    confirmQuit();
                    break;
            }
        } while (choice != 'Q');
        scanner.close();
    }

    private static void displayMenuAndList() {
        System.out.println("Current List:");
        for (int i = 0; i < myArrList.size(); i++) {
            System.out.println((i + 1) + ". " + myArrList.get(i));
        }
        System.out.println("Menu Options:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    private static void addItemToList() {
        String newItem = String.valueOf(getRegExString(".+", "Enter the item to add: "));
        myArrList.add(newItem);
    }

    private static void deleteItemFromList() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        int indexToDelete = getRangedInt(scanner, "Enter the number of the item to delete: ", 1, myArrList.size());
        myArrList.remove(indexToDelete - 1);
    }

    private static void printList() {
        if (myArrList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Current List:");
            for (int i = 0; i < myArrList.size(); i++) {
                System.out.println((i + 1) + ". " + myArrList.get(i));
            }
        }
    }

    private static void confirmQuit() {
        if (getYNConfirm("Are you sure you want to quit? (Y/N): ")) {
            System.out.println("Exiting the program. Goodbye!");
        }
    }

    // SafeInput Library Methods
    private static char getRegExString(String regex, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
        } while (input.isEmpty() || !String.valueOf(input.charAt(0)).matches(regex));
        return input.toUpperCase().charAt(0);
    }

    private static int getRangedInt(Scanner scanner, String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < min || input > max);
        return input;
    }

    private static boolean getYNConfirm(String prompt) {
        char input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().toUpperCase().charAt(0);
        } while (input != 'Y' && input != 'N');
        return input == 'Y';
    }
}
