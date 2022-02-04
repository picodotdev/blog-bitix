package io.github.picodotdev.blogbitix.graphql.repository;

...

public class LibraryRepository {

    ...

    public Book addBook(String title, Long idAuthor, String user) throws PermissionException, ValidationException {
        if (user == null || !user.equals("admin")) {
            throw new PermissionException("Invalid permissions");
        }
        Optional<Author> author = findAuthorById(idAuthor);
        if (!author.isPresent()) {
            throw new ValidationException("Invalid author");
        }

        Book book = new Book(nextId(), title, author.get(), LocalDate.now(), Collections.EMPTY_LIST);
        books.add(book);
        return book;
    }

    ...
}
