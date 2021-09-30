package com.sav.assignments.controller;

import com.sav.assignments.dto.AddFeedRequest;
import com.sav.assignments.dto.FeedDTO;
import com.sav.assignments.dto.PageDTO;
import com.sav.assignments.dto.PagingRequestDTO;
import com.sav.assignments.security.CurrentUser;
import com.sav.assignments.service.FeedService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    private FeedService feedService;

    @GetMapping
    public ResponseEntity<PageDTO<FeedDTO>> getFeeds(@CurrentUser User user, PagingRequestDTO pagingRequestDTO) throws Exception {
        logger.info("Received request get all feeds");
        PageDTO<FeedDTO> feeds = feedService.getAllFeeds(user, pagingRequestDTO.getPage(), pagingRequestDTO.getLimit());
        return ResponseEntity.ok(feeds);
    }

    @PostMapping
    public ResponseEntity<FeedDTO> createFeed(@RequestBody AddFeedRequest addFeedRequest) throws NotFoundException {
        logger.info("Received request add new feed: {}", addFeedRequest);
        FeedDTO feedDTO = feedService.add(addFeedRequest);
        logger.info("New Feed added: {}", feedDTO);
        return ResponseEntity.ok(feedDTO);
    }
}
