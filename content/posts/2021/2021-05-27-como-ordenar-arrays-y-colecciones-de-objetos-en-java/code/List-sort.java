System.out.println();
System.out.println("List (sorted)");
List<Integer> listSorted = new ArrayList<>(list);
Collections.sort(listSorted);
listSorted.stream().forEach(i -> System.out.println(i));