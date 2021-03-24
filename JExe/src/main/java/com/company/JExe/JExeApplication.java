package com.company.JExe;


import net.minidev.json.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

@SpringBootApplication
public class JExeApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JExeApplication.class, args);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Book> library = new ArrayList<>();

        Book newBook = new Book("Aru", "Meru", 20); // So I can have a Default to test
        library.add(newBook);

        String option;

        do {
            System.out.println("Choose Create, Update, Read or Delete");
            option = br.readLine();
            switch (option.toLowerCase()) {
                case "create":
                    System.out.print("Add the book name: ");
                    String bname = br.readLine();
                    System.out.print("The book author: ");
                    String bauthor = br.readLine();
                    System.out.print("The price: ");
                    double bprice = Double.parseDouble(br.readLine());

                    newBook = new Book(bname, bauthor, bprice);

                    library.add(newBook);

                    break;
                case "update": // when i update the id changes, to not change it other option would be seek the closest positive number to zero available and set it
                    if (library.size() > 0) {
                        for (Book e : library) {
                            System.out.println("Book id: " + e.getId() + "\n" +
                                    "Book name: " + e.getBookName());
                        }
                    } else {
                        System.out.println("Error, create at least 1 book...");
                    }

                    System.out.print("Select by id: ");
                    int selected = Integer.parseInt(br.readLine()) - 1;

                    if (library.size() >= selected) {
                        System.out.print("Add the book name: ");
                        bname = br.readLine();
                        System.out.print("The book author: ");
                        bauthor = br.readLine();
                        System.out.print("The price: ");
                        bprice = Double.parseDouble(br.readLine());

                        newBook = new Book(bname, bauthor, bprice);

                        library.set(selected,newBook);
                    } else {
                        System.out.println("Selection out of bound");
                    }

                    break;
                case "read":
                    if (library.size() > 0) {
                        for (int i = 0; i < library.size(); i++) {
                            System.out.println(library.get(i));
                        }
                    } else {
                        System.out.println("Error, create at least 1 book...");
                    }

                    String jsonStr = JSONArray.toJSONString(library);
                    System.out.println(jsonStr);

                    // Constructs a FileWriter given a file name
                    FileWriter file = new FileWriter("/Users/Public/Documents/Json.txt");
                    file.write(jsonStr);
                    System.out.println("JSON Object: " + jsonStr);
                    System.out.println("A Json file has been created.");
                    break;
                case "delete":
                    if (library.size() > 0) {
                        for (int i = 0; i < library.size(); i++) {
                            System.out.println(library.get(i));
                        }
                    } else {
                        System.out.println("Error, create at least 1 book...");
                    }

                    System.out.println("Select by id: ");
                    int selection = Integer.parseInt(br.readLine()) - 1;

                    library.remove(selection);
                    break;
                case "find":
                    System.out.println("¿What do you want to filter or find?");
                    System.out.print("Book name, author or prices: ");

                    String find = br.readLine();

                    if (find.equalsIgnoreCase("name")){
                        System.out.println("Which name: ");
                        String name = br.readLine();
                        System.out.println(library.contains(name));
                    } else if(find.equalsIgnoreCase("author")) {
                        System.out.println("Which author: ");
                        String author = br.readLine();
                        System.out.println(library.contains(author));
                    } else {
                        System.out.println("Which price: ");
                        double price = Double.parseDouble(br.readLine());
                        System.out.println(library.contains(price));
                    }
                    break;
                default:
                    System.out.println("Error...");

            }
        } while (!option.equalsIgnoreCase("salir"));
    }
}
