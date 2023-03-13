package tv.codely.mooc.video.domain;

public class VideoNotFoundException extends  Exception{
    private String message;

    public VideoNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}