package app.bank.application.adapters.persistence.sql.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "transfers")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "origin_account", nullable = false)
    private String originAccount;
    @Column(name = "destination_account", nullable = false)
    private String destinationAccount;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;
    @Column(name = "approval_date")
    private Timestamp approvalDate;
    @Column(name = "status")
    private String status;
    @Column(name = "creator_user_id", nullable = false)
    private Long creatorUserId;
    @Column(name = "approver_user_id")
    private Long approverUserId;
}
