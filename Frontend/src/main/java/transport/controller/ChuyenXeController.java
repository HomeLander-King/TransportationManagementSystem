package transport.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import transport.model.ChuyenXe;
import transport.model.TaiXe;
import transport.model.TuyenXe;
import transport.model.XeKhach;
import transport.service.ChuyenXeService;
import transport.service.TaiXeService;
import transport.service.TuyenXeService;
import transport.service.XeKhachService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/chuyenXe")
public class ChuyenXeController {
    private ChuyenXeService chuyenXeService;
    private TaiXeService taiXeService;
    private TuyenXeService tuyenXeService;
    private XeKhachService xeKhachService;

    @Autowired
    public ChuyenXeController(ChuyenXeService chuyenXeService, TuyenXeService tuyenXeService, XeKhachService xeKhachService, TaiXeService taiXeService){
        this.xeKhachService = xeKhachService;
        this.chuyenXeService = chuyenXeService;
        this.tuyenXeService = tuyenXeService;
        this.taiXeService = taiXeService;
    }

    @GetMapping("/{id}")
    public String getChuyenXeById(@PathVariable("id") Long id, Model model){
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("chuyenXe", chuyenXe);
        return "ChuyenXe/infoChuyenXe";
    }

    @GetMapping()
    public String getAllChuyenXe(Model model){
        List<ChuyenXe> listChuyenXe = chuyenXeService.getAllChuyenXe();
        model.addAttribute("listChuyenXe", listChuyenXe);
        return "ChuyenXe/listChuyenXe";
    }

    @GetMapping("/create")
    public String addChuyenXe(Model model){
        List<TaiXe> listTaiXe = taiXeService.getAllTaiXe();
        List<XeKhach> listXeKhach = xeKhachService.getAllXeKhach();
        List<TuyenXe> listTuyenXe = tuyenXeService.getAllTuyenXe();
        model.addAttribute("listTaiXe", listTaiXe);
        model.addAttribute("listXeKhach", listXeKhach);
        model.addAttribute("listTuyenXe", listTuyenXe);
        return "ChuyenXe/addChuyenXe";
    }

    @PostMapping("/store")
    public String storeChuyenXe(ChuyenXe chuyenXe){
        chuyenXeService.creatChuyenXe(chuyenXe);
        return "redirect:/chuyenXe";
    }

    @GetMapping("/edit/{id}")
    public String editChuyenXe(@PathVariable("id") Long id, Model model){
        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(id);
        model.addAttribute("chuyenXe", chuyenXe);
        List<TaiXe> listTaiXe = taiXeService.getAllTaiXe();
        List<XeKhach> listXeKhach = xeKhachService.getAllXeKhach();
        List<TuyenXe> listTuyenXe = tuyenXeService.getAllTuyenXe();
        model.addAttribute("listTaiXe", listTaiXe);
        model.addAttribute("listXeKhach", listXeKhach);
        model.addAttribute("listTuyenXe", listTuyenXe);
        return "ChuyenXe/editChuyenXe";
    }

    @PostMapping("/update/{id}")
    public String updateChuyenXe(@PathVariable("id") Long id, ChuyenXe chuyenXe){
        chuyenXeService.updateChuyenXe(chuyenXe, id);
        return "redirect:/chuyenXe/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteChuyenXe(@PathVariable("id") Long id){
        chuyenXeService.deleteChuyenXe(id);
        return "redirect:/chuyenXe";
    }
}
