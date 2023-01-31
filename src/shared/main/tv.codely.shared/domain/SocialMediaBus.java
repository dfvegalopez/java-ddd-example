package tv.codely.shared.domain;

import java.util.List;

public interface SocialMediaBus {
    void publish(final List<SocialMediaEvent> events);
}
