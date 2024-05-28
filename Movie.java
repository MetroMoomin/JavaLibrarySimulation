package Libraryex1;

public final class Movie extends LibraryItem {
    private String genre;
    private String director;
    private String year;
    private String runtimeMinutes;
    private String rating;

    static int type = 3;
    int Sloan = 2;
    int Floan = 2;
    static int prob = 5;
    double fine = 5;




    public Movie(int libraryId, String title, String genre, String director, String year, String runtimeMinutes, String rating)
    {
        this.libraryId = libraryId;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.runtimeMinutes = runtimeMinutes;
        this.rating = rating;
        this.inStock = true;
    }
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

    public void printInfo()
    {
        System.out.println("ID: " + this.libraryId + "\n" + "Title: " + this.title + "\n" + "Genre: " + this.genre + "\n" + "Director: " + this.director + "\n" + "Year: " + this.year + "\n" + "Runtime (Minutes): " + this.runtimeMinutes + "\n" + "Rating: " + this.rating + "\n"+ "Days overdue: " + this.daysLeft + "\n" + "Day of borrowing: " + this.dateBorrowed + "\n ");
    }
}
