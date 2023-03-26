package tv.codely.mooc.video.domain;

import java.util.List;
import java.util.Optional;

public interface VideoRepository {
    void save(Video video);
    List<Video> getAll();
    Video findById(VideoId videoId);
    void update(Video video);
}
