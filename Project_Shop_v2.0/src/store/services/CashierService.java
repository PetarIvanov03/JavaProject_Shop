package store.services;


import store.models.Cashier;
import store.seed.SeedData;

import java.util.ArrayList;
import java.util.List;

public class CashierService {
    private List<Cashier> cashiers;

    public CashierService() {
        this.cashiers = SeedData.FillListWithCashiers();
    }

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    public List<Cashier> getAllCashiers() {
        return new ArrayList<>(cashiers);
    }

    public Cashier getCashierById(int id) {
        return cashiers.stream()
                .filter(cashier -> cashier.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateCashier(Cashier cashier) {
        Cashier existingCashier = getCashierById(cashier.getId());
        if (existingCashier != null) {
            existingCashier.setName(cashier.getName());
            existingCashier.setSalary(cashier.getSalary());
        }
    }

    public void deleteCashier(int id) {
        cashiers.removeIf(cashier -> cashier.getId() == id);
    }
}
