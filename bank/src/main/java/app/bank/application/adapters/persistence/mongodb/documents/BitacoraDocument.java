package app.bank.application.adapters.persistence.mongodb.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Map;

@Getter
@Setter
@Document(collection = "bitacora")
public class BitacoraDocument {
    @Id
    private String id;
    private String operationType;
    private Timestamp operationDateTime;
    private Long userId;
    private String userRole;
    private String affectedProductId;
    private Map<String, Object> detailData;
}
