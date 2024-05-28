package Libraryex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Library {

    static int idC = 1;
    static String books = "/Users/metromoomin/Desktop/School/java/LibrarySim/books.csv";
    static String journals = "/Users/metromoomin/Desktop/School/java/LibrarySim/journals.csv";
    static String movies = "/Users/metromoomin/Desktop/School/java/LibrarySim/movies.csv";
    
    static ArrayList<LibraryUser> users = AssignUsers();
    static ArrayList<LibraryItem> catalog = loadAll();

  
    private static ArrayList<Movie> loadM()
    {

        Scanner scanner;
        ArrayList<Movie> Movies = new ArrayList<>();
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader(movies)));
            String header = scanner.nextLine();
            String[] headersA = header.split(";");
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] values = line.split(";");
                ArrayList<String> valuesArray = new ArrayList<>(Arrays.asList(values));
                if (values.length != headersA.length)
                {
                    valuesArray.add("");
                }
                Movies.add(new Movie(idC, valuesArray.get(1), valuesArray.get(2), valuesArray.get(4), valuesArray.get(6), valuesArray.get(7), valuesArray.get(11)));
                idC++;
            }
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }

        return Movies;
    }
    private static ArrayList<Journal> loadJ()
    {

        Scanner scanner;
        ArrayList<Journal> Journals = new ArrayList<>();
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader(journals)));
            String header = scanner.nextLine();
            String[] headersA = header.split(";");
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] values = line.split(";");
                ArrayList<String> valuesArray = new ArrayList<>(Arrays.asList(values));
                if (values.length != headersA.length)
                {
                    valuesArray.add("");
                }
                Journals.add(new Journal(idC, valuesArray.get(0), valuesArray.get(3), valuesArray.get(4), valuesArray.get(6), valuesArray.get(12)));
                idC++;
            }
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }

        return Journals;
    }
    private static ArrayList<Book> loadB()
    {
        Scanner scanner;
        ArrayList<Book> Books = new ArrayList<>();
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader(books)));
            String header = scanner.nextLine();
            String[] headersA = header.split(";");
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] values = line.split(";");
                ArrayList<String> valuesArray = new ArrayList<>(Arrays.asList(values));
                valuesArray.remove(3);
                if (values.length != headersA.length)
                {
                    valuesArray.add("");
                }
                Books.add(new Book(idC, valuesArray.get(0), valuesArray.get(1), valuesArray.get(2), valuesArray.get(3)));
                idC++;
            }
        }
        catch (Exception x)
            {
                x.printStackTrace();
            }

        return Books;
    }
   
    private static ArrayList<LibraryItem> loadAll()
    {
        ArrayList<LibraryItem> Items = new ArrayList<>();
        Items.addAll(loadM());
        Items.addAll(loadJ());
        Items.addAll(loadB());
        return Items;

    }
    // Method to calculate the total fine for all users
    public static double calcFine(ArrayList<LibraryUser> users)
    {
        double fine = 0;
        for(LibraryUser user : users)
        {
            fine += user.getUFine(); // fine zrobimy dla usera w jego klasie
        }
        return fine;
    }
    // Method to display items currently on loan
    public static void showLoans()
    {
        System.out.println("On loan: \n");
        for(LibraryItem item : catalog)
        {
        if(!item.Stock())
        {
            item.printInfo();
        }
        }


    }
    // Method to borrow an item by updating its status and recording the start date
    public void borrowItem(int id, int date , int userType)
    {
        for ( LibraryItem item : catalog)
        {
        if(item.getLibraryId() == id)
        {
            item.setDateBorrowed(date);
            item.CurrStock();
            item.setStartDay(userType);
        }
        }
    }
    // Method to display various statistics such as total fine, items loaned, overdue items, and items returned
    public static void showStatistics() {
        int totalLoaned = 0;
        int totalOverdue = 0;
        int totalReturned = 0;

        for (LibraryItem item : catalog)
        {
                if (item.isOverdue())
                {
                    totalOverdue++;
                }
                totalLoaned += item.getBC();
                totalReturned += item.getRC();
        }
        System.out.println("╭══• ೋ•✧๑♡๑✧•ೋ •══╮"+ "\n" + "╰══• ೋ•✧๑♡๑✧•ೋ •══╯");
        System.out.println("Total Fine: " + calcFine(users));
        System.out.println("*Total Items Loaned: " + totalLoaned);
        System.out.println("*Total Overdue Items: " + totalOverdue);
        System.out.println("*Total Items Returned: " + totalReturned);
        System.out.println("╭══• ೋ•✧๑♡๑✧•ೋ •══╮"+ "\n" + "╰══• ೋ•✧๑♡๑✧•ೋ •══╯");
    }
    // Method to assign users, including students and faculty members,
    public static ArrayList<LibraryUser> AssignUsers()
    {
        Random rand = new Random();
        List<Integer> risky = new ArrayList<>();
        ArrayList<LibraryUser> users = new ArrayList<>();
        for (int x = 1; x<=80; x++)
        {
            users.add(new Student((x)));
        }
        for (int x = 81; x<=100; x++)
        {
            users.add(new FacultyMember(x));
        }
        for (int x = 0; x < 33; x++)
        {
            int i = rand.nextInt(100);
            while (risky.contains(i))
            {
                i = rand.nextInt(100);
            }
            risky.add(i);
        }
        Collections.sort(risky);
        for (LibraryUser user : users)
        {
            if (risky.contains(user.UserID()))
            {
                user.setStatus(true);
            }
        }
        return users;
    }
    // Method to perform daily operations in the library simulation
    public static void dailyOp(int date)
    {
        Random rand = new Random();
        for (LibraryUser user : users)
        {
            user.updateOverdue();
            int prob = rand.nextInt(100) + 1;
            if(prob <= Book.prob && !user.isOverdue())
            {
             int id = rand.nextInt(422);
             if (catalog.get(id).Stock())
             {
                 user.updateL(catalog.get(id), date);
             }
            }
            prob = rand.nextInt(100)+1;
            if(prob <= Journal.prob && !user.isOverdue())
            {
                int id = rand.nextInt(3923)+423;
                if (catalog.get(id).Stock())
                {
                    user.updateL(catalog.get(id), date);
                }
            }
            prob = rand.nextInt(100) +1;
            if(prob <= Movie.prob && !user.isOverdue())
            {
                int id = rand.nextInt(1000)+4346;
                if (catalog.get(id).Stock())
                {
                    user.updateL(catalog.get(id), date);
                }
            }

            if (user.Status() && !user.getBorrowed().isEmpty())
            {
                for (int x = 0; x < user.getBorrowed().size(); x ++)
                {
                    if ( user.getBorrowed().get(x).getDaysLeft() == 0)
                    {
                        user.deleteLoan(user.getBorrowed().get(x));
                    }
                }
            }
            prob = rand.nextInt(100) +1;

            if(prob <= 2 && !user.getBorrowed().isEmpty())
            {
                int id = rand.nextInt(user.getBorrowed().size());
                user.deleteLoan(user.getBorrowed().get(id));
            }
            if(!user.getBorrowed().isEmpty() && date != 1)
            {
                for (LibraryItem item : user.getBorrowed())
                {
                    item.updateDate();
                }
            }
            user.addFine();
        }
    }
    // Method to run the simulation for a specified number of days
    public static void simulation(int z)
    {
        for (int x = 1; x <= z; x++)
        {
            dailyOp(x);
        }
    }

    public static void displayUsersWithLateRefunds(int day) {
        System.out.println("Users with late refunds for day " + day + ":");
        for (LibraryUser user : users) {
            double totalPenalties = 0;
            for (LibraryItem item : user.getBorrowed()) {
                totalPenalties += item.computeLateRefundPenalty();
            }
            if (totalPenalties > 0) {
                System.out.println("User ID: " + user.UserID() + ", Total Late Refund Penalty: " + totalPenalties);
            }
        }
    }

    public static void blockRentalsByPenaltyThreshold(double threshold) {
        for (LibraryUser user : users) {
            double totalPenalties = 0;
            for (LibraryItem item : user.getBorrowed()) {
                totalPenalties += item.computeLateRefundPenalty();
            }
            if (totalPenalties > threshold) {
                System.out.println("User ID " + user.UserID() + " blocked due to exceeding penalty threshold.");
                // Implement logic to block rentals for the user as needed
            }
        }
    }
    private static class LocalClassExample {
        void displayMessage() {
            System.out.println("This is a local internal class within Library class.");
        }
    }

    // Method to use an anonymous internal class for a one-time object
    public static void oneTimeObjectExample() {
        // Creating a one-time object using an anonymous internal class
        Runnable oneTimeObject = new Runnable() {
            @Override
            public void run() {
                System.out.println("This is a one-time object created using an anonymous internal class.");
            }
        };

        // Using the one-time object
        oneTimeObject.run();
    }



    public static void main(String[] args) {
            Library.simulation(365);
            Library.showLoans();
           

            for (LibraryUser user : users) {
                if (user instanceof FacultyMember) {
                    FacultyMember facultyMember = (FacultyMember) user;
                    facultyMember.checkAccountBalance(1);
                } else if (user instanceof Student) {
                    Student student = (Student) user;
                    student.checkAccountBalance(2);
                }
            }

            displayUsersWithLateRefunds(3);

            blockRentalsByPenaltyThreshold(10.0);

    
        Library.showStatistics();

        }
    }




