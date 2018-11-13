public class Driver {

    public static void main(String[] args)
    {
        Patient[] myPatientList = new Patient[15];
        myPatientList[0] = new Patient();
        myPatientList[0].getInfo(myPatientList);
        myPatientList[0].simulation(myPatientList);
    }
}
