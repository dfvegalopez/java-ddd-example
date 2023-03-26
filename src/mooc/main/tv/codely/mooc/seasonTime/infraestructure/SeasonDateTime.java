package tv.codely.mooc.seasonTime.infraestructure;

import tv.codely.mooc.seasonTime.domain.Season;

import java.time.LocalDate;

public class SeasonDateTime implements Season {

    @Override
    public int getMonthNumber() {
        LocalDate localDate = LocalDate.now();
        return localDate.getMonth().getValue();
    }
}
