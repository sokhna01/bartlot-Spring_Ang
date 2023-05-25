package com.bartlot.Server.entity;

// import java.sql.Date;
// import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_questions_answers")

public class UsersQA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // JPA maps to the SERIAL data type in PostgreSQL automatically, so we, don't
    // need to specify it explicitly.

    @Column(name = "id_user")
    private Integer userId;

    @Column(name = "id_question")
    private Integer idQuestion;

    @Column(name = "answer", length = 100)
    private String answer;

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

// @Override
// public int hashCode() {
// final int prime = 31;
// int result = 1;
// result = prime * result + ((id == null) ? 0 : id.hashCode());
// result = prime * result + ((userId == null) ? 0 : userId.hashCode());
// result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
// result = prime * result + ((answer == null) ? 0 : answer.hashCode());
// return result;
// }

// @Override
// public boolean equals(Object obj) {
// if (this == obj)
// return true;
// if (obj == null)
// return false;
// if (getClass() != obj.getClass())
// return false;
// UsersQA other = (UsersQA) obj;
// if (id == null) {
// if (other.id != null)
// return false;
// } else if (!id.equals(other.id))
// return false;
// if (userId == null) {
// if (other.userId != null)
// return false;
// } else if (!userId.equals(other.userId))
// return false;
// if (questionId == null) {
// if (other.questionId != null)
// return false;
// } else if (!questionId.equals(other.questionId))
// return false;
// if (answer == null) {
// if (other.answer != null)
// return false;
// } else if (!answer.equals(other.answer))
// return false;
// return true;
// }

// @Override
// public String toString() {
// return "UsersQA [id=" + id + ", userId=" + userId + ", questionId=" +
// questionId + ", answer=" + answer + "]";
// }