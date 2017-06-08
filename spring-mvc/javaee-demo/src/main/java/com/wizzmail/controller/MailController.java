package com.wizzmail.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import com.wizzmail.domain.entity.User;
import com.wizzmail.domain.model.bindingModels.CreateEmailModel;
import com.wizzmail.domain.model.viewModels.EmailDetailsViewModel;
import com.wizzmail.domain.model.viewModels.EmailOverviewViewModel;
import com.wizzmail.service.EmailService;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Stateless
public class MailController {

    @Inject
    private EmailService emailService;

    @GetMapping("/mail/inbox")
    public String getInboxMailPage(Model model, HttpSession session, @RequestParam(value = "search") String search){
        User user = (User) session.getAttribute("currentUser");
        List<EmailOverviewViewModel> emails = this.emailService.getInboxEmails(user);
        if(search != null){
            emails = emails.stream().filter(e-> e.getSubject().contains(search) || e.getMessage().contains(search)).collect(Collectors.toList());
        }

        model.addAttribute("emails", emails);
        long newMessages = emails.stream().filter(e -> !e.isRead()).count();
        model.addAttribute("newMessages", newMessages);
        long drafts = this.emailService.getDraftEmails(user).stream().filter(e -> e.isDraft()).count();
        model.addAttribute("drafts", drafts);
        return "/templates/mail-inbox";
    }

    @GetMapping("/mail/sent")
    public String getSentMailPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        List<EmailOverviewViewModel> newEmails = this.emailService.getInboxEmails(user);
        long newMessages = newEmails.stream().filter(e -> !e.isRead()).count();
        model.addAttribute("newMessages", newMessages);
        List<EmailOverviewViewModel> sentEmails = this.emailService.getSentEmails(user);
        model.addAttribute("emails", sentEmails);
        long drafts = this.emailService.getDraftEmails(user).stream().filter(e -> e.isDraft()).count();
        model.addAttribute("drafts", drafts);
        return "/templates/mail-sent";
    }

    @GetMapping("/mail/drafts")
    public String getDraftsMailPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        List<EmailOverviewViewModel> newEmails = this.emailService.getInboxEmails(user);
        long newMessages = newEmails.stream().filter(e -> !e.isRead()).count();
        model.addAttribute("newMessages", newMessages);
        List<EmailOverviewViewModel> draftEmails = this.emailService.getDraftEmails(user);
        model.addAttribute("emails", draftEmails);
        long drafts = draftEmails.stream().filter(e -> e.isDraft()).count();
        model.addAttribute("drafts", drafts);
        return "/templates/mail-drafts";
    }

    @GetMapping("/mail/trash")
    public String getTrashMailPage(Model model, HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        List<EmailOverviewViewModel> newEmails = this.emailService.getInboxEmails(user);
        long newMessages = newEmails.stream().filter(e -> !e.isRead()).count();
        model.addAttribute("newMessages", newMessages);
        long drafts = this.emailService.getDraftEmails(user).stream().filter(e -> e.isDraft()).count();
        model.addAttribute("drafts", drafts);
        List<EmailOverviewViewModel> trashMails = this.emailService.getTrashEmails(user);
        model.addAttribute("emails", trashMails);
        return "/templates/mail-trash";
    }

    @PostMapping("/mail/new")
    public String sendMail(@ModelAttribute CreateEmailModel createEmailModel, Model model, HttpSession session){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CreateEmailModel>> constraintViolations = validator.validate(createEmailModel);
        if (constraintViolations.size() > 0) {
            model.addAttribute("constraintViolations", constraintViolations);
            return "/templates/new-email";
        }

        User user = (User) session.getAttribute("currentUser");
        try {
            this.emailService.sendEmail(createEmailModel, user);
        } catch (EJBException e){
            createEmailModel.setRecipientsUsername(null);
            constraintViolations = validator.validate(createEmailModel);
            model.addAttribute("constraintViolations", constraintViolations);
            return "/templates/new-email";
        }

        return "redirect:/mail/inbox";
    }

    @PostMapping("/mail/draft")
    public String draftMail(@ModelAttribute CreateEmailModel createEmailModel, Model model, HttpSession session){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CreateEmailModel>> constraintViolations = validator.validate(createEmailModel);
        if (constraintViolations.size() > 0) {
            model.addAttribute("constraintViolations", constraintViolations);
            return "/templates/new-email";
        }

        User user = (User) session.getAttribute("currentUser");
        try {
            this.emailService.draftEmail(createEmailModel, user);
        } catch (EJBException e){
            createEmailModel.setRecipientsUsername(null);
            constraintViolations = validator.validate(createEmailModel);
            model.addAttribute("constraintViolations", constraintViolations);
            return "/templates/new-email";
        }

        return "redirect:/mail/inbox";
    }

    @GetMapping("/mail/new")
    public String getMailNewPage(){
        return "/templates/new-email";
    }

    @GetMapping("/mail/received/{id}")
    public String getMailReceivedPage(@PathVariable(value = "id") long id, Model model){
        EmailDetailsViewModel email = this.emailService.readReceivedEmail(id);
        model.addAttribute("email", email);
        return "/templates/email-details";
    }

    @GetMapping("/mail/sent/{id}")
    public String getMailSentPage(@PathVariable(value = "id") long id, Model model){
        EmailDetailsViewModel email = this.emailService.readSentEmail(id);
        model.addAttribute("email", email);
        return "/templates/email-details";
    }

    @GetMapping("/mail/send/{id}")
    public String sendMail(@PathVariable(value = "id") long id){
        this.emailService.sendEmail(id);
        return "redirect:/mail/inbox";
    }

    @GetMapping("/mail/trash/{id}")
    public String trashMail(@PathVariable(value = "id") long id){
        this.emailService.trashEmail(id);
        return "redirect:/mail/inbox";
    }

    @GetMapping("/mail/restore/{id}")
    public String restoreMail(@PathVariable(value = "id") long id){
        this.emailService.restoreEmail(id);
        return "redirect:/mail/inbox";
    }

    @GetMapping("/mail/delete/{id}")
    public String deletePermanentlyMail(@PathVariable(value = "id") long id){
        this.emailService.deleteEmailPermanently(id);
        return "redirect:/mail/inbox";
    }
}
