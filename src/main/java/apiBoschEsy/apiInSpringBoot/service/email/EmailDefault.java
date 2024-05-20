package apiBoschEsy.apiInSpringBoot.service.email;

import apiBoschEsy.apiInSpringBoot.dto.auth.DataAuth;
import apiBoschEsy.apiInSpringBoot.service.utils.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailDefault {

    @Autowired
    private FormatService formatService;

    // Creating a method for send e-mail with image in your body
    public String emailDefault (String nameOfEvent, String cid, DataAuth user){
        String greeting = formatService.isMorning() ? "Bom dia" : "Boa tarde";
        return String.format(
                "<p>Prezado %s,</p>" +
                        "<p>%s! É com grande satisfação que lhe damos as boas-vindas ao %s. " +
                        "Agradecemos por fazer parte da nossa história e por estar presente conosco.</p>" +
                        "<p>Gostaríamos de fornecer a você uma imagem para autenticar o seu ticket na hora do evento. " +
                        "Certifique-se de tê-la em mãos para facilitar o processo de entrada.</p>" +
                        "<img src='cid:%s' alt='QR Code'>" +
                        "<p>Aproveite ao máximo o evento e desfrute de todas as experiências que preparamos para você.</p>" +
                        "<p>Atenciosamente, Esy Bosch</p>",
                user.userName(), greeting, nameOfEvent, cid
        );
    };
}
