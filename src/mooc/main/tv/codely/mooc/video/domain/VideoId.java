package tv.codely.mooc.video.domain;

public class VideoId {
    private final String value;

    public VideoId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        tv.codely.mooc.video.domain.VideoId that = (tv.codely.mooc.video.domain.VideoId) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
