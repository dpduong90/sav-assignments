package com.sav.assignments.service.impl;

import com.google.common.base.Preconditions;
import com.sav.assignments.dto.AddFeedRequest;
import com.sav.assignments.dto.FeedDTO;
import com.sav.assignments.dto.PageDTO;
import com.sav.assignments.entity.AppUser;
import com.sav.assignments.entity.Feed;
import com.sav.assignments.repository.FeedRepository;
import com.sav.assignments.repository.UserRepository;
import com.sav.assignments.service.FeedService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {
    private static final Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public FeedDTO add(AddFeedRequest addFeedRequest) throws NotFoundException {
        Preconditions.checkArgument(!StringUtils.isEmpty(addFeedRequest.getMessage()), "Feed message is empty");

        AppUser user = userRepository.findOneByUsername(addFeedRequest.getUserName())
            .orElseThrow(() -> new NotFoundException(String.format("User not found with username %s", addFeedRequest.getUserName())));
        FeedDTO response;

        try {
            Feed feed = new Feed();
            feed.setMessage(addFeedRequest.getMessage());
            feed.setUser(user);
            Feed savedFeed = feedRepository.saveAndFlush(feed);
            response = new FeedDTO(savedFeed);
        } catch (Exception e) {
            logger.error("Add new author failed", e);
            throw e;
        }

        return response;
    }

    @Override
    public PageDTO<FeedDTO> getAllFeeds(User user, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Feed> feedPage = feedRepository.findAll(pageable);

        List<FeedDTO> authorDTOS = feedPage.stream().map(FeedDTO::new).collect(Collectors.toList());
        return new PageDTO<>(feedPage.getTotalPages(), authorDTOS);
    }

    @Override
    public FeedDTO get(Long feedId) throws NotFoundException {
        Preconditions.checkArgument(feedId != null, "Feed id is empty");
        Feed author = getFeed(feedId);
        return new FeedDTO(author);
    }

    private Feed getFeed(Long authorId) throws NotFoundException {
        Optional<Feed> feedOptional = feedRepository.findById(authorId);
        if (!feedOptional.isPresent()) {
            throw new NotFoundException(String.format("Not found feed for id %s", authorId));
        }

        return feedOptional.get();
    }
}
