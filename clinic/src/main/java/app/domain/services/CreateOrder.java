package app.domain.services;

import app.domain.models.Order;
import app.domain.models.OrderItem;
import app.domain.models.ItemType;
import app.domain.ports.OrderPort;
import app.domain.Exceptions.BusinessException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CreateOrder {

    private final OrderPort orderPort;

    public CreateOrder(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public void createOrder(Order order) throws BusinessException {
        validateOrderRules(order);
        orderPort.save(order);
    }

    private void validateOrderRules(Order order) throws BusinessException {
        // Número de orden: único y máximo 6 dígitos
        if (String.valueOf(order.getId()).length() > 6)
            throw new BusinessException("El número de orden no puede superar 6 dígitos");
        if (orderPort.existsByNumber(String.valueOf(order.getId())))
            throw new BusinessException("Ya existe una orden con ese número");

        // Cédula del médico: solo dígitos, máximo 10
        if (!order.getDoctorId().matches("\\d{1,10}"))
            throw new BusinessException("La cédula del médico debe tener máximo 10 dígitos");

        OrderItem[] items = order.getOrderItems();

        // La orden no puede estar vacía
        if (items == null || items.length == 0)
            throw new BusinessException("La orden debe tener al menos un ítem");

        // Un solo recorrido: valida número de ítem, duplicados y especialista
        HashSet<Integer> itemNumbers = new HashSet<>();
        for (OrderItem item : items) {
            if (item.getItemNumber() < 1)
                throw new BusinessException("El número de ítem debe comenzar desde 1");
            if (!itemNumbers.add(item.getItemNumber()))
                throw new BusinessException("No puede haber ítems con el mismo número en la misma orden");
            if (item.isRequiresSpecialist() &&
               (item.getSpecialistTypeId() == null || item.getSpecialistTypeId().isBlank()))
                throw new BusinessException("Debe indicar el tipo de especialista");
        }

        // Ayuda diagnóstica no puede coexistir con medicamentos ni procedimientos
        validateDiagnosticExclusivityHelp(Arrays.asList(items));
    }

    private void validateDiagnosticExclusivityHelp(List<OrderItem> items) throws BusinessException {
        boolean hasDiagnosticHelp = false;
        boolean hasMedicationOrProcedure = false;
        for (OrderItem item : items) {
            if (item.getItemType() == ItemType.MEDICALSUPPORT)
                hasDiagnosticHelp = true;
            if (item.getItemType() == ItemType.MEDICINE || item.getItemType() == ItemType.PROCEDURE)
                hasMedicationOrProcedure = true;
        }
        if (hasDiagnosticHelp && hasMedicationOrProcedure)
            throw new BusinessException("No puede haber ayuda diagnóstica junto con medicamentos o procedimientos");
    }
}
