public class Board {

    private String[] data;

    public String board() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                result.append(data[i][j]);
            }
            result.append("\n");
        }

        return result.toString();
    }
}
