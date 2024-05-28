package Libraryex1;

// FacultyMember.java

import java.util.ArrayList;

public final class FacultyMember implements LibraryUser
{
    int UId;
    int type;
    double fine;
    boolean NonRisky;
    boolean inDebt;

    ArrayList<LibraryItem> borrowedItems = new ArrayList<>();

    FacultyMember(int UId)
    {
        this.UId = UId;
        this.type = 2;
        this.fine =0;
        this.NonRisky = true;
        this.inDebt = false;
    }



    @Override
    public void printUser()
    {
        System.out.println("UserID: " + this.UId + "\n" + "User type: " + this.type + "\n" + " NoRisk: " + this.NonRisky + "\n" + "Books on loan: " + numberOfLoaned(Book.type) + "\n" + "Journals on loan: " + numberOfLoaned(Journal.type) + "\n" + "Movies on loan: " + numberOfLoaned(Movie.type) + "\n" + "Total debt: " + this.fine +"$"+"\n");
    }
    @Override
    public int getUserType()
    {
        return this.type;
    }

    @Override
    public double getUFine()
    {
        return this.fine;
    }
    public int UserID()
    {
        return this.UId;
    }
//updating the loans of a faculty member
    @Override
    public void updateL(LibraryItem item, int date)
    {
        if(item.getType() == Book.type && numberOfLoaned(Book.type) < 3)
        {
            item.CurrStock();
            item.setStartDay(2);
            item.setDateBorrowed(date);
            item.updateBC();
            this.borrowedItems.add(item);
        }
        if(item.getType() == Journal.type && numberOfLoaned(Journal.type) < 3)
        {
            item.CurrStock();
            item.setStartDay(2);
            item.setDateBorrowed(date);
            item.updateBC();
            this.borrowedItems.add(item);
        }
        if(item.getType() == Movie.type && numberOfLoaned(Movie.type) < 3)
        {
            item.CurrStock();
            item.setStartDay(2);
            item.setDateBorrowed(date);
            item.updateBC();
            this.borrowedItems.add(item);
        }
    }

    @Override
    public void checkAccountBalance(int day) {
        double totalPenalties = 0;
        for (LibraryItem item : borrowedItems) {
            totalPenalties += item.computeLateRefundPenalty();
        }

        System.out.println("Account balance for day " + day + ": " + totalPenalties);
    }
    @Override
    public ArrayList<LibraryItem> getBorrowed()
    {
        return this.borrowedItems;
    }
//method that clears the loan
    public void deleteLoan(LibraryItem item)
    {
        item.updateRC();
        item.CurrStock();
        item.reset();
        this.borrowedItems.remove(item);
    }

    public void setStatus(boolean risk)
    {
        this.NonRisky = risk;
    }

    @Override
    public boolean Status()
    {
        return NonRisky;
    }
    @Override
    public boolean isOverdue()
    {
        return inDebt;
    }
    @Override
    public void updateOverdue()
    {
        this.inDebt = false;
        for(LibraryItem item : borrowedItems)
        {
            if(item.isOverdue())
            {
                this.inDebt= true;
            }
        }
    }
    @Override
    public void addFine()
    {
        double x = 0;
        for(LibraryItem item :borrowedItems)
        {
            x += item.computeFine(item.getFine());
        }
        this.fine += x - this.fine;
    }

    @Override
    public int numberOfLoaned (int type)
    {
        int total = 0;
        if ( type == 1)
        {
            for(LibraryItem item : borrowedItems)
            {
                if(item.getType() == Book.type)
                {
                    total++;
                }
            }
        }

        if ( type == 2)
        {
            for(LibraryItem item : borrowedItems)
            {
                if(item.getType() == Journal.type)
                {
                    total++;
                }
            }
        }

        if ( type == 3)
        {
            for(LibraryItem item : borrowedItems)
            {
                if(item.getType() == Movie.type)
                {
                    total++;
                }
            }
        }

        return total;


    }


}

