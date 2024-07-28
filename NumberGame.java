import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX_CHANCES = 7;
        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have " + MAX_CHANCES + " chances to guess the correct number.");

        while (playAgain) {
            int randomNumber = getRandN(1, 100);
            boolean guessedCorrectly = false;

            for (int i = 0; i < MAX_CHANCES; i++) {
                System.out.println("Chance " + (i + 1) + ": Enter your guess (between 1 and 100):");
                int userGuess = sc.nextInt();

                if (userGuess == randomNumber) {
                    guessedCorrectly = true;
                    score++;
                    System.out.println("Congratulations! You guessed the number.");
                    break;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your chances. The correct number was " + randomNumber + ".");
            }

            System.out.println("Do you want to play again? (y/n)");
            String playAgainResponse = sc.next();
            playAgain = playAgainResponse.equalsIgnoreCase("y");
        }

        System.out.println("Thank you for playing! Your final score is: " + score);

        sc.close(); 
    }

    public static int getRandN(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
