public class Patient
{
    protected String firstName;
    protected String lastName;
    protected String birthday;
    protected String medicalCondition;
    protected int arrivalTime;
    protected int timeAttended;
    protected int urgency;

    public Patient()
    {
        firstName = "Patient";
        lastName = "1";
        birthday = "1/1/1990";
        medicalCondition = "minor cut";
        arrivalTime = 0;
        timeAttended = 0;
        urgency = 0;
    }

    public void getInfo(Patient[] myList)                   //initializes the rest of the patients in the array Also sets all patient with new information
    {
        //6 Arrays are filled with data to be used to fill patient information and can later be modified
        String [] firstNameList = {"Vicente","Jose","Jorge","Jim","Jacob","Teddy","Itzel","Maria","Daisy","Linda","Kery","Gaby","Rosaline","Ashley","Bianca"};
        String [] lastNameList = {"Romero","Reyes","Villa","Franco","Mills"," Rose","Romero","Penaloza","Florez","Grey","Mendez","Rios","Diaz","Valdez","Penaloza"};
        String [] birthdayList = {"2/21/1993","1/1/2000","6/7/1950","10/2/1984","11/1/1963","12/31/1990","4/15/1978","9/28/1974","8/29/1943","3/1/2007","5/15/2005","1/30/1993","7/22/1999","6/18/1996","2/25/1980"};
        String [] medicalConditionList = {"Minor Cut","Bee Sting","Wasp Sting","Flu","Fever","Fractured Leg","Broken Arm","Head Injury","3rd Degree Burns","Heart Attack","Car Crash","Knife Wound","Gun Shot","Amputation","Cancer"};
        int [] arrivalTimeList = {0,1,37,2,13,3,12,4,38,5,10,6,9,7,8};
        int [] urgencyList = {20,18,17,16,15,13,12,10,8,7,6,5,4,2,1};

        for(int i=1;i<myList.length;i++)
        {
            myList[i] = new Patient();
        }

        for(int i=0;i<myList.length;i++)
        {
            myList[i].firstName = firstNameList[i];
            myList[i].lastName = lastNameList[i];
            myList[i].birthday = birthdayList[i];
            myList[i].medicalCondition = medicalConditionList[i];
            myList[i].arrivalTime = arrivalTimeList[i];
            myList[i].urgency = urgencyList[i];
        }
    }

    public String toString()                                                // Prints out patients info and checks for time attended to properly format
    {
        System.out.println("-----------------------------------------------------");
        System.out.println();
        System.out.println("Patient name: "+firstName+" "+lastName);
        System.out.println("Date of Birth: "+ birthday);
        if(arrivalTime<10)
            System.out.println("Time of Arrival: 1:0"+arrivalTime);
        if(arrivalTime>=10)
            System.out.println("Time of Arrival 1:"+arrivalTime);

        System.out.println("Medical Condition: "+ medicalCondition);
        System.out.println("Urgency Priority: "+urgency);
        if(timeAttended<10)
            System.out.println("Time Attended 1:0"+timeAttended);
        else if(timeAttended>=10 && timeAttended<60)
            System.out.println("Time Attended 1:"+timeAttended);
        else if(timeAttended>=60)
        {
            timeAttended = timeAttended - 60;
            if(timeAttended<10)
                System.out.println("Time Attended 2:0"+timeAttended);
            else if(timeAttended>=10 && timeAttended<60)
                System.out.println("Time Attended 2:"+timeAttended);
        }
        return "";
    }

    public void simulation(Patient [] myPatientList)
    {
        Heap waitingRoom = new Heap();
        boolean acceptPatient=true;
        int waitTime=-1;

        for(int i =0;i<90;i++)                                               //Simulates 60 minutes of a hospital and then closes but will still attend patients that have already entered
        {
            for(int j=0;j<myPatientList.length;j++)                          //Check if any patients walked in
            {
                if(myPatientList[j].arrivalTime==i)                              // Push into the waiting room
                {
                    waitingRoom.insert(myPatientList[j],myPatientList[j].urgency,myPatientList[j].arrivalTime);
                }
            }

            for(int k=0;k<myPatientList.length;k++)                         //Up dates all patients with current time
            {
                myPatientList[k].timeAttended = i;
            }

            if(waitTime==i)                                     //checks if there is a patient being attended within a 2 min range
            {
                acceptPatient=true;
            }

            if(!waitingRoom.isEmpty())                              //if there isn't anyone in line don't accept patients or place wait time
            {
                if (acceptPatient)                                 //accepts first patient then denies access until wait time has been achieved
                {
                    waitingRoom.delete();
                    waitingRoom.age();
                    acceptPatient = false;
                    waitTime = i + 2;
                }
            }

        }
    }
}
