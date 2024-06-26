package univers.player;

import exception.PlayerAttributeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NormalPlayerTest {

    @Test
    public void testGetAttribute() throws PlayerAttributeException {
        NormalPlayer player = new NormalPlayer("Test", "HUMAN");
        assertEquals(100, player.getAttribute("health"));
        assertEquals(10, player.getAttribute("strength"));
        assertThrows(PlayerAttributeException.class, () -> player.getAttribute("invalid"));
    }

    @Test
    public void testInteract() throws PlayerAttributeException {
        NormalPlayer player = new NormalPlayer("Test", "HUMAN");
        assertEquals(100, player.getAttribute("health"));
        assertEquals(-10, player.interact(-10, "health"));
        assertEquals(90, player.getAttribute("health"));
        assertThrows(PlayerAttributeException.class, () -> player.interact(-10, "invalid"));
    }
}
