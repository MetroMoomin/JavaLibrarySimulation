// package Libraryex1;
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.util.Collection;
// import java.util.List;
// import java.util.Random;

// class LibraryItemTest {

//     @Test
//     void testDaysOverdue() {

//         LibraryItem item = new SampleLibraryItem();
//         item.setDateBorrowed(1);
//         item.updateDate();

//         // Test the daysOverdue method
//         int actualDaysOverdue = item.getDaysLeft();
//         assertEquals(1, actualDaysOverdue);
//     }

//     @Test
//     void testIsOverdue() {
//         LibraryItem item = new SampleLibraryItem();
//         item.setDateBorrowed(1); // Set a known date
//         item.updateDate(); // Simulate passing time

//         // Test the isOverdue method
//         boolean isOverdue = item.isOverdue();
//         assertTrue(isOverdue);
//     }




//     // Helper class for testing
//     private static class SampleLibraryItem extends LibraryItem {
//         @Override
//         public void updateDate() {
//             this.daysLeft++;
//         }
//     }

// }
