//package org.example.bank.until;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.Serializable;
//
//@Entity
//@Table(name = "bankusers")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class User implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id", nullable = false, updatable = false)
//    private Long user_id;
//
//    @Column(name = "login", nullable = false, unique = true)
//    private String login;
//
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "created_at", nullable = false)
//    private String createdAt;
//
//
//    // Переопределение методов equals и hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        return user_id != null ? user_id.equals(user.user_id) : user.user_id == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return user_id != null ? user_id.hashCode() : 0;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + user_id +
//                ", username='" + login + '\'' +
//                ", email='" + email + '\'' +
//                ", createdAt='" + createdAt + '\'' +
//                '}';
//    }
//}
//todo full class. Its not work normal -_-