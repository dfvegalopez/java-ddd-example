package tv.codely.shared.domain;

import java.util.List;

public interface SocialMedia {
    void publish(List<SocialMediaEvent> socialMediaEvents);
}
