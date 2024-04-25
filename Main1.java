//package projet;
//import java.util.*;
//public class Main {
//	public static void main(String[] args) {     
//new MenuGUI();
//		boolean c = true;
//	    Scanner scanner1 = new Scanner(System.in);
//	    int  UserInputInt1;    
//		
//	   while(c) {
//	    System.out.println("╔════════════════════════════════════════╗");
//	 	System.out.println("║        Welcome  To Our Service         ║");
//	 	System.out.println("╚════════════════════════════════════════╝");
//	 	System.out.println("╔════════════════════╗");
//    	System.out.println("║(1) Classic Game    ║");
//        System.out.println("║(2) Private Game    ║");
//        System.out.println("║(3) Chompionship    ║");
//        System.out.println("║(4) Classify Items  ║"); 
//        System.out.println("║(0) Exit            ║");
//        System.out.println("╚════════════════════╝");
//	 	System.out.println("Choice =>  ");
//	 	UserInputInt1 = scanner1.nextInt();	   
//		switch (UserInputInt1) {
//		case 1 :{
//			  boolean a = true;
//			  while(a) {
//			int  UserInputInt2;
//			//System.out.println("");
//		    System.out.println("╔═══════════════╗"); 
//			System.out.println("║(1) 2 Players  ║");
//	        System.out.println("║(2) 4 Players  ║"); 
//	        System.out.println("║(0) Back       ║"); 	
//	        System.out.println("╚═══════════════╝");			
//	        System.out.println("Choice =>  ");
//	        UserInputInt2 = scanner1.nextInt();	   
//	    	//System.out.println("");
//		    switch (UserInputInt2) {
//	        case 1 :{
//	        final int NUM_PLAYERS = 2;
//	    	final int NUM_ROUNDS = 3;
//	    	GameCards Cards = new GameCards();			
//	    	String NumberOfPlayerString;
//	        int NumberOfPlayerInt;   
//	        String NumberOfObjectString;
//	        int NumberOfObjectInt;
//	        Table table= new Table(); 
//	        boolean b = true;
//	     	Card User1Card = new Card();
//	     	Card User2Card = new Card();
//	     	Card MiddleCard = new Card();
//	    	User1Card = table.ChooseNewCard(Cards.cards);
//	        User2Card = table.ChooseNewCard(Cards.cards);
//	        int User1score = 0;
//	        int User2score = 0;
//	        MiddleCard = table.ChooseNewCard(Cards.cards);
//	        while (b) {
//	    	String UserInputObjects;
//	    	System.out.println("");
//	    	System.out.println("╔════════════════════════════════════════╗");
//	     	System.out.println("║        Welcome  To Our Service         ║");
//	     	System.out.println("╚════════════════════════════════════════╝");
//	     	System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");	
//	     	System.out.println("║ Middle card :           ");
//	        System.out.println(" ════════════════════════════════════════════════════════════════════════════════════ ");
//		 	table.displayCard(MiddleCard);
//		    System.out.println(" ════════════════════════════════════════════════════════════════════════════════════ ");
//		 	System.out.println("║ User 1 card :           "+User1score);
//		    System.out.println(" ════════════════════════════════════════════════════════════════════════════════════ ");
//		    table.displayCard(User1Card);
//		    System.out.println(" ════════════════════════════════════════════════════════════════════════════════════ ");
//		    System.out.println("║ User 2 card :           "+User2score);
//		    System.out.println(" ════════════════════════════════════════════════════════════════════════════════════ ");
//		    table.displayCard(User2Card);
//		    System.out.println(" ════════════════════════════════════════════════════════════════════════════════════ ");
//		    System.out.println("║(0/) Back"); 	
//		    System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝ ");
//	        System.out.println("Choice =>  ");
//	        UserInputObjects = scanner1.next();
//	        int index = UserInputObjects.indexOf('/');
//	        NumberOfPlayerString = UserInputObjects.substring(0,index);
//	        NumberOfPlayerInt = Integer.parseInt(NumberOfPlayerString);
//	      if (NumberOfPlayerInt==0) {
//	    	  NumberOfObjectInt=0;
//	      }
//	      else {  NumberOfObjectString = UserInputObjects.substring(index+1);
//	        NumberOfObjectInt = Integer.parseInt(NumberOfObjectString);} 
//	        switch (NumberOfPlayerInt) {
//	        case 1 : {
//	        	if (User1Card.Objects.contains(NumberOfObjectInt)) {
//	        			int j=0;
//	                    int i=0;
//	        		while(i < MiddleCard.NumberofObjects) {
//	        		if(MiddleCard.Objects.get(i)==NumberOfObjectInt) {
//	        	   if(User1score <= NUM_ROUNDS -1) {
//	        		User1score++;	 
//	        		System.out.println("║ User 1 : Great ║");    	    	   	
//	                MiddleCard = User1Card;
//	                User1Card = table.ChooseNewCard(Cards.cards);
//	                i=8;
//	                j=1;
//	        	   }
//	           	else {	   	
//	        		System.out.println("║ User 1 : Win ║");
//	            	b = false;
//	            	a= false;
//	            	i=8;
//	            	j=1;
//	           	} 
//	        	 }
//		        	  i++;  
//			        	 
//	        		}
//	        	if(j==0) {
//	           		User1score--;	   	
//	        		System.out.println("║ User 1 : Objects Dont Contain On the Middle Card ║");
//                   	        	
//	        	}
//		    	}
//	        	else {
//	           		System.out.println("║ User 1 : You dont Have This Object In Your Card ║");
//	        	}
//	        	break;
//	        }
//	        case 2 : {
//	        	if (User2Card.Objects.contains(NumberOfObjectInt)) {
//	        		int j=0;
//	    	        int i=0;
//	        		while(i < MiddleCard.NumberofObjects) {
//	        		//System.out.println("1");    	    	   	
//		        	if(MiddleCard.Objects.get(i)==NumberOfObjectInt) {
//	        	   if(User2score <= NUM_ROUNDS -1) {
//	        		User2score++;	 
//	        		System.out.println("║ User 2 : Great ║");    	    	   	
//	                MiddleCard = User2Card;
//	                User2Card = table.ChooseNewCard(Cards.cards);
//	                i=8;
//	                j=1;
//	        	   }
//	        	   
//	           	else {	   	
//	        		System.out.println("║ User 2 : Win ║");
//	            	i=8;
//	            	j=1;
//	            	b= false;
//	            	a= false;
//	            } 
//	        	  
//	        	 }
//		        	 i++;  
//	        		}
//	        	if(j==0) {
//	           		User2score--;	   	
//	        		System.out.println("║ User 2 : Objects Dont Contain On the Middle Card ║");
//                   	        	
//	        	}
//		    	}
//	        	else {
//	           		System.out.println("║ User 2 : You dont Have This Object In Your Card ║");
//	        	}
//	        	break;
//	        }
//	    case 0 :{
//	         	b = false;
//            	a= false; 	
//	        	break;
//	        }
//	       default :{
//	    		System.out.println("║ Invalide Input ║");       
//	       }
//	        }
//	        }   
//	        	
//	        	break;
//			}
//			case 2 :{
//				System.out.println("║ We Work On it ║"); 
//		        	break;
//			}
//			case 0 :{
//			a=false;
//		     break;
//			}
//			default : {
//				System.out.println("║ Invalide Choice ║");
//			break;	
//			}    
//	        
//	        
//	        }
//			  }
//			break;
//		}
//		case 2 :{
//			System.out.println("║ We Work On it ║"); 
//	        	break;
//		}
//		case 3 :{
//			System.out.println("║ We Work On it ║"); 
//	        	break;
//		}
//		case 4 :{
//			System.out.println("║ We Work On it ║"); 
//	        	break;
//		}
//		case 0 :{
//			System.out.println("║ Se You Later ║");
//			c=false;
//	        	break;
//		}
//		default : {
//			System.out.println("║ Invalide Choice ║");
//		break;	
//		}
//		}
//	   }
//	}
//}