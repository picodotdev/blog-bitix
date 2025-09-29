package io.github.picodotdev.blogbitix.graphql.resolver;

...

public class Mutation implements GraphQLMutationResolver {

    private LibraryRepository libraryRepository;

    ...

    public Book addBook(String title, Long author, DataFetchingEnvironment env) throws Exception {
        GraphQLServletContext context = (GraphQLServletContext) env.getGraphQlContext();
        return libraryRepository.addBook(title, author, context.getHttpServletRequest().getHeader("User"));
    }
}