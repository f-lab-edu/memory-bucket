package com.zephyr.api.controller;

import com.zephyr.api.domain.Album;
import com.zephyr.api.request.AlbumCreate;
import com.zephyr.api.request.AlbumMemberRequest;
import com.zephyr.api.request.AlbumUpdate;
import com.zephyr.api.response.AlbumListResponse;
import com.zephyr.api.response.AlbumMemberResponse;
import com.zephyr.api.response.AlbumResponse;
import com.zephyr.api.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AlbumCreate request) {
        Album album = albumService.create(1L, request);

        return ResponseEntity.created(URI.create("/albums/" + album.getId())).build();
    }

    @GetMapping("/{albumId}")
    public AlbumResponse get(@PathVariable Long albumId) {
        Album album = albumService.get(albumId, 1L);

        return new AlbumResponse(album);
    }

    @GetMapping
    public List<AlbumListResponse> getList() {
        List<Album> albumsOfMember = albumService.getAlbumsOfMember(1L);

        return albumsOfMember.stream().map(AlbumListResponse::new).toList();
    }

    @DeleteMapping("/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long albumId) {
        albumService.delete(albumId, 1L);
    }

    @PatchMapping("/{albumId}")
    public AlbumResponse update(@PathVariable Long albumId, @RequestBody AlbumUpdate request) {
        Album album = albumService.update(albumId, 1L, request);

        return new AlbumResponse(album);
    }

    @PostMapping("/{albumId}/members")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAlbumMember(@PathVariable Long albumId, @RequestBody AlbumMemberRequest request) {
        Long loginId = 1L;
        albumService.createAlbumMember(albumId, loginId, request);
    }

    @GetMapping("/{albumId}/members")
    public List<AlbumMemberResponse> getAlbumMembers(@PathVariable Long albumId) {
        return albumService.getAlbumMembers(albumId, 1L).stream()
                .map(AlbumMemberResponse::new).toList();
    }

    @DeleteMapping("/{albumId}/members/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumMember(@PathVariable Long albumId, @PathVariable Long memberId) {
        Long loginId = 1L;
        albumService.deleteAlbumMember(albumId, loginId, memberId);
    }
}
