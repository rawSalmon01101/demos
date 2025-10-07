/*
 * progress check:
 * appointment feature: check
 * Emergency feature: check
 * lookup feature: check
 * Export spreadsheet:      null
 * reduce code:             null
 * no errors: check
 */
import java.util.Scanner;
class hospmgmt
{
    private String [] patientName = new String[9999];
    private String [] patientDOB = new String [9999];
    private long [] patientPH = new long[9999];
    private String [] appReason = new String[9999];
    private String [] moreInfo = new String [9999];
    private String [] patientAddress = new String[9999];
    private int [] emergencyID = new int [9999];
    //begin pid & inc from 1 since they cause issues if set to 0
    private int pid = 1;
    int inc = 1;

    //instance of outer class
    static hospmgmt toplevel = new hospmgmt();
    public static void main (String args[]) //<---- begin execution
    {
        hospmgmtDef inner = toplevel.new hospmgmtDef();
        inner.begin();
    }
    public class hospmgmtDef
    {
        public void begin ()
        {
            Scanner sc = new Scanner (System.in);
            System.out.print("Enter purpose: Appointment / Emergency / Lookup: ");
            String purpose = sc.next().toLowerCase();

            //create new instnce of mgmt
            mgmt newInstance = toplevel.new mgmt();
            if (purpose.contains("appointment"))
            {
                newInstance.startAppointment();
            }
            else if (purpose.contains("emergency"))
            {
                newInstance.startEmergency();
            }
            else if (purpose.contains("lookup"))
            {
                newInstance.lookup();
            }
            else
            {
                System.out.println("Invalid input.");
                main(new String [] {});
            }
            sc.close();
        }
    }
    class mgmt
    {
        //create global instance of previous class
        hospmgmtDef newInstance2 = new hospmgmtDef();
        public void startAppointment()
        {
            appointmentInput(); //<-- cannot directly call private methods from outside
        }
        private void appointmentInput()
        {
            @SuppressWarnings("resource")
            Scanner in = new Scanner (System.in);
            System.out.print("Enter patient's full name: ");
            patientName[pid] = in.nextLine();
            System.out.print("Enter patient's Date of Birth (DD/MM/YYYY format): ");
            patientDOB[pid] = in.nextLine();
            System.out.print("Enter patient's full address: ");;
            patientAddress[pid] = in.nextLine();
            System.out.print("Enter patient's phone number: ");
            patientPH[pid] = in.nextLong();
            System.out.print("Enter appointment reason: ");
            in.nextLine(); //workaround for nextline() skipping bug when followed by nextInt()
            appReason [pid] = in.nextLine();
            System.out.print("Enter anything else the patient might like to add here: ");
            moreInfo[pid] = in.nextLine();
            System.out.println();
            appDetailsVerify();
        }
        private void appDetailsVerify()
        {
            @SuppressWarnings("resource")
            Scanner ch =  new Scanner (System.in);
            System.out.println("Confirm the information below if it is correct or not. Write yes to proceed or no to go back:\n");
            System.out.println(patientName[pid]);
            System.out.println(patientDOB[pid]);
            System.out.println(patientAddress[pid]);
            System.out.println(patientPH[pid]);
            System.out.println(appReason[pid]);
            System.out.println(moreInfo[pid]);
            String conf = ch.next().toLowerCase();
            if (conf.contains("yes"))
            {
                System.out.println("The info is saved.");
                System.out.println("Saved entry for Patient ID "+pid);
                pid++;
                newInstance2.begin();
            }
            else if (conf.contains("no"))
            {
                System.out.println("Rewrite entries for Patient ID "+pid+": ");
                appointmentInput();
            }
            else
            {
                System.out.println("Invalid input.");
                appDetailsVerify();
            }
        }
        public void startEmergency()
        {
            emergencyInput(); // same; cannot call private methods from outside
        }
        private void emergencyInput()
        {
            @SuppressWarnings("resource")
            Scanner em = new Scanner (System.in);
            System.out.print("Enter patient's full name: ");
            patientName[pid] = em.nextLine();
            System.out.print("Enter patient's Date of Birth: ");
            patientDOB[pid] = em.nextLine();
            System.out.print("Enter emergency contact number: ");
            patientPH[pid]  = em.nextLong();
            em.nextLine(); //wokaround for nextline() bug; same as previous one
            System.out.print("Enter any allergies / medical conditions in this field: ");
            moreInfo[pid] = em.nextLine();
            System.out.println();
            emergencyDetailsVerify();
        }
        private void emergencyDetailsVerify()
        {
            @SuppressWarnings("resource")
            Scanner ch = new Scanner (System.in);
            System.out.println("Verify and enter yes / no: ");
            System.out.println("Patient's full name: "+patientName[pid]);
            System.out.println("Patient's Date of Birth: "+patientDOB[pid]);
            System.out.println("Emergency contact number: "+patientPH[pid]);
            System.out.println("Medical conditions: "+moreInfo[pid]);
            String emch = ch.next().toLowerCase();
            if (emch.contains("yes"))
            {
                System.out.println("The info is saved.");
                System.out.println("Enrolled entry for Emergency Patient ID "+pid);
                pid++;
                emergencyID[inc] = pid;
                inc++;
                newInstance2.begin();
            }
            else if (emch.contains("no"))
            {
                System.out.println("Rewrite entries for emergency Patient ID "+pid+": ");
                emergencyInput();
            }
            else
            {
                System.out.println("Invalid input.");
                emergencyDetailsVerify();
            }
        }
        private void lookup()
        {
            try
            {
                @SuppressWarnings("resource")
                Scanner up = new Scanner (System.in);
                System.out.print("Enter Patient ID to lookup: ");
                int lookupPID = up.nextInt();
                boolean isEmergency = false;
                if (patientAddress[lookupPID] == null && appReason[lookupPID] == null) // i found this to work better than the loop thing i came up with previously
                {
                    isEmergency = true;
                }
                if (isEmergency == true)
                {
                    System.out.println("Emergency ID at PID "+lookupPID);
                    System.out.println("Full name: "+patientName[lookupPID]);
                    System.out.println("Date of Birth: "+patientDOB[lookupPID]);
                    System.out.println("Contact number: "+patientPH[lookupPID]);
                    System.out.println("Medical conditions: "+moreInfo[lookupPID]);
                }
                else
                {
                    System.out.println("Full name: "+patientName[lookupPID]);
                    System.out.println("Date of Birth: "+patientDOB[lookupPID]);
                    System.out.println("Full address: "+patientAddress[lookupPID]);
                    System.out.println("Contact number: "+patientPH[lookupPID]);
                    System.out.println("Appointment reason: "+appReason[lookupPID]);
                    System.out.println("Medical conditions: "+moreInfo[lookupPID]);
                }
                newInstance2.begin();
            }
            catch (Exception e)
            {
                System.out.println("Whoops! You were trying to look up an invalid entry.");
                newInstance2.begin();
            }
        }
    }
}