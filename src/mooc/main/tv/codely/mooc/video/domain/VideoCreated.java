package tv.codely.mooc.video.domain;

import tv.codely.shared.domain.DomainEvent;

public class VideoCreated implements DomainEvent {
    private static final String FULL_QUALIFIED_EVENT_NAME = "codelytv.video.video.event.1.video.created";

    private final String title;
    private final String description;
    private final String id;

    public VideoCreated(String title, String description, String id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String fullQualifiedEventName() {
        return FULL_QUALIFIED_EVENT_NAME;
    }

    public String title() {
        return title;
    }
    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoCreated that = (VideoCreated) o;

        if (!title.equals(that.title)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode() + id.hashCode();
        return result;
    }
}
