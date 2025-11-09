public class MovieReport {
    private String[] movies = {"Napoleon", "Oppenheimer"};
    private int[][] sales = {
        {3000, 1500, 1700}, // Napoleon
        {3500, 1200, 1600}  // Oppenheimer
    };

    public void generateReport() {
        System.out.println("Movie Ticket Report (Jan-Mar 2024)");
        System.out.println("---------------------------------");
        for (int i = 0; i < movies.length; i++) {
            int total = 0;
            System.out.print(movies[i] + ": ");
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
                System.out.print(sales[i][j] + " ");
            }
            System.out.println("| Total: " + total);
        }
    }

    public String topMovie() {
        int maxTotal = 0;
        String top = "";
        for (int i = 0; i < movies.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            if (total > maxTotal) {
                maxTotal = total;
                top = movies[i];
            }
        }
        return top;
    }
}