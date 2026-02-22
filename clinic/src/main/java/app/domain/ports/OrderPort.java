package app.domain.ports;

import app.domain.models.Order;
import java.util.List;

public interface OrderPort {

    // Guarda una orden en la base de datos
    void save(Order order);

    // Verifica si ya existe una orden con ese número
    boolean existsByNumber(String orderNumber);

    // Busca todas las órdenes de un paciente por su cédula
    List<Order> findByPatientId(String patientId);

    // Busca todas las órdenes generadas por un médico
    List<Order> findByDoctorId(String doctorId);
}