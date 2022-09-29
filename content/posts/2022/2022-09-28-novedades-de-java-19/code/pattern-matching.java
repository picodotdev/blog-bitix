record Point(int x, int y) {}

static void printSum(Object o) {
    if (o instanceof Point p) {
        int x = p.x();
        int y = p.y();
        System.out.println(x + y);
    }
}