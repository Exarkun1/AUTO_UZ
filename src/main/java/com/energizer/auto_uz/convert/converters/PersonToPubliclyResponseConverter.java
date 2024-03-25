package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.response.PubliclyUserResponse;
import com.energizer.auto_uz.models.advertisements.Advertisement;
import com.energizer.auto_uz.models.users.Feedback;
import com.energizer.auto_uz.models.users.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPubliclyResponseConverter implements Converter<Person, PubliclyUserResponse> {
    @Override
    public PubliclyUserResponse convert(Person source) {
        return new PubliclyUserResponse(
                source.getId(),
                source.getUsername(),
                source.getRole().name(),
                source.getAvgScore(),
                source.getAdvertisements().stream().map(Advertisement::getId).toList(),
                source.getFeedbacks().stream().map(Feedback::getId).toList()
        );
    }
}
