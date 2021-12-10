public class Board {

    private String[] data;

    public String board() {
        StringBuilder result = new StringBuilder();
        collectRows(result);
        return result.toString();
    }

    private void collectRows(StringBuilder result) {
        for (int i = 0; i < 10; i++) {
            collectRow(result, i);
        }
    }

    private void collectRow(StringBuilder result, int row) {
        for (int i = 0; i < 10; i++) {
            result.append(data[row][i]);
        }
        result.append("\n");
    }
}
