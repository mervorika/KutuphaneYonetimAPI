package com.mervorika.KutuphaneYonetimAPI.business.abstracts;

import com.mervorika.KutuphaneYonetimAPI.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {
    Publisher getById(int id);
    Publisher save(Publisher publisher);
    Publisher update(Publisher publisher);
    boolean delete(int id);
    Page<Publisher> cursor(int page, int pageSize);
}
