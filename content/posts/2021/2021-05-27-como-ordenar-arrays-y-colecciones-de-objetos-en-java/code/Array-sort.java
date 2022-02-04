System.out.println();
System.out.println("Array (sorted)");
int[] arraySorted = Arrays.copyOf(array, array.length);
Arrays.sort(arraySorted);
Arrays.stream(arraySorted).forEach(i -> System.out.println(i));