package tv.codely.mooc.video.application.publish;

import tv.codely.mooc.video.domain.Video;
import tv.codely.mooc.video.domain.VideoDescription;
import tv.codely.mooc.video.domain.VideoId;
import tv.codely.mooc.video.domain.VideoTitle;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

public final class VideoPublisher {
    private final EventBus eventBus;
    private final SocialMedia socialMedia;

    public VideoPublisher(EventBus eventBus, SocialMedia socialMedia) {
        this.eventBus = eventBus;
        this.socialMedia = socialMedia;
    }

    public void publish(String rawTitle, String rawDescription) {
        final var title = new VideoTitle(rawTitle);
        final var description = new VideoDescription(rawDescription);
        final var videoId = new VideoId("");
        final var video = Video.publish(title, description, videoId);

        this.eventBus.publish(video.pullDomainEvents());
        this.socialMedia.publish(video.pullSocialMediaEvents());
    }
}
