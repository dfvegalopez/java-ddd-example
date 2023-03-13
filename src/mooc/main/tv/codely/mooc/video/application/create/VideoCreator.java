package tv.codely.mooc.video.application.create;

import tv.codely.mooc.video.domain.*;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

public class VideoCreator {
    private final VideoRepository videoRepository;
    private final EventBus eventBus;
    private final SocialMedia socialMedia;

    public VideoCreator(VideoRepository videoRepository, EventBus eventBus, SocialMedia socialMedia) {
        this.eventBus = eventBus;
        this.videoRepository = videoRepository;
        this.socialMedia = socialMedia;
    }

    public Video create(VideoId videoId, VideoTitle videoTitle, VideoDescription videoDescription) {
        final var video = Video.create(videoTitle, videoDescription, videoId);
        this.socialMedia.publish(video.pullSocialMediaEvents());
        this.videoRepository.save(video);
        this.eventBus.publish(video.pullDomainEvents());
        return video;
    }
}
