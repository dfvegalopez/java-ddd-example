package tv.codely.mooc.video.infrastructure;

import tv.codely.mooc.video.domain.Video;
import tv.codely.mooc.video.domain.VideoId;
import tv.codely.mooc.video.domain.VideoRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class VideoRepositoryStubs implements VideoRepository {
    private List<Video> videoList = new LinkedList<>();

    @Override
    public void save(Video video) {
        videoList.add(video);
    }

    @Override
    public List<Video> getAll() {
        return null;
    }

    @Override
    public Video findById(VideoId videoId) {
        return this.videoList.stream()
                .filter(video -> video.getVideoId().value().equals(videoId.value()))
                .findAny().get();
    }

    @Override
    public void update(Video video) {

    }
}
