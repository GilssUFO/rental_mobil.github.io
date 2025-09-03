package rental.form;

import java.util.HashMap;
import java.util.Map;

public class Stok_mobil {
    private Map<String, Integer> stokMobil;

    public Stok_mobil() {
        stokMobil = new HashMap<>();

        // Inisialisasi stok awal
        stokMobil.put("Toyota Alphard", 2);
        stokMobil.put("Toyota Avanza", 5);
        stokMobil.put("BMW M4", 2);
        stokMobil.put("Mitsubishi Expander", 3);
        stokMobil.put("Nissan All New grand Livina", 3);
        stokMobil.put("Honda Brio", 5);
        stokMobil.put("Honda Jazz", 5);
        stokMobil.put("Honda BR-V", 5);
        stokMobil.put("Daihatsu Xenia", 5);
        stokMobil.put("Tesla Cybertruck", 1);
    }
    
    public Map<String, Integer> getAllStok() {
    return stokMobil;
}

    
    public int getStok(String namaMobil) {
        return stokMobil.getOrDefault(namaMobil, 0);
    }

    public boolean kurangiStok(String namaMobil) {
        int stok = getStok(namaMobil);
        if (stok > 0) {
            stokMobil.put(namaMobil, stok - 1);
            return true;
        }
        return false;
    }

    public void tampilkanStok() {
        System.out.println("Stok Mobil Saat Ini:");
        for (Map.Entry<String, Integer> entry : stokMobil.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

