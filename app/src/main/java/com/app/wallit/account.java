package com.app.wallit;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class account {
    private static float TotalBalance;
    private static int DailyBudget;
    private static String email;
    public static ArrayList<list> List = new ArrayList<list>();
    private static String History = "";
    private static boolean Updated = false;
    private static boolean First = true;
    static float []Percent = {0,0,0,0,0,0,0};

    public static boolean isFirst() {
        return First;
    }

    public static void setFirst(boolean first) {
        First = first;
    }

    public static boolean isUpdated() {
        return Updated;
    }

    public static void setUpdated(boolean updated) {
        Updated = updated;
    }

    public static String getHistory() {
        return History;
    }

    public static void setHistory(String hist) {
        History = hist;
    }


    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        account.email = email;
    }

    public account(float totalBalance){
        TotalBalance=totalBalance;
    }

    public static float getTotalBalance() {
        return TotalBalance;
    }

    public static void setTotalBalance(String totalBalance) {
        float tb = Float.parseFloat(totalBalance);
        TotalBalance =  tb;
    }

    public static int addTotalBalance(String totalBalance) {
        int tb = Integer.parseInt(totalBalance);
        return (int) (TotalBalance =  TotalBalance + tb);
    }

    public static int getDailyBudget() {
        return DailyBudget;
    }

    public static void setDailyBudget() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd");
        int currDate = Integer.parseInt(ft.format(date));
        int i = 30-currDate;
        if(i==0) DailyBudget= (int) TotalBalance;
        else{
        DailyBudget = (int) (getTotalBalance()/i);}
    }
    public static void add_into_list(list l) {
        List.add(l);
    }

    public static void UpdateTotalBalance(int a){
        TotalBalance = TotalBalance - a;
    }

    public static ArrayList<list> populateList( ){
        String arr1[];
        String arr2[];
        ArrayList<list> hist = new ArrayList<list>();
        if(History.length()!=0){
        arr1 = History.split("_");
        for(int i = 1;i<arr1.length;i++){
            arr2 = arr1[i].split("#");
            int amount = Integer.parseInt(arr2[0]);
            String Cat = arr2[1];
            String date = arr2[2];
            hist.add(new list(amount,Cat,date));}
        }
    return hist;
    }

    public static float[] getPercent(){
        float percent[] = {0,0,0,0,0,0,0};
        for(list l : List){
            percent[0]= percent[0]+l.amount;
            switch(l.category){
                case "FOOD":
                    percent[1]= percent[1]+l.amount;
                    break;
                case "SHOPPING":
                    percent[2]= percent[2]+l.amount;
                    break;
                case "STUDIES":
                    percent[3]= percent[3]+l.amount;
                    break;
                case "SELF CARE":
                    percent[4]= percent[4]+l.amount;
                    break;
                case "OUTING":
                    percent[5]= percent[5]+l.amount;
                    break;
                case "OTHERS":
                    percent[6]= percent[6]+l.amount;
                    break;
            }
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);

        for(int j = 1; j<=6;j++){
            percent[j] = Float.parseFloat(df.format (percent[j]/percent[0]*100));

        }
   return percent;
    }
}
