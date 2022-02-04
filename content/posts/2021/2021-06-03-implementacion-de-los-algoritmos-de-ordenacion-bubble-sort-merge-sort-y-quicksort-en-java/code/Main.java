package io.github.picodotdev.blogbitix.javasort;

...

public class Main {

    public static void main(String[] args) {
        ...

        {
            List<Integer> integers = randomList(25);
            List<Integer> bubbleSort = new BubbleSort<Integer>().sort(integers, Integer::compareTo);
            List<Integer> mergeSort = new MergeSort<Integer>().sort(integers, Integer::compareTo);
            List<Integer> quickSort = new QuickSort<Integer>().sort(integers, Integer::compareTo);
            System.out.printf("Integers (%s):    %s%n", integers.size(), integers.stream().map(i -> i.toString()).collect(Collectors.joining(",")));
            System.out.printf("Bubble Sort (%s): %s%n", bubbleSort.size(), bubbleSort.stream().map(i -> i.toString()).collect(Collectors.joining(",")));
            System.out.printf("Merge Sort (%s):  %s%n", mergeSort.size(), mergeSort.stream().map(i -> i.toString()).collect(Collectors.joining(",")));
            System.out.printf("Quick Sort (%s):  %s%n", quickSort.size(), quickSort.stream().map(i -> i.toString()).collect(Collectors.joining(",")));
        }
    }

    ...

    public static List<Integer> randomList(int elements) {
        return new Random().ints(elements, 0, 100).boxed().collect(Collectors.toList());
    }
}
