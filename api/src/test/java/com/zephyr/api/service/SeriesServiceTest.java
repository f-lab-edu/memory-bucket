package com.zephyr.api.service;

import com.zephyr.api.domain.Album;
import com.zephyr.api.domain.Series;
import com.zephyr.api.dto.SeriesCreateServiceDto;
import com.zephyr.api.repository.SeriesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeriesServiceTest {

    @Mock
    private SeriesRepository seriesRepository;

    @Mock
    private AlbumService albumService;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private SeriesService seriesService;

    @Test
    @DisplayName("시리즈 생성 성공")
    void successCreateSeries() {
        SeriesCreateServiceDto serviceDto = new SeriesCreateServiceDto(1L, "시리즈 이름");
        Album album = mock(Album.class);
        when(albumService.get(serviceDto.getAlbumId())).thenReturn(album);
        when(album.getId()).thenReturn(serviceDto.getAlbumId());

        //when
        Series result = seriesService.create(serviceDto);

        //then
        assertNotNull(result);
        assertEquals(serviceDto.getAlbumId(), result.getAlbum().getId());
        assertEquals(serviceDto.getSeriesName(), result.getName());
        assertNull(result.getPostCount());
        assertNull(result.getFirstDate());
        assertNull(result.getLastDate());
        assertNull(result.getThumbnailUrl());

        verify(albumService, times(1)).get(serviceDto.getAlbumId());
        verify(seriesRepository, times(1)).save(any(Series.class));

    }

}