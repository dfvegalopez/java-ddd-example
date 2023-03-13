package tv.codely.mooc.video.domain;

import tv.codely.shared.domain.SocialMediaEvent;

public final class VideoSocialMediaPublished implements SocialMediaEvent {

    private static final String FULL_QUALIFIED_EVENT_NAME = "codelytv.video.video.event.1.video.published";
    private final String title;
    private final String description;

    public VideoSocialMediaPublished(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String socialMediaEventName() {  return FULL_QUALIFIED_EVENT_NAME; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoSocialMediaPublished that = (VideoSocialMediaPublished) o;

        if (!title.equals(that.title)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
