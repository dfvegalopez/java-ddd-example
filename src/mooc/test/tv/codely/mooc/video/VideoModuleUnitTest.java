package tv.codely.mooc.video;

import tv.codely.mooc.video.domain.Video;
import tv.codely.mooc.video.domain.VideoId;
import tv.codely.mooc.video.domain.VideoRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class VideoModuleUnitTest {

    private VideoRepository videoRepository;

    protected VideoRepository repository() {
        if (videoRepository == null) videoRepository = mock(VideoRepository.class);
        return videoRepository;
    }

    protected void shouldSave(Video video) {
        verify(this.repository()).save(video);
    }

    protected void shouldFind(VideoId videoId,Video video) {
        when(this.repository().findById(videoId)).thenReturn(video);
    }
}
