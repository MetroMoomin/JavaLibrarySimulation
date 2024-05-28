package Libraryex1;

// LibraryItem

import java.util.Date;
import java.util.List;

public abstract class LibraryItem {
    public int daysLeft; //days left
    public int libraryId; //id
    public boolean inStock;
    public int returnCount;
    public int borrowCount;
    public int dateBorrowed;
    public int Sloan;
    public int Floan;
    public double fine;
    public int type;

    public String title;

    private static final List<Class<? extends LibraryItem>> subclasses = List.of(Book.class, Journal.class, Movie.class);
    public int getLibraryId()
    {
        return this.libraryId;
    }
    public void  printInfo()
    {
        System.out.println(title);
    }

    public int getType()
    {
        return type;
    }
//Method that marks if the item is instock
    public boolean Stock()
    {
        return this.inStock;
    }

    public double getFine()
    {
        return fine;
    }
//Method that sets the date of the borrowing of an item so we can than use it to determine the overdue days
    public void setDateBorrowed(int date)
    {
        this.dateBorrowed = date;
    }
//Method that keeps track if something is in stock or not
    public void CurrStock()
    {
        if(this.inStock)
        {
            this.inStock = false;
        }
        else
        {
            this.inStock = true;
        }
    }
    public void setStartDay(int userType)
    {
        this.daysLeft =(userType == 1) ? -Sloan : -Floan;
    }
    public int getDaysLeft()
    {
        return this.daysLeft;
    }

    public boolean isOverdue()
    {
        return daysLeft > 0;
    }

    public double computeFine(double fine)
    {
        return Math.max(0,this.getDaysLeft()*fine);
    }

    public void reset()
    {
       this.daysLeft = -1;
    }
// Counting methods that give that used in showStatistics method
    public void updateBC()
    {
        this.borrowCount++;
    }
    public int getBC()
    {
        return this.borrowCount;
    }
    public void updateRC()
    {
        this.returnCount++;
    }
    public int getRC()
    {
        return this.returnCount;
    }
    public void updateDate()
    {
        this.daysLeft++;
    }
    public double computeLateRefundPenalty() {
        return Math.max(0, this.getDaysLeft() * this.getFine());
    }

}
