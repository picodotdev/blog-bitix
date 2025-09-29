System.out.println();
System.out.println("Persons (sorted, natural)");
List<Person> personsSortedNatural = new ArrayList<>(persons);
Collections.sort(personsSortedNatural);
personsSortedNatural.stream().forEach(i -> System.out.println(i));

System.out.println();
System.out.println("Persons (sorted, name)");
List<Person> personsSortedName = new ArrayList<>(persons);
Collections.sort(personsSortedName, nameComparator);
personsSortedName.stream().forEach(i -> System.out.println(i));

System.out.println();
System.out.println("Persons (sorted, age)");
List<Person> personsSortedAge = new ArrayList<>(persons);
Collections.sort(personsSortedAge, ageComparator);
personsSortedAge.stream().forEach(i -> System.out.println(i));

System.out.println();
System.out.println("Persons (sorted, hired)");
List<Person> personsSortedHired = new ArrayList<>(persons);
Collections.sort(personsSortedHired, hireComparator);
personsSortedHired.stream().forEach(i -> System.out.println(i));