package tv.codely.mooc.video.application.find;

import org.junit.jupiter.api.Test;
import reactor.core.support.Assert;
import tv.codely.mooc.video.application.create.VideoCreator;
import tv.codely.mooc.video.application.update.VideoUpdater;
import tv.codely.mooc.video.domain.*;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VideoFinderTest {

    @Test
    void find_the_video_created() throws VideoNotFoundException {
        final EventBus eventBus = mock(EventBus.class);
        final SocialMedia socialMedia = mock(SocialMedia.class);
        final VideoRepository videoRepository = mock(VideoRepository.class);
        final var videoCreator = new VideoCreator(videoRepository, eventBus, socialMedia);
        final var videoFinder = new VideoFinder(videoRepository);

        final var videoTitle = new VideoTitle("\uD83C\uDF89 New YouTube.com/CodelyTV video title");
        final var videoDescription = new VideoDescription("This should be the video description \uD83D\uDE42");
        final var videoId = new VideoId( "1");

        final var createdVideo = videoCreator.create(videoId, videoTitle, videoDescription);
        when(videoRepository.findById(videoId)).thenReturn(Optional.of(createdVideo));
        Optional<Video> videoFound = videoFinder.findById(videoId);
        assertEquals(videoFound.get(), createdVideo);
    }
    @Test()
    void find_the_video_not_created() throws VideoNotFoundException {
        final EventBus eventBus = mock(EventBus.class);
        final SocialMedia socialMedia = mock(SocialMedia.class);
        final VideoRepository videoRepository = mock(VideoRepository.class);
        final var videoCreator = new VideoCreator(videoRepository, eventBus, socialMedia);
        final var videoFinder = new VideoFinder(videoRepository);

        final var videoTitle = new VideoTitle("\uD83C\uDF89 New YouTube.com/CodelyTV video title");
        final var videoDescription = new VideoDescription("This should be the video description \uD83D\uDE42");
        final var videoId = new VideoId( "1");
        final var invalidVideoId = new VideoId( "2");

        final var createdVideo = videoCreator.create(videoId, videoTitle, videoDescription);
        when(videoRepository.findById(videoId)).thenReturn(Optional.of(createdVideo));
        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoFinder.findById(invalidVideoId);
        });
        Assert.isTrue(exception.getMessage().contains("video no encontrado"));
    }

}