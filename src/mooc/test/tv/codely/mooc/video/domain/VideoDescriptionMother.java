package tv.codely.mooc.video.domain;

public class VideoDescriptionMother {
    public static VideoDescription create() {
        String randomDescription = "";
        return new VideoDescription(randomDescription);
    }
}
