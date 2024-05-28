package Libraryex1;



// Book

public final class Book extends LibraryItem {
    String author;
    private String genre;
    private String publisher;
    private int Sloan = 14;
    private int Floan = 14;
    static int prob = 5;
    private double fine = 0.5;
    static int type = 1;




    public Book(int libraryId, String title, String author, String genre, String publisher) {
        this.libraryId = libraryId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.inStock = true;
    }
    public int getProb()
    {
        return prob;
    }
    public double getFine()
    {
        return fine;
    }
    public int getType()
    {
        return type;
    }
    public int getSloan()
    {
        return Sloan;
    }
    public int getFloan()
    {
        return Floan;
    }

    public void printInfo()
    {
        System.out.println("ID: "+ this.libraryId + "\n" +"Title: "+ this.title + "\n" + "Genre: "+ this.genre + "\n"+"Publisher: "+ this.publisher + "\n"+ "Days overdue: "+ this.daysLeft + "\n"+"Day of borrowing: "+ this.dateBorrowed + "\n");
    }

}




