package HW1;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();


        // Adding books
        library.addBook("Book 1", "Author 1");
        library.addBook("Book 2", "Author 2");
        library.addBook("Book 3", "Author 1");
        library.addBook("Book 4", "Author 2");
        library.addBook("Book 5", "Author 2");

        // Displaying total books in the library
        System.out.println("Total books in library: " + library.totalBooksInLibrary());


        // Borrowing a book
        DataStructure<Library.Book> borrowedBooks = library.borrowBook(1);
        System.out.println(borrowedBooks.toString());
        borrowedBooks = library.borrowBook(4);
        System.out.println(borrowedBooks.toString());

        System.out.println("Total books in library: " + library.totalBooksInLibrary());

        library.removeBooks(10);
       // System.out.println("Total books in library: " + library.totalBooksInLibrary());


        // Displaying total available books
        System.out.println("Total available books: " + library.totalAvailableBooks());
        System.out.println("Total loan books: " + library.totalLoanBooks());

        boolean returned = library.returnBook(1);
        if (returned) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Failed to return book.");
        }
        System.out.println(library.isBorrowed(1));
        System.out.println(library.returnBook(4));
        System.out.println(library.returnBook(5));


        // System.out.println(library.authorWithMostBooks());
        System.out.println(library.toString());
        library.sortedByUniqueID();
        System.out.println(library.toString());
/*
        boolean returned2 = library.returnBook(1);
        if (returned2) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Failed to return book.");
        }

*/
    }


}

