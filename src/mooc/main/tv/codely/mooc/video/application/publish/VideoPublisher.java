package tv.codely.mooc.video.application.publish;

import tv.codely.mooc.video.domain.Video;
import tv.codely.mooc.video.domain.VideoDescription;
import tv.codely.mooc.video.domain.VideoTitle;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMediaBus;

public final class VideoPublisher {
    private final EventBus eventBus;
    private final SocialMediaBus socialMediaBus;

    public VideoPublisher(EventBus eventBus, SocialMediaBus socialMediaBus) {
        this.eventBus = eventBus;
        this.socialMediaBus = socialMediaBus;
    }

    public void publish(String rawTitle, String rawDescription) {
        final var title = new VideoTitle(rawTitle);
        final var description = new VideoDescription(rawDescription);

        final var video = Video.publish(title, description);

        eventBus.publish(video.pullDomainEvents());
        socialMediaBus.publish(video.pullSocialMediaEvents());
    }
}
