package com.groupc.weather.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupc.weather.dto.response.board.GetBoardFirstViewDto;
import com.groupc.weather.dto.response.board.GetBoardListResponseDto;
import com.groupc.weather.dto.response.board.GetBoardListResponsetop5Dto;
import com.groupc.weather.dto.response.board.GetBoardResponseDto;
import com.groupc.weather.entity.primaryKey.LikeyPk;
import com.groupc.weather.common.model.AuthenticationObject;
import com.groupc.weather.dto.ResponseDto;
import com.groupc.weather.dto.request.board.PatchBoardRequestDto;
import com.groupc.weather.dto.request.board.PostBoardRequestDto;
import com.groupc.weather.dto.request.board.PostBoardRequestDto2;
import com.groupc.weather.service.BoardService;
import com.groupc.weather.service.BoardService2;
import com.groupc.weather.service.implement.BoardServiceImplement;
import com.groupc.weather.service.implement.BoardServiceImplement2;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/photoBoard")
@RequiredArgsConstructor
public class BoardController2 {
    private final BoardService2 boardService;
    private final BoardServiceImplement2 boardServiceImplement;
    
    // 1. 게시물 작성
    @PostMapping("post")
    public ResponseEntity<ResponseDto> postBoard(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @Valid @RequestBody PostBoardRequestDto2 requestBody
    ){
        ResponseEntity<ResponseDto> response = boardService.postBoard(authenticationObject,requestBody);
        return response;
    }
    // @PostMapping("post")
    // public ResponseEntity<ResponseDto> postBoard(
    //         @Valid @RequestBody PostBoardRequestDto requestBody){
    //     ResponseEntity<ResponseDto> response = boardService.postBoard(requestBody);
    //     return response;
    // }


    
    // 2. 특정게시물 조회
    @GetMapping("/view/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
            @PathVariable("boardNumber") Integer boardNumber) {
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    // 3. 게시물 목록 조회(본인 작성)
    @GetMapping("/myself")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardMyList(
        @AuthenticationPrincipal String userEmail){
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardMyList(userEmail);
        return response;
    }

    // 4. TOP5 게시물 목록 조회
    @GetMapping("/top5")
    public ResponseEntity<? super GetBoardListResponsetop5Dto>getBoardtop5(){
        ResponseEntity<? super GetBoardListResponsetop5Dto> response = boardService.getBoardTop5();
        return response;
    }

    // 5. 일반게시물 목록 조회(최신순)
    @GetMapping("/list")
    public ResponseEntity<? super GetBoardListResponseDto> getBoard(){
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardList();
        return response;
    }

    // 6. 첫화면 일반 게시물 목록
    @GetMapping("/firstView")
    public ResponseEntity<? super GetBoardFirstViewDto> getBoardFirstView(){
        ResponseEntity<? super GetBoardFirstViewDto> response = boardService.getBoardFirstView();
        return response;

    }

    // 7. 특정 게시물 수정
    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchBoard(
        @Valid @RequestBody String userEmail, PatchBoardRequestDto dto
    ){
        ResponseEntity<ResponseDto> response = boardService.patchBoard(userEmail, dto);
        return response;
    }

    // 8 . 특정 게시물 삭제
    @DeleteMapping("/{userNumber}/{boardNumber}")
    public ResponseEntity<ResponseDto> deleteBoard(
        @PathVariable("userNumber") Integer userNumber,
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<ResponseDto> response = boardService.deleteBoard(userNumber, boardNumber);
        return response;
    }



  //=====================================================================================================


    // 9. 특정 게시물 좋아요 등록
    @PostMapping("/like")
    public ResponseEntity<ResponseDto> likeBoard(
        @Valid @RequestBody LikeyPk likeyPk){
        ResponseEntity<ResponseDto> response = boardService.likeBoard(likeyPk);
        return response;
    }


    // 10. 특정 게시물 좋아요 해제

    @DeleteMapping("/likeDelete/{userNumber}/{boardNumber}")
    public ResponseEntity<ResponseDto> likeDeleteBoard(
        @PathVariable("userNumber") Integer userNumber,
        @PathVariable("boardNumber") Integer boardNumber){
        ResponseEntity<ResponseDto> response = boardService.likeDeleteBoard(userNumber,boardNumber);
        return response;
    }
    


    // 11. 특정 유저 좋아요 게시물 조회

    @GetMapping("/Likelist/{userNumber}")
    public ResponseEntity<? super GetBoardListResponseDto> getLikeBoardList(@PathVariable("userNumber") Integer userNumber){
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getLikeBoardList(userNumber);
        return response;
    }

    // 12-1-1. 특정 게시물 검색(비회원)
    @GetMapping("/search/{searchWord}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWord(
        @PathVariable("searchWord") String searchWord,
        @PathVariable("weatherMain") String weatherMain,
        @PathVariable("minTemperature") Integer minTemperature, 
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(searchWord);
        return response;
    }

    // 12-1-2. 특정 게시물 검색(회원)
    @GetMapping("/search/user/{searchWord}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWordUserResponseEntity(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("searchWord") String searchWord
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(authenticationObject, searchWord);
        return response;
    }

    // 12-2-1. 특정 게시물 검색(비회원) (검색어 + 날씨) 
    @GetMapping("/search/{searchWord}/{weather}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWordAndWeather(
        @PathVariable("searchWord") String searchWord,
        @PathVariable("weather") String weather
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(searchWord, weather);
        return response;
    }

    // 12-2-2. 특정 게시물 검색(회원) (검색어 + 날씨)
    @GetMapping("/search/user/{searchWord}/{weather}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWordAndWeatherForUser(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("searchWord") String searchWord,
        @PathVariable("weather") String weather
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(authenticationObject, searchWord, weather);
        return response;
    }

    // 12-3-1. 특정 게시물 검색(비회원) (검색어 + 기온)
    @GetMapping("/search/{searchWord}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWordAndTemperature(
        @PathVariable("searchWord") String searchWord,
        @PathVariable("minTemperature") Integer minTemperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(searchWord, minTemperature, maxTemperature);
        return response;
    }

    // 12-3-2. 특정 게시물 검색(회원) (검색어 + 기온)
    @GetMapping("/search/user/{searchWord}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWordAndTemperatureForUser(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("searchWord") String searchWord,
        @PathVariable("minTemperature") Integer minTemperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(authenticationObject, searchWord, minTemperature, maxTemperature);
        return response;
    }

    // 12-4-1. 특정 게시물 검색(비회원) (검색어 + 날씨 + 기온)
    @GetMapping("/search/{searchWord}/{weather}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWordAndAll(
        @PathVariable("searchWord") String searchWord,
        @PathVariable("weather") String weather,
        @PathVariable("minTemperature") Integer temperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(searchWord, weather, temperature, maxTemperature);
        return response;
    }

    // 12-4-2. 특정 게시물 검색(회원) (검색어 + 날씨 + 기온)
    @GetMapping("/search/user/{searchWord}/{weather}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> searchListByWordAndAllForUser(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("searchWord") String searchWord,
        @PathVariable("weather") String weather,
        @PathVariable("minTemperature") Integer minTemperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByWord(authenticationObject, searchWord, weather, minTemperature, maxTemperature);
        return response;
    }

    
    // 13-1-1. 특정 게시물 검색(비회원) (해시태그)
    @GetMapping("/searchHash/{hashtag}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtag(
        @PathVariable("hashtag") String hashtag
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(hashtag);
        return response;
    }

    // 13-1-2. 특정 게시물 검색(회원) (해시태그)
    @GetMapping("/searchHash/user/{hashtag}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtagForUser(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("hashtag") String hashtag
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(authenticationObject, hashtag);
        return response;
    }

    // 13-2-1. 특정 게시물 검색(비회원) (해시태그 + 날씨)
    @GetMapping("/searchHash/{hashtag}/{weather}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtagAndWeather(
        @PathVariable("hashtag") String hashtag,
        @PathVariable("weather") String weather
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(hashtag, weather);
        return response;
    }

    // 13-2-2. 특정 게시물 검색(회원) (해시태그 + 날씨)
    @GetMapping("/searchHash/user/{hashtag}/{weather}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtagAndWeatherForUser(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("hashtag") String hashtag,
        @PathVariable("weather") String weather
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(authenticationObject, hashtag, weather);
        return response;
    }

    // 13-3-1. 특정 게시물 검색(비회원) (해시태그 + 기온)
    @GetMapping("/searchHash/{hashtag}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtagAndTemperature(
        @PathVariable("hashtag") String hashtag,
        @PathVariable("minTemperature") Integer minTemperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(hashtag, minTemperature, maxTemperature);
        return response;
    }

    // 13-3-2. 특정 게시물 검색(회원) (해시태그 + 기온)
    @GetMapping("/searchHash/user/{hashtag}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtagAndTemperatureForUser(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("hashtag") String hashtag,
        @PathVariable("minTemperature") Integer minTemperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(authenticationObject, hashtag, minTemperature, maxTemperature);
        return response;
    }

    // 13-4-1. 특정 게시물 검색(비회원) (해시태그 + 날씨 + 기온)
    @GetMapping("/searchHash/{hashtag}/{weather}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtagAndAll(
        @PathVariable("hashtag") String hashtag,
        @PathVariable("weather") String weather,
        @PathVariable("minTemperature") Integer minTemperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(hashtag, weather, minTemperature, maxTemperature);
        return response;
    }

    // 13-4-2. 특정 게시물 검색(회원) (해시태그 + 날씨 + 기온)
    @GetMapping("/searchHash/user/{hashtag}/{weather}/{minTemperature}/{maxTemperature}")
    public ResponseEntity<? super GetBoardListResponseDto> getSearchListByHashtagAndAllForUser(
        @AuthenticationPrincipal AuthenticationObject authenticationObject,
        @PathVariable("hashtag") String hashtag,
        @PathVariable("weather") String weather,
        @PathVariable("minTemperature") Integer minTemperature,
        @PathVariable("maxTemperature") Integer maxTemperature
    ) {
        ResponseEntity<? super GetBoardListResponseDto> response =
            boardServiceImplement.getSearchListByHashtag(authenticationObject, hashtag, weather, minTemperature, maxTemperature);
        return response;
    }






}
