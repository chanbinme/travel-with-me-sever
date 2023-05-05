package com.frog.travelwithme.domain.feed.service;

import com.frog.travelwithme.domain.feed.controller.dto.FeedDto;
import com.frog.travelwithme.domain.feed.controller.dto.FeedDto.Response;
import com.frog.travelwithme.domain.feed.entity.Feed;
import com.frog.travelwithme.domain.feed.entity.FeedTag;
import com.frog.travelwithme.domain.feed.entity.Tag;
import com.frog.travelwithme.domain.feed.mapper.FeedMapper;
import com.frog.travelwithme.domain.feed.repository.FeedRepository;
import com.frog.travelwithme.domain.member.entity.Member;
import com.frog.travelwithme.domain.member.service.MemberService;
import com.frog.travelwithme.global.exception.BusinessLogicException;
import com.frog.travelwithme.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 작성자: 김찬빈
 * 버전 정보: 1.0.0
 * 작성일자: 2023/04/17
 **/
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final MemberService memberService;
    private final TagService tagService;
    private final FeedMapper feedMapper;

    public Response postFeed(String email, FeedDto.Post postDto) {
        Feed feed = feedMapper.toEntity(postDto);
        Member saveMember = memberService.findMember(email);
        feed.setMember(saveMember);
        List<Tag> tagList = this.findOrCreateTags(postDto.getTags());
        List<FeedTag> feedTagList = this.createFeedTags(feed, tagList);
        feed.setFeedTagList(feedTagList);
        Feed saveFeed = feedRepository.save(feed);

        return feedMapper.toResponse(saveFeed, email);
    }

    @Transactional(readOnly = true)
    public Response findFeedById(String email, long feedId) {
        return feedMapper.toResponse(this.findFeed(feedId), email);
    }

    @Transactional(readOnly = true)
    public List<Response> findAll(Long lastFeedId, String email) {
        List<Feed> feedList = feedRepository.findAll(lastFeedId, email);

        return feedMapper.toResponseList(feedList);
    }

    public Response updateFeed(String email, long feedId, FeedDto.Patch patchDto) {
        Feed saveFeed = this.findFeed(feedId);
        String writerEmail = saveFeed.getMember().getEmail();
        this.checkWriter(email, writerEmail);
        FeedDto.InternalPatch internalPatchDto = feedMapper.toInternalDto(patchDto);
        List<Tag> tagList = this.findOrCreateTags(internalPatchDto.getTags());
        List<FeedTag> feedTagList = this.createFeedTags(saveFeed, tagList);
        saveFeed.updateFeedData(internalPatchDto);
        feedTagList.forEach(saveFeed::addFeedTag);

        return feedMapper.toResponse(saveFeed, email);
    }

    public void deleteFeed(String email, long feedId) {
        Feed saveFeed = this.findFeed(feedId);
        String writerEmail = saveFeed.getMember().getEmail();
        checkWriter(email, writerEmail);
        feedRepository.deleteById(feedId);
    }

    private void checkWriter(String email, String writerEmail) {
        if (!email.equals(writerEmail)) {
            log.debug("FeedService.checkWriter exception occur email : {}, writerEmail : {}",
                    email, writerEmail);
            throw new BusinessLogicException(ExceptionCode.FEED_WRITER_NOT_MATCH);
        }
    }

    private Feed findFeed(Long feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> {
                    log.debug("FeedService.findFeed exception occur feedId : {}", feedId);
                    throw new BusinessLogicException(ExceptionCode.FEED_NOT_FOUND);
                });
    }

    private List<FeedTag> createFeedTags(Feed feed, List<Tag> tagList) {
        return tagList.stream()
                .map(tag -> FeedTag.builder()
                        .tag(tag)
                        .feed(feed)
                        .name(tag.getName())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Tag> findOrCreateTags(List<String> tagNames) {
        return tagNames.stream()
                .map(tagService::findOrCreateTag)
                .collect(Collectors.toList());
    }
}
