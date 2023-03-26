package tv.codely.mooc.seasonTime.domain;

import org.junit.jupiter.api.Test;
import reactor.core.support.Assert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SeasonTimeTest {

    @Test
    void should_be_summer() {
        Season season = mock(Season.class);
        when(season.getMonthNumber()).thenReturn(8);
        SeasonTime seasonTime = new SeasonTime(season);
        Assert.isTrue(seasonTime.getSeason().equals("SUMMER"));
    }

    @Test
    void should_not_be_summer() {
        Season season = mock(Season.class);
        when(season.getMonthNumber()).thenReturn(9);
        SeasonTime seasonTime = new SeasonTime(season);
        Assert.isTrue(seasonTime.getSeason().equals("OTHER"));
    }

}