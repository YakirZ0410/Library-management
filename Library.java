//Author: Yakir Zindani
//ID: 207872664
package HW1;
import java.util.ArrayList;
import java.util.List;
/**
 * A class for managing a library. The underlying data structure for this class
 * minimizes memory use.
 * @author Tal
 */
public class Library {
    public static int idx = 0;
    private DataStructure <Book> library;
    private DataStructure <Book> borrowedBooks;
    /**
     * Creates a brand-new library with no books.
     */
    public Library() {
        this.library = new DataStructure<>();
        this.borrowedBooks = new DataStructure<>();
    }
    /**
     * Adds a new book to the library with a copy quantity of one.
     *
     * @param name     the name of the book
     * @param author   the author of the book
     */
    public void addBook(String name, String author) {

        Book newBook = new Book(name, author);
        boolean flag = false;
        for (int i = 0; i < library.size(); i++)
        {
            if (newBook.equals(library.getData(i)))
            {
                flag = true;
                System.out.println("The book cannot be added, there is an identical book in the library");
            }
        }
        for (int i = 0; i < borrowedBooks.size(); i++)
        {
            if (newBook.equals(borrowedBooks.getData(i)))
            {
                flag = true;
                System.out.println("The book cannot be added, there is an identical book in the library");
            }
        }
        if (flag == false)
            library.addToEnd(newBook);
    }
    /**
     * Removes a book from the library based on its unique ID.
     *
     * @param uniqueID the unique ID of the book to be removed
     */
    public void removeBooks(int uniqueID) {
        boolean flag = false;
        for (int i = 0; i < library.size(); i++)
        {
            Book removeBook = (Book) library.getData(i);
            if (removeBook.getUniqueID() == uniqueID)
            {
                flag = true;
                library.delete(removeBook);
                System.out.println("Book with unique ID " + uniqueID + " has been removed.");
            }
        }
        if (!flag)
            System.out.println("Book with unique ID " + uniqueID + " was not found in the library.");
    }
    /**
     * Borrows the book with the specified unique ID.
     *
     * @param uniqueID the unique ID of the book to be borrowed
     * @return An unmodifiable list of books.
     */
    public DataStructure <Book> borrowBook(int uniqueID) {

        for (int i = 0; i < library.size(); i++) {
            Book book = (Book) library.getData(i);
            if (book.getUniqueID() == uniqueID) {
                borrowedBooks.addToEnd(book);
                library.delete(book);
                return borrowedBooks;
            }
        }
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = (Book) borrowedBooks.getData(i);
            if (book.getUniqueID() == uniqueID) {
                System.out.println("The book is already borrowed");
                return null;
            }
        }
        System.out.println("does not exist in the library with this uniqueID");
        return null;
    }
    /**
     * borrow all books with the specified author name from the library.
     *
     * @param author_name The name of the author we would like to borrow all his books
     * @return An unmodifiable list of books
     */
    public List<Book> borrowAllBooks(String author_name) {

        List<Book> booksByAuthor = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < library.size(); i++) {
            Book book = (Book) library.getData(i);
            if (book.getAuthor().equals(author_name)) {
                flag = true;
                booksByAuthor.add(book);
                borrowedBooks.addToEnd(book);
                library.delete(book);
            }
        }
        if (flag == true)
            return booksByAuthor;
        else {
            System.out.println("There is no book available to borrow that the author wrote");
        }
        return null;
    }
    /**
     * Sorting the books in the library according to the nunique ID
     * This method has a time complexity of O(n log n).
     * @return A sorted unmodifiable list of all the books in the library.
     */
    public void sortedByUniqueID() {
        if (library.size() <=1)
            System.out.println("Can be sorted when there are more than 2 books");
        else{
            DataStructure<Book> sortedLibrary = mergeSort(library);
            DataStructure<Book> reversSortedLibrary = new DataStructure<>();
            for (int i = sortedLibrary.size() -1; i >= 0; i-- )
                reversSortedLibrary.addToEnd((Book)sortedLibrary.getData(i));
            library = reversSortedLibrary;
        }
    }
    /**
     * Sorts the given data structure of books by their unique ID using the merge sort algorithm.
     * This method has a time complexity of O(n log n).
     * @param dataStructure the data structure to be sorted
     * @return a new data structure containing the sorted books
     */
    private DataStructure<Book> mergeSort(DataStructure<Book> dataStructure) {

        if (dataStructure.size() <= 1) {
            return dataStructure;
        }

        DataStructure<Book> left = new DataStructure<>();
        DataStructure<Book> right = new DataStructure<>();
        int middle = dataStructure.size() / 2;
        for (int i = 0; i < middle; i++) {
            left.addToEnd((Book)dataStructure.getData(i));
        }
        for (int i = middle; i < dataStructure.size(); i++) {
            right.addToEnd((Book) dataStructure.getData(i));
        }

        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted halves
        return merge(left, right);
    }
    /**
     * Merges two sorted data structures of books into a single sorted data structure.
     * @param left  the left data structure to merge
     * @param right the right data structure to merge
     * @return a new data structure containing the merged and sorted books
     */
    private DataStructure<Book> merge(DataStructure<Book> left, DataStructure<Book> right) {

        DataStructure<Book> merged = new DataStructure<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            Book leftBook = (Book) left.getData(leftIndex);
            Book rightBook = (Book) right.getData(rightIndex);
            if (leftBook.getUniqueID() < rightBook.getUniqueID()) {
                merged.addToEnd(leftBook);
                leftIndex++;
            } else {
                merged.addToEnd(rightBook);
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            merged.addToEnd((Book) left.getData(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            merged.addToEnd((Book) right.getData(rightIndex));
            rightIndex++;
        }
        return merged;
    }
    /**
     * Returns the total number of books in the library.
     *
     * @return the total number of books in the library
     */
    public int totalBooksInLibrary() {
        return (library.size() + borrowedBooks.size());
    }

    /**
     * Returns the total number of books available for loan in the library
     * @return the total number of books available in the library
     */
    public int totalAvailableBooks() {
        return library.size();
    }

    /**
     * Returns the total number of books currently on loan in the library
     * @return the total number of books currently on loan in the library
     */
    public int totalLoanBooks() {
        return borrowedBooks.size();
    }

    /**
     * return the name of the author who wrote the most books in the library.
     */
    public String authorWithMostBooks() {

        String mostPopularAuthor = null;

        if (library.size() > 0) {
            int maxBooks = 0;

            for (int i = 0; i < library.size(); i++) {

                Book book = (Book) library.getData(i);
                String author = book.getAuthor();
                int authorBooks = countBooksByAuthor(author);

                if (authorBooks > maxBooks) {
                    mostPopularAuthor = author;
                    maxBooks = authorBooks;
                }
            }
            return mostPopularAuthor;
        }
        else
            System.out.println("There are no books in the library");
        return mostPopularAuthor;
    }

    /**
     * The function receives an ID of a borrowed book and returns it to the library
     * (makes it available again).
     * @param uniqueID the unique ID of the book to be return
     * @return If the book was indeed borrowed and returned, the function will return true, otherwise it will return false
     */
    public boolean returnBook(int uniqueID) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book borrowBook = (Book) borrowedBooks.getData(i);
            if (borrowBook.getUniqueID() == uniqueID) {
                library.addToEnd(borrowBook);
                borrowedBooks.delete(borrowBook);
                return true;
            }
        }
        System.out.println("There is no borrowed book with this uniqueID");
        return false;
    }
    /**
     * The function receives an ID of a book and returns if its borrowed .
     * @param uniqueID the unique ID of the book
     * @return If the book was borrowed the function will return true, otherwise it will return false
     */
    public boolean isBorrowed(int uniqueID) {

        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = (Book) borrowedBooks.getData(i);
            if (book.getUniqueID() == uniqueID) {
                return true;
            }
        }
        System.out.println("There is no borrowed book with this uniqueID");
        return false;
    }
    /**
     * Counts the number of books written by a specific author in the library.
     *
     * @param author the name of the author
     * @return the number of books written by the specified author
     */
    private int countBooksByAuthor(String author) {

        int count = 0;
        for (int i = 0; i < library.size(); i++) {
            Book book = (Book) library.getData(i);
            if (book.getAuthor().equals(author)) {
                count++;
            }
        }
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = (Book) borrowedBooks.getData(i);
            if (book.getAuthor().equals(author)) {
                count++;
            }
        }
        return count;
    }
    /**
     * Returns a string representation of the books in the library.
     *
     * @return a string containing information about all the books in the library,
     *         with each book on a separate line
     */
    @Override
    public String toString() {
        String result = "Books in the library:\n";
        for (int i = 0; i < library.size(); i++) {
            result += library.getData(i).toString() + "\n";
        }
        return result;
    }
    /**
     * Book class representing a book with name, unique ID, author, and quantity.
     */
    public static class Book {
        private int uniqueID;
        private String name;
        private String author;

        /**
         * Constructor for a book
         * @param name The name of the book.
         * @param author The author of the book.
         */
        public Book(String name, String author) {
            Library.idx = Library.idx+1;
            this.uniqueID = Library.idx;
            this.name = name;
            this.author = author;
        }
        /**
         * Returns the unique ID of the book.
         *
         * @return the unique ID of the book
         */
        public int getUniqueID()
        {
            return this.uniqueID;
        }
        /**
         * Returns the author of the book.
         *
         * @return the author of the book
         */
        public String getAuthor()
        {
            return this.author;
        }
        /**
         * Returns a string representation of the book.
         *
         * @return a string containing information about the book,
         *         including its unique ID, name, and author
         */
        @Override
        public String toString() {
            return "Unique number: " + this.uniqueID +
                    ",name of the book: " + this.name +
                    ",the name of author: " + this.author + '\n';
        }
    }
}
