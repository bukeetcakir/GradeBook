package com.bukeetcakir.gradebook.dto.student;

public record StudentUpdateRequest(String fullName,
                                   String email,
                                   String gsmNumber) {
}
