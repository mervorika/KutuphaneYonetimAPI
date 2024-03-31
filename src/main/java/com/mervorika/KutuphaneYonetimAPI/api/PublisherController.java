package com.mervorika.KutuphaneYonetimAPI.api;

import com.mervorika.KutuphaneYonetimAPI.core.config.IModelMapperService;
import com.mervorika.KutuphaneYonetimAPI.core.result.Result;
import com.mervorika.KutuphaneYonetimAPI.core.result.ResultData;
import com.mervorika.KutuphaneYonetimAPI.core.utilies.ResultHelper;
import com.mervorika.KutuphaneYonetimAPI.dto.response.CursorResponse;
import com.mervorika.KutuphaneYonetimAPI.dto.response.publisher.PublisherResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mervorika.KutuphaneYonetimAPI.business.abstracts.IPublisherService;
import com.mervorika.KutuphaneYonetimAPI.entities.Publisher;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    @Autowired
    private IPublisherService publisherService;
    @Autowired
    private IModelMapperService modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(@RequestParam(name = "page",required = false,defaultValue = "0") int page, @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize){
        Page<Publisher> publisherPage=this.publisherService.cursor(page,pageSize);
        Page<PublisherResponse> publisherResponsePage=publisherPage
                .map(publisher -> this.modelMapper.forResponse().map(publisher,PublisherResponse.class));
        return ResultHelper.cursor(publisherResponsePage);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<Publisher> save(@Valid @RequestBody Publisher publisher){
        this.publisherService.save(publisher);
        return ResultHelper.created(publisher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") int id){
        this.publisherService.getById(id);
        this.publisherService.delete(id);
        return ResultHelper.success();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Publisher> update(@Valid @RequestBody Publisher publisher){
        this.publisherService.getById(publisher.getId());
        this.publisherService.update(publisher);
        return ResultHelper.success(publisher);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<Publisher> get(@PathVariable("id") int id){
        Publisher publisher=this.publisherService.getById(id);
        return ResultHelper.success(publisher);
    }
}
