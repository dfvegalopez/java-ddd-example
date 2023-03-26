package tv.codely.mooc.video.domain;

import java.util.Optional;

public class VideoDomainFinder {
    private VideoRepository videoRepository;

    public VideoDomainFinder(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video findById(VideoId videoId) throws VideoNotFoundException {
        var video =  this.videoRepository.findById(videoId);
        if (video == null) throw new VideoNotFoundException("video no encontrado");
        return video;
    }
}
