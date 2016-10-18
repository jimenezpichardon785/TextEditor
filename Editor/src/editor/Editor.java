
package editor;

//Nerys Jimenez Pichardo
//Editor.java
//March 19, 2015
import java.util.Scanner;
public class Editor 
{
	/*IN Insert
	 *DL Delete
	 *MV Move
	 *LA List All
	 *XT Exit
	 */
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner (System.in);
		ListNode editor;	//beginning of the list
		ListNode current;	//current line
		ListNode precursor;	//reference for inserts
		ListNode tail;	//end of the list
		
	String command;
        String inputString;
        String number;
        int someint;
        String insert = "IN";
        String del = "DL";    	
        String move = "MV";
        String listall = "LA";
        String exit = "XT";
        String done = "//";	
        boolean ile = false;
        
        editor = new ListNode();
        editor.link = new ListNode();
        editor.link.data = "<<END>>";
        tail = editor.link;
        editor.link.link = null;
        
        current = editor.link;
		precursor = editor;
        
        System.out.print("> ");
        command = keyboard.next();
        inputString = keyboard.nextLine();
		
		while (!(command.equals(exit)))
		{	
			/* IN command inserts new input lines into the file
			 * just before the current line
			 */
			ile = false;
			if (command.equals(insert))
			{   
						System.out.print(": " );
						inputString = keyboard.nextLine();
					
					while (!(inputString.equals(done)))
					{	
						ListNode temp = new ListNode();
						temp.data = inputString;
						temp.link = current;
						precursor.link = temp;
						precursor = temp;
						System.out.print(": " );
						inputString = keyboard.nextLine();
					}		
			}
			/*DL command deletes the current line.
			 * The successor of the deleted line becomes the new current line
			 */
			else if(command.equals(del))
			{
				if(current != tail)
				{
					precursor.link = current.link;
					current = precursor.link;
				}
			}
			/*MV command moves the current line down n lines when followed
			 * by a positive integer n and moves the current line down m lines
			 * when followed by a negative integer m.
			 */
			else if(command.equals(move))
			{
				number = inputString.substring(1);
				number = number.trim();
				someint = Integer.parseInt(number);
				if(someint < 0)
				{
					for(int i=0; i < someint * -1; ++i )
					{
						if(precursor != editor)
						{
							current = precursor;
							precursor = previous( precursor,editor);
						}
					}
					
				}
				if(someint > 0)
				{
					for(int j = 0; j < someint; j++)
					{	
						if(current != tail)
						{
							precursor = current;
							current = current.link;
						}
					}
				}
				System.out.println(current.data);
			}
			else if(command.equals(listall))
			{
				printLinked(current);
			}
			else
			{
				System.out.println();
				System.out.print("> ");
	    		command = keyboard.next();
	    		inputString = keyboard.nextLine();
	    		ile = true;
			}
			if(!ile)
			{
			System.out.println();
			System.out.print("> ");
    		command = keyboard.next();
    		inputString = keyboard.nextLine();
			}
		}
    }
    /*prints the list from current
     */
    public static void printLinked(ListNode head)
    {
		ListNode cursor;
	    cursor = head;
	    while (cursor != null)
	    {
	    	System.out.println(cursor.data +" ");
	    	cursor = cursor.link;

    	}
    }
    /* returns a pointer to the node preceding the one
     * pointed to by its first parameter.
     */
    public static ListNode previous(ListNode ptr, ListNode head)
    {
    	ListNode temp = head;
    	while(temp.link != ptr)
    	{
    		temp = temp.link;
    	}
    	return temp;
    }
}
