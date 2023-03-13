package tv.codely.mooc.video.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.video.domain.*;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class VideoCreatorTest {
    @Test
    void publish_the_video_created_domain_event() {
        final EventBus eventBus = mock(EventBus.class);
        final SocialMedia socialMedia = mock(SocialMedia.class);
        final VideoRepository videoRepository = mock(VideoRepository.class);
        final var videoCreator = new VideoCreator(videoRepository, eventBus, socialMedia);

        final var videoTitle = new VideoTitle("\uD83C\uDF89 New YouTube.com/CodelyTV video title");
        final var videoDescription = new VideoDescription("This should be the video description \uD83D\uDE42");
        final var videoId = new VideoId( "1");

        final var expectedVideo = videoCreator.create(videoId, videoTitle, videoDescription);

        final var expectedVideoCreated = new VideoCreated(videoTitle.value(), videoDescription.value(), videoId.value());
        final var expectedVideoSocialMediaCreated = new VideoSocialMediaPublished(videoTitle.value(), videoDescription.value());

        verify(videoRepository).save(expectedVideo);
        verify(eventBus).publish(List.of(expectedVideoCreated));
        verify(socialMedia).publish(List.of(expectedVideoSocialMediaCreated));
    }

}