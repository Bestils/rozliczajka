package com.sticngo.rozliczajka.infrastructure.web;


import java.security.Principal;
import java.util.Optional;
import javax.validation.Valid;

import com.sticngo.rozliczajka.domain.calculations.Calculation;
import com.sticngo.rozliczajka.domain.calculations.CalculationService;
import com.sticngo.rozliczajka.domain.members.Member;
import com.sticngo.rozliczajka.domain.members.MemberService;
import com.sticngo.rozliczajka.infrastructure.security.IdProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




import static com.sticngo.rozliczajka.infrastructure.web.WebUtils.redirectTo;

@Slf4j
@Controller
@RequestMapping("/calculations")
@RequiredArgsConstructor
public class CalculationController {
    public static final String CALLCULATION_ALL = "/callculation/all";
    public static final String CALLCULATION_DETAILS = "/callculation/details";
    public static final String CALLCULATION_EDIT_FORM = "/callculation/edit_form";

    private final CalculationService calculationService;
    private final MemberService memberService;
    private final IdProvider idProvider;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("calculation", calculationService.findcalculationsBelongToUser(idProvider.getCurrentUserId()));
        return CALLCULATION_ALL;
    }

    @GetMapping("{id}")
    public String getcalculationDetails(@PathVariable Long id, Model model) {
        model.addAttribute("calculation", calculationService.findById(id));
        return CALLCULATION_DETAILS;
    }

    @GetMapping("edit")
    public String addEditForm(Model model) {
        model.addAttribute("calculation", new Calculation());
        return CALLCULATION_EDIT_FORM;
    }

    @PostMapping
    public String processCreateForm(@ModelAttribute @Valid Calculation calculation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return redirectTo("/calculations");
        }
        calculationService.save(calculation, idProvider.getCurrentUserId());
        return redirectTo("/calculations");
    }

    @PostMapping("update")
    public String processUpdateForm(@ModelAttribute @Valid Calculation calculation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CALLCULATION_EDIT_FORM;
        }
        calculationService.updateCalculation(calculation, idProvider.getCurrentUserId());
        return redirectTo("/calculations");
    }

    @PostMapping("delete")
    public String deletecalculation(@RequestBody Long id) {
        calculationService.deleteById(id, idProvider.getCurrentUserId());
        return redirectTo(CALLCULATION_ALL);
    }
}
