package io.github.picodotdev.blogbitix.graphql.resolver;

...

public class BookResolver implements GraphQLResolver<Book> {

    private LibraryRepository libraryRespository;

    public BookResolver(LibraryRepository libraryRespository) {
        this.libraryRespository = libraryRespository;
    }

    public String getIsbn(Book book) throws InterruptedException {
        System.out.printf("Getting ISBN %d...", book.getId());
        Thread.sleep(3000);
        System.out.printf("ok%n");
        return UUID.randomUUID().toString();
    }

    public String getBatchedIsbn(Book book, DataFetchingEnvironment environment) throws InterruptedException {
        GraphQLContext context = environment.getGraphQlContext();
        Map<Long, String> isbns = (Map<Long, String>) context.get("batchedIsbn");
        return isbns.get(book.getId());
    }

    public CommentsConnection getComments(Book book, String after, Long limit) {
        ...
    }

    public CommentsConnection getBatchedComments(Book book, String after, Long limit, DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getGraphQlContext();
        Map<Long, CommentsConnection> batchedComments = (Map<Long, CommentsConnection>) context.get("batchedComments");
        return batchedComments.get(book.getId());
    }
}
