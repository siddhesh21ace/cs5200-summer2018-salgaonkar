package edu.northeastern.cs5200.person.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/api/Admin")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping("/api/admin")
    public List<Admin> findAllAdmins() {
        return (List<Admin>) adminService.findAllAdmins();
    }

    @GetMapping("/api/admin/{adminId}")
    public Admin findAdminById(@PathVariable("adminId") int adminId) throws Exception {
        return adminService.findAdminById(adminId);
    }

    @PutMapping("/api/admin/{adminId}")
    public Admin updateAdmin(@PathVariable("adminId") int adminId,
                             @RequestBody Admin newAdmin) throws Exception {
        return adminService.updateAdmin(adminId, newAdmin);
    }

    @DeleteMapping("/api/admin/{adminId}")
    public void deleteAdmin(@PathVariable("adminId") int id) {
        adminService.deleteAdmin(id);
    }
}
