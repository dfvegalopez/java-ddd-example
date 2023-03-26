package tv.codely.mooc.video.domain;

import tv.codely.mooc.video.application.create.VideoCreator;

public class VideoMother {

    public static Video create(VideoTitle videoTitle, VideoDescription videoDescription, VideoId videoId) {
        return Video.create(
                videoTitle == null ? VideoTitleMother.create() : videoTitle,
                videoDescription == null ? VideoDescriptionMother.create() : videoDescription,
                videoId == null ? VideoIdMother.create() : videoId);
    }

    public static Video random() {
        return Video.create(VideoTitleMother.create(), VideoDescriptionMother.create(), VideoIdMother.create());
    }

    public static Video videoDiego() {
        VideoTitle videoTitle = new VideoTitle("video diego");
        VideoDescription videoDescription = new VideoDescription("video diego description");
        VideoId videoId = new VideoId("1");
        return Video.create(videoTitle, videoDescription, videoId);
    }

    public static Video videoVega() {
        VideoTitle videoTitle = new VideoTitle("video vega");
        VideoDescription videoDescription = new VideoDescription("video vega description");
        VideoId videoId = new VideoId("2");
        return Video.create(videoTitle, videoDescription, videoId);
    }
}
