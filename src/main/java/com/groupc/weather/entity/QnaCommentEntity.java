package com.groupc.weather.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.groupc.weather.dto.request.board.PostCommentRequestDto;
import com.groupc.weather.dto.request.common.ManagerDto;
import com.groupc.weather.dto.request.common.UserDto;
import com.groupc.weather.dto.request.qnaBoard.PatchQnaBoardRequestDto;
import com.groupc.weather.dto.request.qnaBoard.PatchQnaCommentRequestDto;
import com.groupc.weather.dto.request.qnaBoard.PostQnaCommentRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class QnaCommentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int qnaCommentNumber;
        private int qnaBoardNumber;
        private String content;
        private String writeDatetime;
        private Integer userNumber;
        private Integer managerNumber;
        private String userNickname;
        private String managerNickname;
        private String userProfileImageUrl;
        private String managerProfileImageUrl;


        public QnaCommentEntity(PostQnaCommentRequestDto dto,UserDto userDto){
        Date now = new Date(); // 시간 데이터 타입 변수 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//시간 데이터 타입 형태 설정
        String writeDatetime = simpleDateFormat.format(now);//형태안에 데이터타입 변수 넣음

        this.qnaBoardNumber=dto.getQnaBoardNumber();
        this.content=dto.getQnaCommentContent();
        this.writeDatetime=writeDatetime;
        this.userNumber=dto.getWriterNumber();
        this.managerNumber=null;
        this.userNickname=userDto.getUserNickname();
        this.managerNickname=null;
        this.userProfileImageUrl=userDto.getUserProfileImageUrl();
        this.managerProfileImageUrl=null;
        }

        public QnaCommentEntity(PostQnaCommentRequestDto dto,ManagerDto managerDto){
                Date now = new Date(); // 시간 데이터 타입 변수 설정
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//시간 데이터 타입 형태 설정
                String writeDatetime = simpleDateFormat.format(now);//형태안에 데이터타입 변수 넣음
        
                this.qnaBoardNumber=dto.getQnaBoardNumber();
                this.content=dto.getQnaCommentContent();
                this.writeDatetime=writeDatetime;
                this.userNumber=null;
                this.managerNumber=dto.getWriterNumber();
                this.userNickname=null;
                this.managerNickname=managerDto.getManagerNickname();
                this.userProfileImageUrl=null;
                this.managerProfileImageUrl=managerDto.getManagerProfileImageUrl();
                }

     
}
