package com.tugas.deploy.controller;

import com.tugas.deploy.model.User; // Mengimpor model User yang baru kita buat
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // Ini adalah memori sementara untuk menyimpan data mahasiswa
    private List<User> daftarMahasiswa = new ArrayList<>();

    // --- LOGIKA LOGIN (Sama seperti sebelumnya) ---
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        String nimKamu = "20230140147"; // Sesuaikan NIM-mu
        if ("admin".equals(username) && nimKamu.equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau Password salah!");
            return "login";
        }
    }

    // --- LOGIKA HALAMAN HOME ---
    @GetMapping("/home")
    public String showHomePage(Model model) {
        // Kita mengirim data 'daftarMahasiswa' ke file home.html
        model.addAttribute("users", daftarMahasiswa);
        return "home";
    }

    // --- LOGIKA HALAMAN FORM ---
    // 1. Menampilkan halaman form kosong
    @GetMapping("/form")
    public String showFormPage() {
        return "form";
    }

    // 2. Menerima data saat tombol "Simpan" di form ditekan
    @PostMapping("/form")
    public String processForm(@RequestParam String nama,
                              @RequestParam String nim,
                              @RequestParam String jenisKelamin) {
        // Buat objek mahasiswa baru dan masukkan ke dalam List memori kita
        User mahasiswaBaru = new User(nama, nim, jenisKelamin);
        daftarMahasiswa.add(mahasiswaBaru);

        // Setelah disimpan, langsung kembalikan user ke halaman home
        return "redirect:/home";
    }
}