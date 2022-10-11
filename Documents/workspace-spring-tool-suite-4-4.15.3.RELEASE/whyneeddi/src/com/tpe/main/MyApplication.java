package com.tpe.main;

import com.tpe.domain.Message;
import com.tpe.repository.DBRepository;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SMSService;
import com.tpe.service.WhatsAppService;

public class MyApplication {

	/*
	  	1- Normalde repo objelerini servis classlarındaki cons aracılığı ile oluşturuyoruz. (Genelde parametresiz)
		2- Bu sefer parametreli oluşturdk ki başka yerden veri atayabilelim.
		3- Classlarda parametreli cons oluşturunca ve parametresiz oluşturmayınca objeleri mecburen parametreli oluşturmak zorunda kaldık.
		4- Application classta obje oluşturunca bizden parametre istedi.
		5- Birden fazla parametre tipi olduğu için biz bu parametreyi if clause ları ile dinamik hale getirdik. Sonrasında yeni bir repo gerekirse bunu if clause a eklemkek yeterli hale geldi.
		6- Son olarak yukarıda oluşturulun bir variable ile parametreleri depoladık ki bunları if clause larda kullanalım.
	 */
	public static void main(String[] args) {
		
		String serviceName="mailService";
		String repositoryName="dbRepository";
		if(args.length>0) {
			serviceName=args[0];
			repositoryName=args[1];
		}
		
		Repository repository=null;
		if(repositoryName.equalsIgnoreCase("fileRepository")) {
			repository=new FileRepository();
		}else {
			repository=new DBRepository();
		}
		
		Message message=new Message();
		message.setMessage("Your order  was sent");
	
		MessageService messageService=null;

		if(serviceName.equalsIgnoreCase("whatsAppService")) {
			messageService=new WhatsAppService(repository);
			messageService.sendMessage(message);
		}else if(serviceName.equalsIgnoreCase("smsService")) {
			messageService=new SMSService(repository);
			messageService.sendMessage(message);
		}else {
			messageService=new MailService(repository);
			messageService.sendMessage(message);
		}
		

//		MessageService whatsAppService=new WhatsAppService();
//		whatsAppService.sendMessage();
//		
//		MessageService smsService=new SMSService();
//		smsService.sendMessage();
	}

}
