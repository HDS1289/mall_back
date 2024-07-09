package com.my.mall.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder // 부모의 private 필드에서도 접근하여 빌더 패턴을 쓸 수 있게 한다.
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {
	@Builder.Default
	private int page = 1;
	
	@Builder.Default
	private int size = 5;
}
