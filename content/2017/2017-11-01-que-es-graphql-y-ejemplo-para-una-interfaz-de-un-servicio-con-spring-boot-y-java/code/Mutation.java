package io.github.picodotdev.blogbitix.graphql.resolver;

...

public class Mutation implements GraphQLMutationResolver {

    private LibraryRepository libraryRepository;

    public Mutation(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Book addBook(String title, Long author, DataFetchingEnvironment env) {
        try {
            DefaultGraphqlContext context = (DefaultGraphqlContext) env.getContext();
            return libraryRepository.addBook(title, author, context.getHttpServletRequest().getHeader("User"));
        } catch (Exception e) {
            throw Exceptions.toRuntimeException(e);
        }
    }
}