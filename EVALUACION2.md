# EVALUACION 2 - ConstruccionDeSoftware2-2026-grupo8pm

## Informacion general
- Estudiante(s): Fray Sebastian Benitez Rodriguez
- Rama evaluada: main
- Commit evaluado: fbe8986dc92e846e736f7c2975c856f42a0349b7
- Fecha: 2026-04-11

## Tabla de calificacion

| Criterio | Peso | Puntaje (1-5) | Aporte |
|---|---|---|---|
| 1. Modelado de dominio | 20% | 5 | 1.00 |
| 2. Modelado de puertos | 20% | 5 | 1.00 |
| 3. Modelado de servicios de dominio | 20% | 5 | 1.00 |
| 4. Enums y estados | 10% | 5 | 0.50 |
| 5. Reglas de negocio criticas | 10% | 5 | 0.50 |
| 6. Bitacora y trazabilidad | 5% | 5 | 0.25 |
| 7. Estructura interna de dominio | 10% | 5 | 0.50 |
| 8. Calidad tecnica base en domain | 5% | 2 | 0.10 |
| **SUBTOTAL** | | | **4.85** |

## Penalizaciones
- Ninguna.

## Bonus
- +0.2: Puertos bien disenados con firmas semanticas por agregado.
- +0.2: Servicios de dominio con alta cohesion (19 servicios, uno por caso de uso).
- +0.1: Excelente trazabilidad en `BitacoraEntry` con `BitacoraEntryPort`.

Total bonus: +0.5

## Nota final
**5.0 / 5.0**

---

## Hallazgos

### Positivos
- **Dominio muy completo:** 9 entidades (Person, User, NaturalClient, Company, BankAccount, Loan, Transfer, BankProduct, BitacoraEntry).
- **9 enums bien definidos:** Role, AccountStatus, AccountType, Currency, LoanStatus (IN_STUDY, APPROVED, REJECTED, DISBURSED), TransferStatus (PENDING_APPROVAL, EXECUTED, REJECTED, EXPIRED), UserStatus, LoanType, ProductCategory.
- **7 puertos semanticos** por agregado: UserPort, BankAccountPort, LoanPort, TransferPort, CompanyPort, NaturalClientPort, BitacoraEntryPort.
- **19 servicios de dominio**, uno por caso de uso: `ApproveLoan`, `ApproveTransfer`, `CreateBankAccount`, `CreateLoan`, `CreateTransfer`, `DisburseLoan`, etc.
- Reglas de negocio implementadas en entidades: `Loan.approve()`, `Loan.reject()`, `Loan.disburse()` con validacion de estado y rol.
- `BitacoraEntry` con `operationType`, `operationDateTime`, `user`, `affectedProductId`, `detailData (Map)`.
- `BusinessException` y `NotFoundException` explicitas.
- `BigDecimal` para montos monetarios.

### Negativos
- El uso de `@Service` y `@Autowired` en servicios de dominio es un patron comun en proyectos Spring que no se penaliza en esta evaluacion, aunque en arquitectura hexagonal estricta se prefiere inyeccion sin anotaciones de framework.

## Recomendaciones
1. Considerar inyeccion por constructor en lugar de `@Autowired` para mayor testabilidad y claridad de dependencias.
2. Este es un dominio de alta calidad con cobertura completa de casos de uso y puertos bien definidos.
