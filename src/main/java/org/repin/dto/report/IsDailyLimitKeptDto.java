package org.repin.dto.report;

import lombok.Data;

@Data
public class IsDailyLimitKeptDto {
    Boolean isKept;
    Integer differenceFromLimit;
}
