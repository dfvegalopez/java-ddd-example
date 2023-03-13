package tv.codely.mooc.video.application.find;

import tv.codely.mooc.video.domain.*;

import java.util.Optional;

public class VideoFinder {
    private VideoDomainFinder videoDomainFinder;

    public VideoFinder(VideoRepository videoRepository) {
        this.videoDomainFinder = new VideoDomainFinder(videoRepository);
    }

    public Optional<Video> findById(VideoId id) throws VideoNotFoundException {
        return this.videoDomainFinder.findById(id);
    }
}
