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
    public String emailDefault (String nameOfEvent, DataAuth user){
        String greeting = formatService.isMorning() ? "Bom dia" : "Boa tarde";
        return String.format(
                "Prezado %s,\n" +
                        "É com grande satisfação que lhe damos as boas-vindas ao %s. Agradecemos por fazer parte da nossa história e por estar presente conosco.\n" +
                        "Gostaríamos de fornecer a você uma imagem para autenticar o seu ticket na hora do evento. Certifique-se de tê-la em mãos para facilitar o processo de entrada.\n" +
                        "Aproveite ao máximo o evento e desfrute de todas as experiências que preparamos para você.\n" +
                        "Atenciosamente,\n" +
                        "Esy Bosch",
                user.userName(), greeting, nameOfEvent
        );
    };
    public String titleEmail(String nameOfEvent){
        return "Parabéns por particpar do evento (" + nameOfEvent + ")! Acesse o ticket!";
    }
}
