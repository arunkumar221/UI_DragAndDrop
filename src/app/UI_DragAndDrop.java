package app;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UI_DragAndDrop {

       static String URL= "http://quickfuseapps.com";
       String btnCreate= "//a[@id='link-create']";
       String btnGetStarted = "//button[contains(text(),'get started!')]";
       String btnNewPage = "//span[@class='ui-icon ui-icon-plusthick']";
       String txtPageName = "//input[@class='indented submitonenter']";
       String btnCreatePage = "//label[text()='Enter a new name for this page:']//following::button[text()='Create']";
       String btnDefaultPage = "//a[text()='Untitled Page 1']//following::a[@title='Actions']";
       String btnDeletePAge = "//li[text()='Delete Page']";
       String btnConfirmDelete = "//span[text()='Delete page \"Untitled Page 1\"']//following::button[text()='Delete']";
       String btnStartDraggable = "//div[@class='syn-module start-module start-module-loading syn-module-green syn-module-unified ui-draggable']//following::div[@class='syn-node ui-draggable syn-node-active']";
       String btnMessage = "//a[contains(text(),'Messaging')]";
       String btnSms = "//li[text()='Send an SMS']";
       String btnSmsDroppable = "//div[@class='syn-module syn-module-green ui-draggable']//following::div[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']";
       String txtMobileNumber = "//div[@class='syn-module syn-module-green ui-draggable']//following::textarea[@name='phone_constant']";
       String txtSmsMessage = "//div[@class='syn-module syn-module-green ui-draggable']//following::textarea[@name='message_phrase[]']";
       String btnEmail = "//li[text()='Send an Email']";
       String btnSmsDraggable = "//div[text()='Send an SMS']//following::div[@class='syn-node syn-node-attached-e ui-draggable syn-node-active']";
       String btnEmailDroppable = "//div[text()='Send an Email']//following::div[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']";
       String txtHost = "//input[@name='smtp_url']";
       String txtPost = "//input[@name='port']";
       String txtName = "//input[@name='username']";
       String txtPassword = "//input[@name='password']";
       String txtEmailFrom = "//textArea[@name='from_constant']";
       String txtEmailTO = "//textArea[@name='to_constant']";
       String txtSubject = "//textArea[@name='subject_constant']";
       String txtEmailMessage = "//textArea[@name='subject_constant']//following::textArea[@name='message_phrase[]']";
       String btnBasic = "//a[contains(text(),'Basic')]";
       String btnExitApp = "//li[text()='Hang Up or Exit']";
       String btnSmsDraggable1 = "//div[text()='Send an SMS']//following::div[@class='syn-node syn-node-attached-w ui-draggable syn-node-active']";
       String btnExitDroppable1 = "//div[text()='Exit App']//following::div[@class='syn-receptor ui-droppable syn-receptor-north ui-draggable syn-receptor-draggable']";
       String btnEmailDraggable1 = "//li[text()='Send an Email']//following::div[@class='syn-node syn-node-attached-w ui-draggable syn-node-active']";
       String btnExitDroppable2 = "//div[text()='Exit App']//following::div[text()='Exit App']//following::div[text()='Exit App']";
       String btnEmailDraggable2 = "//li[text()='Send an Email']//following::div[@class='syn-node syn-node-attached-e ui-draggable syn-node-active']";
       String btnExitDroppable3 = "//div[text()='Exit App']//following::div[text()='Exit App']";
       
       
       static Framework obj =new Framework();
       static UI_DragAndDrop abc =new UI_DragAndDrop();
       
       
	public static void main(String[] args) throws InterruptedException, IOException {
		
		obj.setUp();
		obj.launchApplication(URL);	
		
		abc.createNewPAge();		
		abc.sendSMSDrag();		
		abc.sendEmailDrag();		
		abc.exitAppForSmsAndEmail();
		
		obj.close();
		 
	}
	
	public void createNewPAge() throws InterruptedException, IOException
	{
		obj.clickObject(btnCreate);
		obj.clickObject(btnGetStarted);
		
		//Create new page
		obj.clickObject(btnNewPage);
		obj.EnterText(txtPageName,"Arun");
		obj.clickObject(btnCreatePage);
		  
		//Deleting Default page
		obj.clickObject(btnDefaultPage);
		obj.clickObject(btnDeletePAge);
		obj.clickObject(btnConfirmDelete);
	}
	
	public void sendSMSDrag() throws InterruptedException, IOException
	{
		//Drag and drops Send an SMS
		obj.clickObject(btnMessage);
		Thread.sleep(1000);
		obj.dragAndDropByIndex(btnSms,655,100);
		
		Thread.sleep(1500);
		obj.dragAndDrop(btnStartDraggable,btnSmsDroppable);		//Connect SMS input port to Start output port
		
		//Enter SMS Details
		obj.EnterText(txtMobileNumber, "9876543210");
		obj.EnterText(txtSmsMessage, "Sample message via SMS - Hello World, 1234567890");
	}
	
	public void sendEmailDrag() throws InterruptedException, IOException
	{
		//Drag and drops Send an Email
		Thread.sleep(1000);
		obj.dragAndDropByIndex(btnEmail, 1030, 100);
		Thread.sleep(1500);
		obj.dragAndDrop(btnSmsDraggable, btnEmailDroppable);	//Connect SMS not sent port to Send an Email input port
		
		//Enter email Details
		obj.EnterText(txtHost, "Arun@gmail.com");
		obj.EnterText(txtPost, "5050");
		obj.EnterText(txtName, "Arun");
		obj.EnterText(txtPassword, "Arun123");
		obj.EnterText(txtEmailFrom, "ABC@gmail.com");
		obj.EnterText(txtEmailTO, "xyz@gmail.com");
		obj.EnterText(txtSubject, "Java");
		obj.EnterText(txtEmailMessage, "Sample message via Email - Hello World, 1234567890");
	}
	
	public void exitAppForSmsAndEmail() throws InterruptedException, IOException
	{
        obj.clickObject(btnBasic);
		Thread.sleep(1000);
		
		obj.dragAndDropByIndex(btnExitApp, 600, 500);	//Drag and Drop Exit app for SMS sent output port
		Thread.sleep(1500);
		obj.dragAndDrop(btnSmsDraggable1, btnExitDroppable1); 	//Join SMS sent output port and Exit app
	
		obj.dragAndDropByIndex(btnExitApp, 1280, 500);   //Drag and Drop Exit app for email output ports
		obj.dragAndDropByIndex(btnExitApp, 950, 500);
		Thread.sleep(1500);
		obj.dragAndDrop(btnEmailDraggable1, btnExitDroppable2);		//Join Email sent output port and Exit app
		Thread.sleep(1500);
		obj.dragAndDrop(btnEmailDraggable2, btnExitDroppable3);		//Join Email not sent output port and Exit app
		
	}

}
