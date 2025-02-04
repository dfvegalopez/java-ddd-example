package tv.codely.mooc.video.domain;

import tv.codely.shared.domain.AggregateRoot;

import java.util.Objects;

public final class Video extends AggregateRoot {
    private VideoTitle title;
    private VideoDescription description;
    private VideoId videoId;

    private Video(VideoTitle title, VideoDescription description, VideoId videoId) {
        this.title = title;
        this.description = description;
        this.videoId = videoId;
    }

    public static Video create(VideoTitle title, VideoDescription description, VideoId videoId) {
        var video = new Video(title, description, videoId);
        var videoCreated = new VideoCreated(title.value(), description.value(), videoId.value());
        var videoSocialMediaCreated = new VideoSocialMediaPublished(title.value(), description.value());
        video.recordSocialMediaEvent(videoSocialMediaCreated);
        video.record(videoCreated);
        return video;
    }

    public static Video publish(VideoTitle title, VideoDescription description, VideoId videoId) {
        var video = new Video(title, description, videoId);
        var videoCreated = new VideoPublished(title.value(), description.value());
        var videoSocialMediaCreated = new VideoSocialMediaPublished(title.value(), description.value());
        video.record(videoCreated);
        video.recordSocialMediaEvent(videoSocialMediaCreated);
        return video;
    }

    public void update(VideoTitle title, VideoDescription description) {
        this.title = title;
        this.description = description;
    }

    public VideoTitle getTitle() {
        return title;
    }

    public VideoDescription getDescription() {
        return description;
    }

    public VideoId getVideoId() {
        return videoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(title, video.title) && Objects.equals(description, video.description) && Objects.equals(videoId, video.videoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, videoId);
    }
}
