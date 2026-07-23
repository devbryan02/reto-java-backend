package com.example.dispatch_service.domain.repository;

import com.example.dispatch_service.domain.model.Dispatch;

public interface DispatchRepository {

    Dispatch save(Dispatch dispatch);

}
