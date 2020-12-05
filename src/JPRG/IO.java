/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPRG;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.stream.Stream;

/**
 *
 * @author Wong En Ting Kelyn Admission number: P1935800 Class: DIT/1B/01
 *
 */
public class IO {

    File accountDetails = new File("accountDetails.dat");
    File transactionHistory = new File("transactionHistory.txt");
    File account = new File("account.txt");

    public void saveFile(BankAccount[] account) {

        try {
            FileOutputStream fileOutput = new FileOutputStream(accountDetails);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(account);

            fileOutput.close();
            objectOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BankAccount[] readFile() throws FileNotFoundException, IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream(accountDetails);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        return (BankAccount[]) objectInput.readObject();

    }

    public BankAccount[] readFromtextFile() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("bankAccounts.txt");
        BufferedReader br = new BufferedReader(fr);

        BankAccount[] accounts = new BankAccount[Integer.parseInt(br.readLine())];

        for (int i = 0; i < accounts.length; i++) {
            String[] accountDetails = br.readLine().split(";");

            if (accountDetails[6].equals("Savings Account")) {
                accounts[i] = new SavingsAccount(accountDetails[0], accountDetails[1], accountDetails[2], Double.parseDouble(accountDetails[3]), Double.parseDouble(accountDetails[4]), Double.parseDouble(accountDetails[5]), accountDetails[6]);
            } else {
                accounts[i] = new FixedDeposit(accountDetails[0], accountDetails[1], accountDetails[2], Double.parseDouble(accountDetails[3]), Double.parseDouble(accountDetails[4]), Double.parseDouble(accountDetails[5]), accountDetails[6]);
            }
        }
        return accounts;
    }

    public double[] readStartingBaltextFile() throws FileNotFoundException, IOException {
        FileReader f = new FileReader("bankAccounts.txt");
        BufferedReader b = new BufferedReader(f);

        double[] startingBal = new double[Integer.parseInt(b.readLine())];

        for (int i = 0; i < startingBal.length; i++) {
            String[] accountDetails = b.readLine().split(";");

            startingBal[i] = Double.parseDouble(accountDetails[3]);
        }

        return startingBal;
    }

    public double[] readIntRatetextFile() throws FileNotFoundException, IOException {
        FileReader f = new FileReader("bankAccounts.txt");
        BufferedReader b = new BufferedReader(f);

        double[] intRate = new double[Integer.parseInt(b.readLine())];

        for (int i = 0; i < intRate.length; i++) {
            String[] accountDetails = b.readLine().split(";");

            intRate[i] = Double.parseDouble(accountDetails[5]);
        }

        return intRate;
    }

    public void saveHistory(Timestamp ts, String typeOfTransaction, double amount, double balance, String account) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(String.format("transactionHistory%s.txt", account), true));
            pw.println(ts + "\t" + typeOfTransaction + "\t" + String.format("%.2f", amount) + "\t" + String.format("%.2f", balance));
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readHistory(String account) throws IOException {
        StringBuilder history = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(String.format("transactionHistory%s.txt", account)), StandardCharsets.UTF_8)) {
            stream.forEach(s -> history.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return history.toString();
    }

    public boolean deleteHistory(String account) {
        File file = new File(String.format("transactionHistory%s.txt", account));
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
