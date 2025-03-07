package Assignment1;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class RefactoredStudentPortal {
    private static final String FILE_PATH = "D:\\Assignment PF\\JavaApplication5\\src\\Assignment1\\students.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            File file = new File(FILE_PATH);
            ArrayList<String> rnosList = new ArrayList<>();
            ArrayList<String> namesList = new ArrayList<>();
            ArrayList<String> passwordsList = new ArrayList<>();
            ArrayList<String> fatherNameList = new ArrayList<>();
            ArrayList<String> dobList = new ArrayList<>();
            ArrayList<String> emailList = new ArrayList<>();
            ArrayList<String> addressList = new ArrayList<>();
            ArrayList<String> phoneNoList = new ArrayList<>();
            ArrayList<String> resultList = new ArrayList<>();
            ArrayList<String> feeList = new ArrayList<>();

            try (Scanner fileScanner = new Scanner(file)) {
                readFromFile(fileScanner, rnosList, namesList, passwordsList, fatherNameList, dobList, emailList, addressList, phoneNoList, resultList, feeList);
            }

            displayMainMenu(rnosList, passwordsList, namesList, fatherNameList, dobList, emailList, addressList, phoneNoList, resultList, feeList);

            writeToFile(rnosList, namesList, passwordsList, fatherNameList, dobList, emailList, addressList, phoneNoList, resultList, feeList);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            scanner.close();
        }
    }

    private static void readFromFile
            (Scanner fileScanner,
             ArrayList<String> rnosList,
             ArrayList<String> namesList,
             ArrayList<String> passwordsList,
             ArrayList<String> fatherNameList,
             ArrayList<String> dobList,
             ArrayList<String> emailList,
             ArrayList<String> addressList,
             ArrayList<String> phoneNoList,
             ArrayList<String> resultList,
             ArrayList<String> feeList)
    {
        while (fileScanner.hasNext()) {
            rnosList.add(fileScanner.nextLine());
            namesList.add(fileScanner.nextLine());
            passwordsList.add(fileScanner.nextLine());
            fatherNameList.add(fileScanner.nextLine());
            dobList.add(fileScanner.nextLine());
            emailList.add(fileScanner.nextLine());
            addressList.add(fileScanner.nextLine());
            phoneNoList.add(fileScanner.nextLine());
            resultList.add(fileScanner.nextLine());
            feeList.add(fileScanner.nextLine());
        }
    }

    private static void writeToFile
            (ArrayList<String> rnosList,
             ArrayList<String> namesList,
             ArrayList<String> passwordsList,
             ArrayList<String> fatherNameList,
             ArrayList<String> dobList,
             ArrayList<String> emailList,
             ArrayList<String> addressList,
             ArrayList<String> phoneNoList,
             ArrayList<String> resultList,
             ArrayList<String> feeList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < rnosList.size(); i++) {
                writer.write(rnosList.get(i) + "\n");
                writer.write(namesList.get(i) + "\n");
                writer.write(passwordsList.get(i) + "\n");
                writer.write(fatherNameList.get(i) + "\n");
                writer.write(dobList.get(i) + "\n");
                writer.write(emailList.get(i) + "\n");
                writer.write(addressList.get(i) + "\n");
                writer.write(phoneNoList.get(i) + "\n");
                writer.write(resultList.get(i) + "\n");
                writer.write(feeList.get(i) + "\n");
            }
        }
    }

    private static void displayMainMenu(ArrayList<String> rnosList,
                                        ArrayList<String> passwordsList,
                                        ArrayList<String> namesList,
                                        ArrayList<String> fatherNameList,
                                        ArrayList<String> dobList,
                                        ArrayList<String> emailList,
                                        ArrayList<String> addressList,
                                        ArrayList<String> phoneNoList,
                                        ArrayList<String> resultList,
                                        ArrayList<String> feeList) {
        while (true) {
            Object[] options = {"Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to the Student Portal System", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                handleLogin(rnosList, passwordsList, namesList, fatherNameList, dobList, emailList, addressList, phoneNoList, resultList, feeList);
            } else if (choice == 1) {
                JOptionPane.showMessageDialog(null, "Logout successfully");
                break;
            }
        }
    }

    private static void handleLogin(ArrayList<String> rnosList,
                                    ArrayList<String> passwordsList,
                                    ArrayList<String> namesList,
                                    ArrayList<String> fatherNameList,
                                    ArrayList<String> dobList,
                                    ArrayList<String> emailList,
                                    ArrayList<String> addressList,
                                    ArrayList<String> phoneNoList,
                                    ArrayList<String> resultList,
                                    ArrayList<String> feeList) {
        int index = -1;
        while (index == -1) {
            String rollNo = JOptionPane.showInputDialog(null, "Enter your roll number:");
            String password = JOptionPane.showInputDialog(null, "Enter your password:");

            for (int i = 0; i < rnosList.size(); i++) {
                if (rnosList.get(i).equals(rollNo) && passwordsList.get(i).equals(password)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
            }
        }

        showSubMenu(rnosList, namesList, fatherNameList, dobList, emailList, addressList, phoneNoList, resultList, feeList, index);
    }

    private static void showSubMenu(ArrayList<String> rnosList,
                                    ArrayList<String> namesList,
                                    ArrayList<String> fatherNameList,
                                    ArrayList<String> dobList,
                                    ArrayList<String> emailList,
                                    ArrayList<String> addressList,
                                    ArrayList<String> phoneNoList,
                                    ArrayList<String> resultList,
                                    ArrayList<String> feeList,
                                    int index) {
        boolean showSubMenu = true;
        while (showSubMenu) {
            Object[] options = {"Dashboard", "Profile", "Fee Clearance", "Result", "Logout"};
            int choice = JOptionPane.showOptionDialog(null, "Menu", "Sub Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> displayDashboard();
                case 1 -> displayProfile(namesList, fatherNameList, dobList, emailList, addressList, phoneNoList, index);
                case 2 -> displayFeeClearance(feeList, index);
                case 3 -> displayResult(resultList, index);
                case 4 -> showSubMenu = false;
            }
        }
    }

    private static void displayDashboard() {
        String[][] courses = {
                {"Communication Skills", "Misbah Atta"},
                {"Pakistan Studies", "Layloma Rashid"},
                {"Programming Fundamental", "Muzaffar Iqbal"},
                {"Professional Practices", "Shaukat Anwar"},
                {"Software Basics", "Tahseen Abbasi"}
        };

        StringBuilder message = new StringBuilder("Course\t\t\tTeacher\n------------------------------------------\n");
        for (String[] course : courses) {
            message.append(String.format("%-30s%s\n", course[0], course[1]));
        }
        JOptionPane.showMessageDialog(null, message.toString(), "Dashboard", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayProfile(ArrayList<String> namesList, ArrayList<String> fatherNameList, ArrayList<String> dobList,
                                       ArrayList<String> emailList, ArrayList<String> addressList, ArrayList<String> phoneNoList, int index) {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Name: %s\n", namesList.get(index)));
        message.append(String.format("Date of Birth: %s\n", dobList.get(index)));
        message.append(String.format("Father's Name: %s\n", fatherNameList.get(index)));
        message.append(String.format("Phone Number: %s\n", phoneNoList.get(index)));
        message.append(String.format("Email: %s\n", emailList.get(index)));
        message.append(String.format("Address: %s\n", addressList.get(index)));

        JOptionPane.showMessageDialog(null, message.toString(), "Profile", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayFeeClearance(ArrayList<String> feeList, int index) {
        JOptionPane.showMessageDialog(null, "Your Fee Status: " + feeList.get(index), "Fee Clearance", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void displayResult(ArrayList<String> resultList, int index) {
        JOptionPane.showMessageDialog(null, "Your Result: " + resultList.get(index), "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}