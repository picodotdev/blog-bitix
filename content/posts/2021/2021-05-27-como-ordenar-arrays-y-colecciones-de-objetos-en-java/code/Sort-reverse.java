System.out.println();
System.out.println("Array (sorted descending)");
Integer[] arraySorted = Arrays.stream(array).boxed().toArray(Integer[]::new);
Arrays.sort(arraySorted, Collections.reverseOrder());
Arrays.stream(arraySorted).forEach(i -> System.out.println(i));

System.out.println();
System.out.println("List (sorted descending)");
List<Integer> listSortedNatural = new ArrayList<>(list);
Collections.sort(listSortedNatural, Comparator.<Integer>naturalOrder().reversed());
listSortedNatural.stream().forEach(i -> System.out.println(i));

System.out.println();
System.out.println("Persons (sorted descending, age)");
List<Person> personsSortedAge = new ArrayList<>(persons);
Collections.sort(personsSortedAge, ageComparator.reversed());
personsSortedAge.stream().forEach(i -> System.out.println(i));