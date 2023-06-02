package com.restfulapi.restfulapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "email"),
        @Index(columnList = "nickname")
})
@Entity
public class User extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPid;

    @Setter
    @Column(unique = true, nullable = false)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Column(nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String nickname;

    protected User(){}

    private User(String userId, String password, String email, String nickname) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    public static User getEntity(String userId, String password, String email, String nickname) {
        return new User(userId,password,email,nickname);
    }


}
