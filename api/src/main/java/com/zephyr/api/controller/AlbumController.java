package com.zephyr.api.controller;

import com.zephyr.api.request.AlbumCreate;
import com.zephyr.api.request.AlbumMemberRequest;
import com.zephyr.api.request.AlbumUpdate;
import com.zephyr.api.response.AlbumMemberResponse;
import com.zephyr.api.response.AlbumResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {

    @GetMapping("/{albumId}")
    public AlbumResponse get(@PathVariable Long albumId) {
        AlbumResponse response = AlbumResponse.builder()
                .albumId(albumId)
                .albumTitle("테스트 앨범")
                .albumDescription("hello")
                .albumCover("url")
                .createdAt(LocalDateTime.now())
                .build();

        return response;
    }

    @GetMapping
    public List<AlbumResponse> getList() {
        List<AlbumResponse> response = LongStream.range(1, 11).mapToObj(value -> AlbumResponse.builder()
                .albumId(value)
                .albumTitle("테스트 앨범" + value)
                .albumDescription("hello" + value)
                .albumCover("url" + value)
                .createdAt(LocalDateTime.now())
                .build()
        ).toList();

        return response;
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> create(@RequestBody AlbumCreate request) {
        AlbumResponse response = AlbumResponse.builder()
                .albumId(1L)
                .albumTitle(request.getAlbumTitle())
                .albumDescription(request.getAlbumDescription())
                .createdAt(LocalDateTime.now())
                .build();

        return ResponseEntity.created(URI.create("/albums/1")).body(response);
    }

    @DeleteMapping("/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long albumId) {

    }

    @PatchMapping("/{albumId}")
    public AlbumResponse update(@PathVariable Long albumId, @RequestBody AlbumUpdate request) {
        AlbumResponse response = AlbumResponse.builder()
                .albumId(albumId)
                .albumTitle(request.getAlbumTitle())
                .albumDescription(request.getAlbumDescription())
                .albumCover("url")
                .createdAt(LocalDateTime.now())
                .build();

        return response;
    }

    @GetMapping("/{albumId}/members")
    public List<AlbumMemberResponse> getMembers(@PathVariable Long albumId, @RequestBody AlbumMemberRequest request) {
        List<AlbumMemberResponse> response = LongStream.range(1, 11).mapToObj(value -> AlbumMemberResponse.builder()
                .memberId(value)
                .memberName("name" + value)
                .memberProfileImage("url" + value)
                .registerDate(LocalDateTime.now())
                .build()
        ).toList();

        return response;
    }

    @PostMapping("/{albumId}/members")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMember(@PathVariable Long albumId) {

    }

    @DeleteMapping("/{albumId}/members/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long albumId, @PathVariable Long memberId) {

    }
}
