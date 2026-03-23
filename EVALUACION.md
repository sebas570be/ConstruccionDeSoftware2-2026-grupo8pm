# 📋 EVALUACIÓN - Sistema Bancario
**Proyecto:** ConstruccionDeSoftware2-2026-grupo8pm
**Fecha de evaluación:** 23/03/2026
**Nota final: 4.2 / 5.0**

---

## 📊 Tabla de Puntajes

| Criterio | Peso | Puntaje (1-5) | Contribución |
|----------|------|----------------|--------------|
| 1. Modelado de dominio | 25% | 4 | 1.00 |
| 2. Relaciones entre entidades | 15% | 4 | 0.60 |
| 3. Uso de enums | 15% | 4 | 0.60 |
| 4. Manejo de estados | 5% | 5 | 0.25 |
| 5. Tipos de datos | 5% | 3 | 0.15 |
| 6. Separación Usuario vs Cliente | 10% | 3 | 0.30 |
| 7. Bitácora | 5% | 5 | 0.25 |
| 8. Reglas básicas de negocio | 5% | 2 | 0.10 |
| 9. Estructura del proyecto | 10% | 4 | 0.40 |
| 10. Repositorio | 10% | 1 | 0.10 |
| **TOTAL BASE** | 100% | | **3.75** |

### Bonus Aplicados

| Bonus | Puntaje |
|-------|---------|
| Código limpio con Lombok | +0.20 |
| Nombres claros y consistentes en inglés | +0.10 |
| Herencia parcial (NaturalClient/User extends Person) | +0.10 |
| **Total bonus** | **+0.40** |

### Penalizaciones Aplicadas
Ninguna.

**NOTA FINAL: 4.15 → 4.2 / 5.0**

---

## 🔍 Análisis Detallado por Criterio

### 1. Modelado de dominio → 4/5
Entidades implementadas:
- ✅ `Person` (abstract) — base de cliente y usuario
- ✅ `User` (extends Person) — usuario del sistema con credenciales
- ✅ `NaturalClient` (extends Person) — cliente persona natural
- ✅ `Company` — cliente empresa con representante legal
- ✅ `BankAccount` — cuenta bancaria
- ✅ `Loan` — préstamo con cuenta destino y usuario aprobador
- ✅ `Transfer` — transferencia con usuarios creador/aprobador
- ✅ `BankProduct` — producto bancario con categoría
- ✅ `BitacoraEntry` — bitácora flexible

**Observaciones:**
- ⚠️ `Loan.loanType` es `String` en lugar de un enum `LoanType`
- ⚠️ `NaturalClient` y `User` tienen jerarquías separadas desde `Person`, lo que dificulta relacionar un cliente con su acceso

### 2. Relaciones entre entidades → 4/5
- ✅ `User extends Person`, `NaturalClient extends Person` — herencia sobre base común
- ✅ `Company.legalRepresentative` = `NaturalClient` — relación correcta
- ✅ `User.company` = `Company` — relación usuario-empresa
- ✅ `Transfer.originAccount` / `destinationAccount` = `BankAccount` — referencias directas
- ✅ `Transfer.creatorUser` / `approverUser` = `User` — referencias al usuario
- ✅ `Loan.disbursementAccount` = `BankAccount`, `analystApprover` = `User`
- ✅ `BitacoraEntry.user` = `User`

### 3. Uso de enums → 4/5
Enums implementados:
- ✅ `AccountStatus` — ACTIVE, BLOCKED, CANCELLED
- ✅ `AccountType` — SAVINGS, CURRENT, PERSONAL, BUSINESS
- ✅ `Currency` — COP, USD, EUR
- ✅ `LoanStatus` — IN_STUDY, APPROVED, REJECTED, DISBURSED
- ✅ `ProductCategory` — ACCOUNTS, LOANS, SERVICES
- ✅ `Role` — 5 roles (falta CLIENT_PERSON, CLIENT_COMPANY)
- ✅ `TransferStatus` — PENDING_APPROVAL, EXECUTED, REJECTED, EXPIRED
- ✅ `UserStatus` — ACTIVE, INACTIVE, BLOCKED

**Faltantes:**
- ❌ `Loan.loanType` es `String` — debería usar un enum `LoanType`
- ⚠️ `Role` solo tiene empleados y analista, no incluye los roles de cliente

### 4. Manejo de estados → 5/5
Los estados de cuenta, préstamo, transferencia y usuario están correctamente modelados con enums. El flujo `LoanStatus` sigue la lógica del dominio.

### 5. Tipos de datos → 3/5
- ❌ `BankAccount.currentBalance` = `double` — debería ser `BigDecimal`
- ❌ `Loan.requestedAmount` / `approvedAmount` / `interestRate` = `double` — debería ser `BigDecimal`
- ❌ `Transfer.amount` = `double` — debería ser `BigDecimal`
- ✅ `java.sql.Timestamp` para fechas (aceptable aunque `LocalDateTime` es preferible)
- ✅ `java.sql.Date` para fechas de préstamo

Usar `double` para valores monetarios es una mala práctica reconocida en sistemas financieros por los problemas de precisión.

### 6. Separación Usuario vs Cliente → 3/5
- ✅ `User` con credenciales (username, password) — usuario del sistema
- ✅ `NaturalClient` sin credenciales — concepto de cliente
- ⚠️ No hay un mecanismo claro de relacionar `NaturalClient` (cliente) con `User` (acceso al sistema)
- ⚠️ `Company` es completamente independiente (no extends Person/User)
- ✅ La intención de separación es clara pero incompleta

### 7. Bitácora → 5/5
- ✅ `BitacoraEntry` con `Map<String, Object> detailData` — estructura flexible
- ✅ Tiene `operationType`, `operationDateTime`, `user` (referencia directa), `affectedProductId`
- ✅ Uso de Lombok para código limpio
- ✅ Esta es exactamente la estructura esperada para la bitácora

### 8. Reglas básicas de negocio → 2/5
- ✅ `BusinessException` para manejo de errores del dominio
- ❌ No hay validaciones en los constructores de las entidades
- ❌ No hay métodos de dominio que apliquen reglas (aprobar, rechazar, desembolsar)
- ⚠️ Las entidades son puramente estructurales (anémicas)

### 9. Estructura del proyecto → 4/5
Organización:
```
app.bank.domain.exceptions/    → Excepciones de dominio
app.bank.domain.models.enums/  → Enums
app.bank.domain.models/        → Entidades
```
Razonablemente organizado, aunque el paquete base `app.bank` podría ser más descriptivo.

### 10. Repositorio → 1/5
- ❌ **No se encontró ningún archivo README.md** en el repositorio
- ❌ No hay información sobre integrantes, tecnología ni cómo ejecutar

---

## 🌟 Puntos Destacables

- Uso de Lombok que reduce código boilerplate
- `BitacoraEntry` bien implementada con estructura flexible
- Referencias directas de objetos en Transfer y Loan
- `BusinessException` para errores del dominio
- Código completamente en inglés

## 💡 Áreas de Mejora

1. Cambiar `double` a `BigDecimal` en todos los campos monetarios
2. Agregar enum `LoanType` y usarlo en la clase `Loan`
3. Expandir el enum `Role` para incluir roles de cliente
4. Crear un mecanismo de relación entre `NaturalClient` y `User`
5. **Agregar README** con integrantes, materia, tecnología e instrucciones de ejecución
6. Agregar validaciones en constructores (e.g., `amount > 0`)
