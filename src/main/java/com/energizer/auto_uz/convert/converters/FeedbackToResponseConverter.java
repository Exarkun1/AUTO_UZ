package com.energizer.auto_uz.convert.converters;

import com.energizer.auto_uz.dto.response.FeedbackResponse;
import com.energizer.auto_uz.models.users.Feedback;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FeedbackToResponseConverter implements Converter<Feedback, FeedbackResponse> {
    @Override
    public FeedbackResponse convert(Feedback source) {
        return new FeedbackResponse(
                source.getId(),
                source.getScore(),
                source.getNote(),
                source.getTimeOfCreate(),
                source.getPerson().getId(),
                source.getOwner().getId()
        );
    }
}
