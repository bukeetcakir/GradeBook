package com.bukeetcakir.gradebook.dto.student;

public record StudentSaveRequest(String fullName,
                                 Integer number,
                                 String email,
                                 String gsmNumber) {
}
