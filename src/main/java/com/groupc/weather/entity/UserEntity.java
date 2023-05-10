package com.groupc.weather.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.groupc.weather.dto.request.user.PostUserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 데이터베이스의 필드와 직접적인 연관 관계.
// commit test
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {
    @Id
    private Integer userNumber;
    private String name;
    private String nickname;
    private String password;
    private String email;
    private String profileImageUrl;
    private Date birthday;
    private String gender;
    private String adress;
    private String phoneNumber;

    public UserEntity(PostUserRequestDto dto) {
        this.userNumber = dto.getUserNumber();
        this.name = dto.getUserName();
        this.nickname = dto.getUserNickname();
        this.password = dto.getUserPassword();
        this.email = dto.getUserEmail();
        this.profileImageUrl = dto.getUserProfileImageUrl();
        this.birthday = dto.getUserBirthday(); // 등록시 생일은 직접 입력(선택)하는것이기 때문에 SimpleDateFormat 필요 없을듯?
        this.gender = dto.getUserGender();
        this.adress = dto.getUserAdress();
        this.phoneNumber = dto.getUserPhoneNumber();
    }
}