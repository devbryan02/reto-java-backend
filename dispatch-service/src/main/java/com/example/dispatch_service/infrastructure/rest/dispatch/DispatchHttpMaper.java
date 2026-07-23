package com.example.dispatch_service.infrastructure.rest.dispatch;

import com.example.dispatch_service.application.dto.CreateDispatchResponse;
import com.example.dispatch_service.domain.model.Dispatch;
import org.springframework.stereotype.Component;

@Component
public class DispatchHttpMaper {

    public CreateDispatchResponse toResponse(Dispatch dispatch) {
        return new CreateDispatchResponse(
                dispatch.id(),
                dispatch.orderId(),
                dispatch.trackingNumber(),
                dispatch.status(),
                dispatch.createdAt()
        );
    }

}
