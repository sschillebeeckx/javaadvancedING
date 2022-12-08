package be.abis.exercise.test;

import be.abis.exercise.util.DateUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class DateUtilsTest {

    @Test
    public void parseDateWorks() {
        LocalDate d = DateUtils.parse("10/4/1978");
        assertEquals("April", d.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }


    @Test
    public void parseDateThrowsExceptionWhenWrong() {
        assertThrows(DateTimeParseException.class, () -> DateUtils.parse("10/14/1978"));
    }

    @Test
    public void formatDateWorks(){
        LocalDate ld = LocalDate.of(1978,04,10);
        assertEquals("10/4/1978",DateUtils.format(ld));
    }


}
