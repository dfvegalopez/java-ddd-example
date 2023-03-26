package tv.codely.mooc.video.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.support.Assert;
import tv.codely.mooc.video.application.create.VideoCreator;
import tv.codely.mooc.video.domain.*;
import tv.codely.mooc.video.infrastructure.VideoRepositoryFake;
import tv.codely.mooc.video.infrastructure.VideoRepositoryStubs;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class VideoFinderStubsTest {


    private VideoRepository videoRepository;

    @BeforeEach
    void create_video(){
        this.videoRepository = new VideoRepositoryStubs();
        this.videoRepository.save(VideoMother.videoDiego());
        this.videoRepository.save(VideoMother.videoVega());
    }

    @Test
    void find_the_video_created() throws VideoNotFoundException {
        final var videoFinder = new VideoFinder(this.videoRepository);
        final var videoId = new VideoId( "1");

        Video videoFound = videoFinder.findById(videoId);
        Assert.notNull(videoFound);
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
