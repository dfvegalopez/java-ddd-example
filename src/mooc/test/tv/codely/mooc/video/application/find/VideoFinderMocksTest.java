package tv.codely.mooc.video.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.support.Assert;
import tv.codely.mooc.video.application.create.VideoCreator;
import tv.codely.mooc.video.domain.*;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VideoFinderMocksTest {

    private VideoRepository videoRepository;

    @BeforeEach
    void create_video() {
        this.videoRepository = mock(VideoRepository.class);
    }

    @Test
    void find_the_video_created() throws VideoNotFoundException {
        final var videoFinder = new VideoFinder(videoRepository);
        final var videoId = new VideoId( "1");
        final var createdVideo = VideoMother.create(null, null, videoId);

        when(videoRepository.findById(videoId)).thenReturn(createdVideo);
        Video videoFound = videoFinder.findById(videoId);
        assertEquals(videoFound, createdVideo);
    }
    @Test()
    void find_the_video_not_created() {
        final var videoFinder = new VideoFinder(videoRepository);
        final var videoId = new VideoId( "1");
        final var createdVideo = VideoMother.create(null, null, videoId);

        when(videoRepository.findById(videoId)).thenReturn(createdVideo);
        final var invalidVideoId = new VideoId( "2");

        when(videoRepository.findById(videoId)).thenReturn(createdVideo);
        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoFinder.findById(invalidVideoId);
        });
        Assert.isTrue(exception.getMessage().contains("video no encontrado"));
    }
}
