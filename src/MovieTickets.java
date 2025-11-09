public class MovieTickets implements IMovieTickets {

    // Calculate total ticket price including 14% VAT
    @Override
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double total = numberOfTickets * ticketPrice;
        total += total * 0.14; // Add 14% VAT
        return total;
    }

    // Validate ticket data
    @Override
    public boolean ValidateData(MovieTicketData data) {
        if (data.getMovieName() == null || data.getMovieName().isEmpty()) {
            return false;
        }
        if (data.getNumberOfTickets() <= 0) {
            return false;
        }
        if (data.getTicketPrice() <= 0) {
            return false;
        }
        return true;
    }
}