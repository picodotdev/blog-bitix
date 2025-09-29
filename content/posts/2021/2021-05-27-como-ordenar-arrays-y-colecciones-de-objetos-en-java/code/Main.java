package io.github.picodotdev.blogbitix.javasort;

...

public class Main {

    public static void main(String[] args) {
        int[] array = new int[] { 64, 47, 33, 82, 91, 1, 45 };
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        List<Person> persons = List.of(
                new Person("Juan", 56, LocalDate.of(1982, 3, 26)),
                new Person("María", 24, LocalDate.of(2018, 8, 7)),
                new Person("Marisa", 63, LocalDate.of(2021, 4, 17)),
                new Person("Antonio", 41, LocalDate.of(2020, 5, 2))
        );

        Comparator<Person> nameComparator = new NameComparator();
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
        Comparator<Person> hireComparator = Comparator.comparing(Person::getHired);

        ...
    }

    ...
}
