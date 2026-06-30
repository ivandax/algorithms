package sorting;

public class SortClient {
    public static void main(String[] args) {
        Integer[] numbers1 = {5, 4, 3, 2, 1};
        SelectionSort.sort(numbers1);
        System.out.println("Selection sort");
        for (Number num : numbers1) {
            System.out.println(num);
        }

        System.out.println("Insertion sort");
        Integer[] numbers2 = {5, 4, 3, 2, 1};
        InsertionSort.sort(numbers2);
        for (Number num : numbers2) {
            System.out.println(num);
        }

        System.out.println("Shell sort");
        Integer[] numbers3 = {5, 4, 3, 2, 1};
        ShellSort.sort(numbers3);
        for (Number num : numbers3) {
            System.out.println(num);
        }

        System.out.println("sorting.InsertionSort.Shuffle");
        Integer[] ordered = {1,2,3,4,5};
        InsertionSort.Shuffle.run(ordered);
        for (Number num : ordered) {
            System.out.println(num);
        }
    }
}
