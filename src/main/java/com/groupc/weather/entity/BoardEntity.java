package com.groupc.weather.entity;

import com.groupc.weather.dto.request.board.PostBoardRequestDto;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
private int boardNumber;
private int userNumber;
private String title;
private String content;
private String boardImageUrl;
private String writeDatetime;
private int temperature;
private String weatherInfo;
private int viewCount;


        public BoardEntity(PostBoardRequestDto dto) {
                Date now = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String writeDatetime = simpleDateFormat.format(now);

                this.boardNumber = dto.getBoardNumber();
                this.title = dto.getTitle();
                this.content = dto.getContent();
                this.boardImageUrl = dto.getImageUrl();
                this.weatherInfo = writeDatetime;
                this.viewCount = 0;
                
        }

}