package testing;
import org.junit.*;
import static org.junit.Assert.*;

public class LingkaranTest {

    @Test
    public void testHitungLuasDenganRadiusPositif() {
        double radius = 5.0;
        double expected = 3.14 * radius * radius;
        double actual = Lingkaran.hitungLuas(radius);
        assertEquals(expected, actual, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHitungLuasDenganRadiusNegatif() {
        double radius = -2.0;
        Lingkaran.hitungLuas(radius);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHitungLuasDenganRadiusNol() {
        double radius = 0.0;
        Lingkaran.hitungLuas(radius);
    }
}
