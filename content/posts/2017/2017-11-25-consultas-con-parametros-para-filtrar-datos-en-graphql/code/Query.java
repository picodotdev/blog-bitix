package io.github.picodotdev.blogbitix.graphql.resolver;

...

public class Query implements GraphQLQueryResolver {

    private LibraryRepository libraryRepository;

    public Query(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Book> books(BookFilter filter, DataFetchingEnvironment environment) throws InterruptedException  {
        List<Book> books = libraryRepository.findBooks(filter);
        GraphQLContext context = environment.getGraphQlContext();

        ...

        return books;
    }

    ...
}
