package de.hypoport.europace.bowling_score_calculator;

import de.hypoport.europace.bowling_score_calculator.model.BonusRoll;
import de.hypoport.europace.bowling_score_calculator.model.Frame;
import de.hypoport.europace.bowling_score_calculator.model.Game;
import de.hypoport.europace.bowling_score_calculator.model.TenthFrame;
import de.hypoport.europace.bowling_score_calculator.services.ScoreCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BowlingScoreCalculatorApplication implements CommandLineRunner {

    private ScoreCalculatorService service;

    public BowlingScoreCalculatorApplication(ScoreCalculatorService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {

        Scanner sc = new Scanner(System.in);
        List<Frame> frames = new ArrayList<>();

        System.out.println("=== Bowling Game Score Calculator ===");

        handleRegularFrames(sc, frames);

        handleTenthFrame(sc, frames);

        Game game = new Game(frames);
        int score = service.calculateGameScore(game);

        System.out.println("\n==========================");
        System.out.println("Final score: " + score);
        System.out.println("==========================");
    }

    private void handleTenthFrame(Scanner sc, List<Frame> frames) {
        System.out.println("Enter 10th frame");

        int roll1 = readInt(sc, "Roll 1: ");
        int roll2 = readInt(sc, "Roll 2: ");

        boolean isStrike = roll1 == 10;
        boolean isSpare = !isStrike && roll1 + roll2 == 10;

        BonusRoll bonusRoll1 = null;
        BonusRoll bonusRoll2 = null;

        if (isStrike || isSpare) {
            int b1Val = readInt(sc, "Bonus roll 1: ");
            bonusRoll1 = new BonusRoll(b1Val);

            if (isStrike) {
                int b2Val = readInt(sc, "Bonus roll 2: ");
                bonusRoll2 = new BonusRoll(b2Val);
            }
        }

        try {
            frames.add(new TenthFrame(roll1, roll2, bonusRoll1, bonusRoll2));
        } catch (Exception e) {
            System.out.println("Invalid input on 10th frame: " + e.getMessage());
            handleTenthFrame(sc, frames);
        }
    }


    private void handleRegularFrames(Scanner sc, List<Frame> frames) {
        for (int i = 1; i <= 9; i++) {
            System.out.println("Enter frame no.: " + i);

            int roll1 = readInt(sc, "Roll 1: ");
            int roll2 = readInt(sc, "Roll 2: ");

            try {
                frames.add(new Frame(roll1, roll2));
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
                i--;
            }
        }
    }

    private int readInt(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BowlingScoreCalculatorApplication.class, args);
    }

}
