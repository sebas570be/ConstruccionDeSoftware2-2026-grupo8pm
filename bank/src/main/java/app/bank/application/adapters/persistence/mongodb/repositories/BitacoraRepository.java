package app.bank.application.adapters.persistence.mongodb.repositories;

import app.bank.application.adapters.persistence.mongodb.documents.BitacoraDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BitacoraRepository extends MongoRepository<BitacoraDocument, String> {
}
