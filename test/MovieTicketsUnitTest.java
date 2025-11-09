import org.junit.Test;
import static org.junit.Assert.*;

public class MovieTicketsUnitTest {

    @Test
    public void CalculateTotalTicketPrice_CalculatedSuccessfully() {
        MovieTickets mt = new MovieTickets();
        double total = mt.CalculateTotalTicketPrice(2, 100); // 2 tickets * 100
        assertEquals(228.0, total, 0.001); // 2*100 + 14% VAT = 228
    }

    @Test
    public void Validation_InvalidMovieName() {
        MovieTickets mt = new MovieTickets();
        MovieTicketData data = new MovieTicketData("", 2, 100);
        assertFalse(mt.ValidateData(data));
    }

    @Test
    public void Validation_InvalidTickets() {
        MovieTickets mt = new MovieTickets();
        MovieTicketData data = new MovieTicketData("Napoleon", 0, 100);
        assertFalse(mt.ValidateData(data));
    }

    @Test
    public void Validation_InvalidPrice() {
        MovieTickets mt = new MovieTickets();
        MovieTicketData data = new MovieTicketData("Napoleon", 2, 0);
        assertFalse(mt.ValidateData(data));
    }

    @Test
    public void Validation_ValidData() {
        MovieTickets mt = new MovieTickets();
        MovieTicketData data = new MovieTicketData("Napoleon", 2, 100);
        assertTrue(mt.ValidateData(data));
    }
}