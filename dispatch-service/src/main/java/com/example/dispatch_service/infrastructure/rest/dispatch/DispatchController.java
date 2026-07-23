package com.example.dispatch_service.infrastructure.rest.dispatch;

import com.example.dispatch_service.application.dto.CreateDispacthRequest;
import com.example.dispatch_service.application.dto.CreateDispatchResponse;
import com.example.dispatch_service.application.usecase.CreateDispatchUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/dispatch")
@RequiredArgsConstructor
@Tag(name = "Dispatch", description = "Dispatch Service API")
public class DispatchController {

    private final CreateDispatchUseCase createDispatchUseCase;
    private final DispatchHttpMaper httpMaper;

    @PostMapping
    @Operation(summary = "Create a new dispatch")
    public Mono<ResponseEntity<CreateDispatchResponse>> createDispatch(
            @RequestBody @Valid CreateDispacthRequest request){
        return createDispatchUseCase.execute(request)
                .map(dispatch -> ResponseEntity.ok(httpMaper.toResponse(dispatch)));
    }

}
