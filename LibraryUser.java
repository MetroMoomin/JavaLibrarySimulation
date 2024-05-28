package Libraryex1;

// LibraryUser
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public interface LibraryUser {

    List<Class<? extends LibraryUser>> subclasses = List.of(Student.class, FacultyMember.class);
    void addFine();

    void printUser();

    void updateL(LibraryItem item, int day);

    void deleteLoan(LibraryItem item);

    void setStatus(boolean status);

    void updateOverdue();

    int getUserType();

    double getUFine();

    int numberOfLoaned(int type);

    int UserID();

    boolean Status();

    boolean isOverdue();

    ArrayList<LibraryItem> getBorrowed();

    // New method to check account balance and sum of late refund penalties
    void checkAccountBalance(int day);

    // Default method to display user account (items borrowed)
    default void displayUserAccount() {
        System.out.println("Items borrowed:");
        for (LibraryItem item : getBorrowed()) {
            item.printInfo();
        }
    }


}