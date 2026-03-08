package app.bank.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class BitacoraEntry {
    private String id;
    private String operationType;
    private Timestamp operationDateTime;
    private User user;
    private String affectedProductId;
    private Map<String, Object> detailData;
}
