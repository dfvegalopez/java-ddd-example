package tv.codely.shared.domain;

import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot {
    private List<DomainEvent> recordedDomainEvents = new LinkedList<>();
    private List<SocialMediaEvent> recordedSocialMediaEvents = new LinkedList<>();

    final public List<DomainEvent> pullDomainEvents() {
        final var recordedDomainEvents = this.recordedDomainEvents;
        this.recordedDomainEvents = new LinkedList<>();

        return recordedDomainEvents;
    }

    final public List<SocialMediaEvent> pullSocialMediaEvents() {
        final var recordedSocialMediaEvents = this.recordedSocialMediaEvents;
        this.recordedSocialMediaEvents = new LinkedList<>();

        return recordedSocialMediaEvents;
    }

    final protected void record(DomainEvent event) {
        recordedDomainEvents.add(event);
    }

    final protected void recordSocialMediaEvent(SocialMediaEvent event) { recordedSocialMediaEvents.add(event); }
}
