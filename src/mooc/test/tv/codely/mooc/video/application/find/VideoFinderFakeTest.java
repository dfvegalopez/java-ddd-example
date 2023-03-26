package tv.codely.mooc.video.application.find;

import org.junit.jupiter.api.Test;
import reactor.core.support.Assert;
import tv.codely.mooc.video.application.create.VideoCreator;
import tv.codely.mooc.video.domain.*;
import tv.codely.mooc.video.infrastructure.VideoRepositoryFake;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VideoFinderFakeTest {

    @Test
    void find_the_video_created() throws VideoNotFoundException {
        final EventBus eventBus = mock(EventBus.class);
        final SocialMedia socialMedia = mock(SocialMedia.class);
        final VideoRepository videoRepository = new VideoRepositoryFake();
        final var videoCreator = new VideoCreator(videoRepository, eventBus, socialMedia);
        final var videoFinder = new VideoFinder(videoRepository);

        final var videoTitle = new VideoTitle("title_1");
        final var videoDescription = new VideoDescription("description_1");
        final var videoId = new VideoId( "1");

        final var createdVideo = videoCreator.create(videoId, videoTitle, videoDescription);
        Video videoFound = videoFinder.findById(videoId);
        assertEquals(videoFound, createdVideo);
    }
    @Test()
    void find_the_video_not_created() {
        final VideoRepository videoRepository = new VideoRepositoryFake();
        final var videoFinder = new VideoFinder(videoRepository);

        final var invalidVideoId = new VideoId( "4");
        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoFinder.findById(invalidVideoId);
        });
        Assert.isTrue(exception.getMessage().contains("video no encontrado"));
    }

}