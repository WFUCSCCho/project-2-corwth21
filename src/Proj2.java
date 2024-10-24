/**
 * @file: Node.java
 * @description: This program creates a BST and AVL tree and determines the run times for insertion and
 * searching in each. It then prints the results
 * @author: Tucker Corwen
 * @date: October 23, 2024
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // FINISH ME

        //Create a Scanner class that reads the data csv
        Scanner csvScanner = new Scanner(new File(inputFileName));
        //Create ArrayList
        ArrayList<Candidate> candidates = new ArrayList<>();

        boolean isFirstLine = true;

        for (int i = 0; i < numLines; i++) {

            //Read CSV line
            String line = csvScanner.nextLine().trim();

            if(!isFirstLine) {
                //Create empty string array
                String[] info = null;

                //If line is empty move to next line
                if (line.isEmpty()) continue;

                //Split line into sections that are bounded by commas
                info = line.split(",", -1);


                //Check csv data points, if missing create N/A or 0 entries
                float year = Float.parseFloat(info[0].isEmpty() ? "0" : info[0]);
                String name = info[1].isEmpty() ? "N/A" : info[1];
                String college = info[2].isEmpty() ? "N/A" : info[2];
                String position = info[3].isEmpty() ? "N/A" : info[3];

                float heightInches = 0;
                if (isNumeric(info[4])) {
                    Float.parseFloat(info[4].isEmpty() ? "0" : info[4]);
                }

                float weightLbs = Float.parseFloat(info[5].isEmpty() ? "0" : info[5]);
                float handSize = Float.parseFloat(info[6].isEmpty() ? "0" : info[6]);
                float armLength = Float.parseFloat(info[7].isEmpty() ? "0" : info[7]);
                float wonderlic = Float.parseFloat(info[8].isEmpty() ? "0" : info[8]);
                float fortyYard = Float.parseFloat(info[9].isEmpty() ? "0" : info[9]);
                float benchPress = Float.parseFloat(info[10].isEmpty() ? "0" : info[10]);
                float verticalLeap = Float.parseFloat(info[11].isEmpty() ? "0" : info[11]);
                float broadJump = Float.parseFloat(info[12].isEmpty() ? "0" : info[12]);
                float shuttle = Float.parseFloat(info[13].isEmpty() ? "0" : info[13]);
                float threeCone = Float.parseFloat(info[14].isEmpty() ? "0" : info[14]);
                float sixtyYardShuttle = Float.parseFloat(info[15].isEmpty() ? "0" : info[15]);

                //Create the object and set the values
                Candidate newCandidate = new Candidate(info);
                newCandidate.setYear(year);
                newCandidate.setName(name);
                newCandidate.setCollege(college);
                newCandidate.setPos(position);
                newCandidate.setHeight_in(heightInches);
                newCandidate.setWeight_lbs(weightLbs);
                newCandidate.setHand_size_in(handSize);
                newCandidate.setArm_length_in(armLength);
                newCandidate.setWonderlic(wonderlic);
                newCandidate.setForty_yard(fortyYard);
                newCandidate.setBench_press(benchPress);
                newCandidate.setVert_leap_in(verticalLeap);
                newCandidate.setBroad_jump_in(broadJump);
                newCandidate.setShuttle(shuttle);
                newCandidate.setThree_cone(threeCone);
                newCandidate.setSixty_yd_shuttle(sixtyYardShuttle);

                //Insert object to ArrayList
                candidates.add(newCandidate);
            }
            isFirstLine = false;
        }


        //Create arrays of times for each scenario
        ArrayList<Long> bst_shuffled_array = new ArrayList<>();
        ArrayList<Long> bst_sorted_array = new ArrayList<>();
        ArrayList<Long> avl_shuffled_array = new ArrayList<>();
        ArrayList<Long> avl_sorted_array = new ArrayList<>();

        ArrayList<Long> bst_shuffled_search = new ArrayList<>();
        ArrayList<Long> bst_sorted_search = new ArrayList<>();
        ArrayList<Long> avl_shuffled_search = new ArrayList<>();
        ArrayList<Long> avl_sorted_search = new ArrayList<>();


        BST bst_sorted = new BST();
        AvlTree avl_sorted = new AvlTree();
        BST bst_shuffled = new BST();
        AvlTree avl_shuffled = new AvlTree();


        //SHUFFLED INSERT
        //Shuffle the arraylist of candidates
        Collections.shuffle(candidates);
       ArrayList<Candidate> candidates_shuffled = new ArrayList<>(candidates);



        //Find the time to insert each element from the shuffled list into the BST and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            bst_shuffled.insert(candidates_shuffled.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            bst_shuffled_array.add(duration);
        }

        //Find the time to insert each element from the shuffled list into the AVL and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            avl_shuffled.insert(candidates_shuffled.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            avl_shuffled_array.add(duration);

        }

        //SORTED INSERT
        //Sort the arraylist of candidates
        Collections.sort(candidates);
        ArrayList<Candidate> candidates_sorted = new ArrayList<>(candidates);

        //Find the time to insert each element from the sorted list into the BST and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            bst_sorted.insert(candidates_sorted.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            bst_sorted_array.add(duration);
        }

        //Find the time to insert each element from the sorted list into the AVL and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            avl_sorted.insert(candidates_sorted.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            avl_sorted_array.add(duration);
        }


        //SEARCH SHUFFLED

        //Find the time to insert each element from the sorted list into the BST and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            bst_shuffled.search(candidates_shuffled.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            bst_shuffled_search.add(duration);
        }

        //Find the time to search each element from the shuffled list into the AVL and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            avl_shuffled.contains(candidates_shuffled.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            avl_shuffled_search.add(duration);
        }

        //SEARCH SORTED

        //Find the time to search each element from the sorted list into the BST and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            bst_sorted.search(candidates_sorted.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            bst_sorted_search.add(duration);
        }

        //Find the time to search each element from the sorted list into the AVL and create an array of times
        for(int i = 0; i < candidates.size(); i++) {
            long startTime = System.nanoTime();
            avl_sorted.contains(candidates_sorted.get(i));
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            avl_sorted_search.add(duration);
        }

        //PRINT EVERYTHING
        FileWriter writer = new FileWriter("output.txt", true);
        PrintWriter printWriter = new PrintWriter(writer);



        printWriter.println("Total Number of Nodes: " + numLines);
        printRuntimes("Time to insert sorted array into BST (per node): ", bst_sorted_array);
        printRuntimes("Time to insert sorted array into AVL (per node): ", avl_sorted_array);
        printRuntimes("Time to insert shuffled array into BST (per node): ", bst_shuffled_array);
        printRuntimes("Time to insert shuffled array into AVL (per node): ", avl_shuffled_array);

        printRuntimes("Time to search sorted array into BST (per node): ", bst_sorted_search);
        printRuntimes("Time to search sorted array into AVL (per node): ", avl_sorted_search);
        printRuntimes("Time to search shuffled array into BST (per node): ", bst_shuffled_search);
        printRuntimes("Time to search shuffled array into AVL (per node): ", avl_shuffled_search);


        printWriter.println("-----------------------------------------------");

        // Close the writer
        printWriter.close();


    }

    // Method to check if the string is numeric
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void printRuntimes(String str, ArrayList<Long> runtimes) throws IOException {
        FileWriter writer = new FileWriter("output.txt", true);
        PrintWriter printWriter = new PrintWriter(writer);

        //Print the array of runtimes, multiply increment by 2
        printWriter.println("");
        printWriter.println(str);
        printWriter.println("  2    4   8   16  32  64  128 256 512");
        for(int i = 2; i < runtimes.size(); i *= 2) {
            printWriter.print("  "+ runtimes.get(i) + "  ");
        }
        printWriter.println("");

        // Close the writer
        printWriter.close();
    }

}






