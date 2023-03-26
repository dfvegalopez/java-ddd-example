package tv.codely.mooc.video.application.find;

import org.junit.jupiter.api.Test;
import reactor.core.support.Assert;
import tv.codely.mooc.video.VideoModuleUnitTest;
import tv.codely.mooc.video.application.create.VideoCreator;
import tv.codely.mooc.video.application.update.VideoUpdater;
import tv.codely.mooc.video.domain.*;
import tv.codely.shared.domain.EventBus;
import tv.codely.shared.domain.SocialMedia;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VideoFinderTest extends VideoModuleUnitTest {


    @Test
    void it_should_find_the_created_video() throws VideoNotFoundException {
        var randomVideo = givenARandomVideo();
        var foundVideo = whenFinderIsInvoked(randomVideo);
        thenTheFoundVideoShouldBeTheRandomVideo(randomVideo, foundVideo);
    }

    Video givenARandomVideo() {
        return VideoMother.random();
    }

    Video whenFinderIsInvoked(Video video) throws VideoNotFoundException {
        this.shouldFind(video.getVideoId(), video);
        VideoFinder videoFinder = new VideoFinder(this.repository());
        return videoFinder.findById(video.getVideoId());
    }

    void thenTheFoundVideoShouldBeTheRandomVideo(Video expectedVideo, Video foundVideo) {
        assertEquals(expectedVideo, foundVideo);
    }

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
        when(videoRepository.findById(videoId)).thenReturn(createdVideo);
        Video videoFound = videoFinder.findById(videoId);
        assertEquals(videoFound, createdVideo);
    }
    @Test()
    void find_the_video_not_created() throws VideoNotFoundException{

        var randomVideoId = VideoIdMother.create();
        VideoFinder videoFinder = new VideoFinder(this.repository());
        this.shouldFind(randomVideoId, null);

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> {
            videoFinder.findById(randomVideoId);
        });
        Assert.isTrue(exception.getMessage().contains("video no encontrado"));
    }

}