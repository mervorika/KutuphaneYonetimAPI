package com.mervorika.KutuphaneYonetimAPI.core.config;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
