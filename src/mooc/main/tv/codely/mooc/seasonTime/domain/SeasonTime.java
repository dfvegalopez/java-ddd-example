package tv.codely.mooc.seasonTime.domain;

public class SeasonTime {

    private Season season;

    public SeasonTime(Season season) {
        this.season = season;
    }

    public String getSeason() {
        if (season.getMonthNumber() >= 6 && season.getMonthNumber() <= 8) return "SUMMER";
        return "OTHER";
    }
}
