package tv.codely.mooc.video.application.update;

import tv.codely.mooc.video.domain.*;

import java.util.Optional;

public class VideoUpdater {
    private VideoDomainFinder videoDomainFinder;
    private VideoRepository videoRepository;

    public VideoUpdater(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
        this.videoDomainFinder = new VideoDomainFinder(videoRepository);
    }

    public void update(VideoId videoId, VideoTitle videoTitle, VideoDescription videoDescription) throws VideoNotFoundException {
        Optional<Video> video = this.videoDomainFinder.findById(videoId);
        video.get().update(videoTitle, videoDescription);
        this.videoRepository.update(video.get());
    }
}
