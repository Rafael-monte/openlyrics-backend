package org.openlyrics.openlyrics.interfaces;

public interface DuplexDtoWrapper<Model, Response extends DtoResponse> {
    Response fromModel(Model input);
    Model toModel();
}
