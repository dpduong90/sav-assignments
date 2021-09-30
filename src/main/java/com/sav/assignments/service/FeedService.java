package com.sav.assignments.service;

import com.sav.assignments.dto.AddFeedRequest;
import com.sav.assignments.dto.FeedDTO;
import com.sav.assignments.dto.PageDTO;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.User;

public interface FeedService {
    FeedDTO add(AddFeedRequest addFeedRequest) throws NotFoundException;

    PageDTO<FeedDTO> getAllFeeds(User user, Integer page, Integer limit) throws Exception;

    FeedDTO get(Long feedId) throws NotFoundException;
}
