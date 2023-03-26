package tv.codely.mooc.video.domain;

public class VideoIdMother {

    public static  VideoId create() {
        String randomId = "";
        return new VideoId(randomId);
    }
}
