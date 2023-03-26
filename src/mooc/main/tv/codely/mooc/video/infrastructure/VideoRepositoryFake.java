package tv.codely.mooc.video.infrastructure;

import tv.codely.mooc.video.domain.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class VideoRepositoryFake  implements VideoRepository {

    List<Video> videoList = new LinkedList<>();

    public VideoRepositoryFake() {
        this.addFakeVideos();
    }


    @Override
    public void save(Video video) {

    }

    @Override
    public List<Video> getAll() {
        return this.videoList;
    }

    @Override
    public Video findById(VideoId videoId) {
        Optional<Video> videoFound = this.videoList.stream()
                .filter(video -> video.getVideoId().value().equals(videoId.value()))
                .findAny();
        return videoFound.isEmpty() ? null : videoFound.get();
    }

    @Override
    public void update(Video video) {

    }

    private void addFakeVideos() {

        Video fakeVideo1 = Video.create(new VideoTitle("title_1"), new VideoDescription("description_1"), new VideoId("1"));
        Video fakeVideo2 = Video.create(new VideoTitle("title_2"), new VideoDescription("description_2"), new VideoId("2"));
        Video fakeVideo3 = Video.create(new VideoTitle("title_3"), new VideoDescription("description_3"), new VideoId("3"));
        videoList.add(fakeVideo1);
        videoList.add(fakeVideo2);
        videoList.add(fakeVideo3);
    }
}
