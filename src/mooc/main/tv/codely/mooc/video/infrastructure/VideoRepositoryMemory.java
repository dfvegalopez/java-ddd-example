package tv.codely.mooc.video.infrastructure;

import tv.codely.mooc.video.domain.Video;
import tv.codely.mooc.video.domain.VideoId;
import tv.codely.mooc.video.domain.VideoRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class VideoRepositoryMemory implements VideoRepository {
    @Override
    public void save(Video video) {
        System.out.println("se persiste el video");
    }

    @Override
    public List<Video> getAll() {
        System.out.println("se obtiene un listado de todos los videos ");
        List<Video> videoList = new LinkedList<Video>();
        return videoList;
    }

    @Override
    public Optional<Video> findById(VideoId videoId) {
        System.out.println("se obtiene un video por id");
        return Optional.empty();
    }

    @Override
    public void update(Video video) {
        System.out.println("se actualiza el video");
    }
}
