package jaba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class sellysbooksto {
	static Scanner input = new Scanner(System.in);
	static String filePath = "d:\\test\\book.txt";

	public static void main(String[] args) throws IOException {

		printMenu();

		int choice = -1;

		while (choice != 0) {
			choice = inputChoice();
			switch (choice) {
			case 1:
				printbooks();
				break;
			case 2:
				searchbook();
				break;
			case 3:
				insertbook();
				break;
			case 4:
				deletebook();
				break;
			case 0:
				System.out.println("am i go. see u ");
				System.exit(0);
			}
		}
	}

	private static int inputChoice() {
		int choice = -1;

		System.out.println("Choice it : ");

		choice = input.nextInt();

		System.out.println("U chose:" + choice);
		return choice;
	}

	private static void printMenu() {
		System.out.println("Hear is library!! pic it damm! ");
		System.out.println("1.The all");
		System.out.println("2.Search");
		System.out.println("3.Add");
		System.out.println("4.Delete");
		System.out.println("0.Exit");
	}
	

	private static void deletebook() throws IOException {

		String tmpFilePath = filePath + ".tmp";
		int count = 1;
		System.out.println("Plz enter the delete num tat! : ");
		int booknumber = input.nextInt();

		System.out.println("chose delete number : " + booknumber);

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));

		String str = "";

		while ((str = br.readLine()) != null) {
			if (count != booknumber) {
				bw.write(str);
				bw.write("\r\n");
			}
			count++;
		}
		bw.close();
		br.close();

		FileInputStream fis = new FileInputStream(tmpFilePath);
		FileOutputStream fos = new FileOutputStream(filePath);

		int data = 0;
		while ((data = fis.read()) != -1) {
			fos.write(data);
		}
		fis.close();
		fos.close();

		File f = new File(tmpFilePath);
		f.deleteOnExit();
	}

	private static void insertbook() throws IOException {
		System.out.println("More add");
		input.nextLine();
		System.out.println("Title : ");
		String name = input.nextLine();

		System.out.println("Who(Author) : ");
		String author = input.nextLine();

		System.out.println("Publisher : ");
		String publisher = input.nextLine();

		System.out.println("$$$ : ");
		String cost = input.nextLine();

		System.out.println("Book for information");
		System.out.println("Book name : " + name);
		System.out.println("Author: " + author);
		System.out.println("Puvlisher : " + publisher);
		System.out.println("$$$: " + cost);

		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));

		bw.write(name + "\t" + author + "\t" + publisher + "\t" + cost);
		bw.newLine();
		bw.close();
	}

	private static void searchbook() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		System.out.println("Keywords to search! : ");
		input.nextLine();
		int count = 0;
		int bookLine = 1;
		String keyword;
		String str = "";
		keyword = input.nextLine();

		try {
			while ((str = br.readLine()) != null) {
				if (str.contains(keyword)) {
					System.out.println(bookLine + " : " + str);
					count++;
					bookLine++;
				}

			}
			System.out.println("The total number of book is : " + count + ".");
			br.close();
		} catch (IOException e) {
			System.out.println("404 not found--------- Unable to read information about the books.");
			e.printStackTrace();
		}
	}

	private static void printbooks() throws FileNotFoundException {
		System.out.println("print");
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String str = "";
		int count = 0;
		int bookLine = 1;
		try {
			while ((str = br.readLine()) != null) {
				System.out.println(bookLine + " : " + str);
				count++;
				bookLine++;
			}
			System.out.println("The total num of book is : " + count + "!");
		} catch (IOException e) {
			System.out.println("404 not found--------- Unable to read information about the books.");
			e.printStackTrace();
		}
	}
}