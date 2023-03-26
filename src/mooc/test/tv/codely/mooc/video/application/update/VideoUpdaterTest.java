package tv.codely.mooc.video.application.update;

import org.junit.jupiter.api.Test;
import reactor.core.support.Assert;
import tv.codely.mooc.video.application.create.VideoCreator;
import tv.codely.mooc.video.application.publish.VideoPublisher;
import tv.codely.mooc.video.domain.*;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoUpdaterTest {
    @Test
    void update_the_video_created() throws VideoNotFoundException {
        final EventBus eventBus = mock(EventBus.class);
        final SocialMedia socialMedia = mock(SocialMedia.class);
        final VideoRepository videoRepository = mock(VideoRepository.class);
        final var videoUpdater = new VideoUpdater(videoRepository);
        final var videoCreator = new VideoCreator(videoRepository, eventBus, socialMedia);

        final var videoTitle = new VideoTitle("\uD83C\uDF89 New YouTube.com/CodelyTV video title");
        final var updateVideoTitle = new VideoTitle("updateVideoTitle");
        final var videoDescription = new VideoDescription("This should be the video description \uD83D\uDE42");
        final var videoId = new VideoId( "1");

        final var createdVideo = videoCreator.create(videoId, videoTitle, videoDescription);
        when(videoRepository.findById(videoId)).thenReturn(createdVideo);
        videoUpdater.update(videoId, updateVideoTitle, videoDescription);
        Assert.isTrue(createdVideo.getTitle().equals(updateVideoTitle));
    }
    @Test()
    void update_the_video_not_created() throws VideoNotFoundException {
        final EventBus eventBus = mock(EventBus.class);
        final SocialMedia socialMedia = mock(SocialMedia.class);
        final VideoRepository videoRepository = mock(VideoRepository.class);
        final var videoUpdater = new VideoUpdater(videoRepository);
        final var videoCreator = new VideoCreator(videoRepository, eventBus, socialMedia);

        final var videoTitle = new VideoTitle("\uD83C\uDF89 New YouTube.com/CodelyTV video title");
        final var videoDescription = new VideoDescription("This should be the video description \uD83D\uDE42");
        final var videoId = new VideoId( "1");
        final var invalidVideoId = new VideoId( "2");

        final var createdVideo = videoCreator.create(videoId, videoTitle, videoDescription);
        final var updateVideoTitle = new VideoTitle("updateVideoTitle");
        when(videoRepository.findById(videoId)).thenReturn(createdVideo);
        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoUpdater.update(invalidVideoId, updateVideoTitle, videoDescription);
        });
        Assert.isTrue(exception.getMessage().contains("video no encontrado"));
    }
}