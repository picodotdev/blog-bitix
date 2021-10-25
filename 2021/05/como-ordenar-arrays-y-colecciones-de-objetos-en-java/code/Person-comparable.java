private static class Person implements Comparable<Person> {

    private String name;
    private int age;
    private LocalDate hired;

    public Person(String name, int age, LocalDate hired) {
        this.name = name;
        this.age = age;
        this.hired = hired;
    }

    ...

    @Override
    public int compareTo(Person o) {
        if (age < o.getAge()) {
            return -1;
        } else if (age > o.getAge()) {
            return 1;
        } else {
            return 0;
        }
    } 
}