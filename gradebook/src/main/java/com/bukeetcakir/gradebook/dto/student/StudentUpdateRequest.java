package com.bukeetcakir.gradebook.dto.student;

public record StudentUpdateRequest(Long id,
                                   String fullName,
                                   String number,
                                   String email,
                                   String gsmNumber) {
}
