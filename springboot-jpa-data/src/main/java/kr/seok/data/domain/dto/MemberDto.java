package kr.seok.data.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;
    @Builder
    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

}