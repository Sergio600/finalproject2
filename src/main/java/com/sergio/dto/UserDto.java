package com.sergio.dto;

import com.sergio.enums.Role;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private String password;
    private List<FeedbackDto>feedbackDtoList;
    private List<TicketDto> ticketDtoList;
    private List<HistoryDto> historyDtoList;
    private List<CommentDto> commentDtoList;

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FeedbackDto> getFeedbackDtoList() {
        return feedbackDtoList;
    }

    public void setFeedbackDtoList(List<FeedbackDto> feedbackDtoList) {
        this.feedbackDtoList = feedbackDtoList;
    }

    public List<TicketDto> getTicketDtoList() {
        return ticketDtoList;
    }

    public void setTicketDtoList(List<TicketDto> ticketDtoList) {
        this.ticketDtoList = ticketDtoList;
    }

    public List<HistoryDto> getHistoryDtoList() {
        return historyDtoList;
    }

    public void setHistoryDtoList(List<HistoryDto> historyDtoList) {
        this.historyDtoList = historyDtoList;
    }

    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDtoList(List<CommentDto> commentDtoList) {
        this.commentDtoList = commentDtoList;
    }
}
