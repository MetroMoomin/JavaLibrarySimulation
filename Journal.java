package Libraryex1;

// Journal.java

public  final class Journal extends LibraryItem {
    private String eISSN;
    private String publisher;
    private String latestIssue;
    private String journalURL;
    static int type = 2;
    static int Sloan = 3;
    static int Floan = 7;
    static int prob = 8;
    double fine = 2;


    public Journal(int libraryId, String title, String eISSN, String publisher, String latestIssue, String journalURL) {
        this.libraryId = libraryId;
        this.title = title;
        this.eISSN = eISSN;
        this.publisher = publisher;
        this.latestIssue = latestIssue;
        this.journalURL = journalURL;
        this.inStock = true;
    }

    // In Journal class

// Methods to get necessary values
    public int getProb() {
        return prob;
    }

    public double getFine() {
        return fine;
    }

    public int getType() {
        return type;
    }

    public int getSloan() {
        return Sloan;
    }

    public int getFloan() {
        return Floan;
    }

    public void printInfo() {
        System.out.println("ID: " + this.libraryId + "\n" + "Title: " + this.title + "\n" + "eISSN: " + this.eISSN + "\n" + "Publisher: " + this.publisher + "\n" + "Latest issue: " + this.latestIssue + "\n" + "URL: " + journalURL + "\n" + "Days overdue: " + this.daysLeft + "\n" + "Day of borrowing: " + this.dateBorrowed + "\n ");
    }
}
